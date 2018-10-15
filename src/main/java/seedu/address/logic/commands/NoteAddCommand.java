package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_MODULE_CODE;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.NoteManager;
import seedu.address.model.note.Note;
import seedu.address.ui.NoteEntryPrompt;

/**
 * Adds a note to Trajectory.
 */
public class NoteAddCommand extends Command {

    public static final String COMMAND_WORD = "note add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note. "
            + "Parameters: "
            + PREFIX_NOTE_MODULE_CODE + "MODULE CODE "
            + "[" + PREFIX_NOTE_DATE + "DATE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NOTE_MODULE_CODE + "CS2113";

    public static final String MESSAGE_SUCCESS = "Note has been added to %1$s.";
    public static final String MESSAGE_CANCEL = "Note creation has been cancelled.";

    private static NoteManager noteManager = new NoteManager();

    private Note noteToAdd;

    public NoteAddCommand(Note note) {
        requireNonNull(note);
        noteToAdd = note;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        boolean isCancelled = showTextPrompt();

        if (!isCancelled) {
            noteManager.getDataFromFile();
            noteManager.addNote(noteToAdd);
            noteManager.saveDataToFile();

            return new CommandResult(String.format(MESSAGE_SUCCESS, noteToAdd.getModuleCode()));
        } else {
            return new CommandResult(MESSAGE_CANCEL);
        }
    }

    /**
     * Displays a pop-up window for the user to enter input.
     *
     * @return true if the user cancelled, otherwise false
     */
    public boolean showTextPrompt() {

        try {
            NoteEntryPrompt controller = new NoteEntryPrompt();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NoteEntryPromptWindow.fxml"));
            loader.setController(controller);
            BorderPane layout = loader.load();
            Scene scene = new Scene(layout);

            Stage notePromptStage = new Stage();
            notePromptStage.setMinHeight(380);
            notePromptStage.setMinWidth(350);
            notePromptStage.setTitle("Add/Edit note");
            notePromptStage.initModality(Modality.APPLICATION_MODAL);
            notePromptStage.setScene(scene);

            controller.setDialogStage(notePromptStage);
            controller.setNote(noteToAdd);

            notePromptStage.showAndWait();

            return controller.isCancelled();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
