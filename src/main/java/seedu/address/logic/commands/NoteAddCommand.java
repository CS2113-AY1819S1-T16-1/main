package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_TITLE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteManager;
import seedu.address.ui.NoteTextEditWindow;

/**
 * Adds a note to Trajectory.
 */
public class NoteAddCommand extends Command {

    public static final String COMMAND_WORD = "note add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to Trajectory. "
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + "[" + PREFIX_NOTE_TITLE + "TITLE] "
            + "[" + PREFIX_NOTE_START_DATE + "START_DATE] "
            + "[" + PREFIX_NOTE_START_TIME + "START_TIME] "
            + "[" + PREFIX_NOTE_END_DATE + "END_DATE] "
            + "[" + PREFIX_NOTE_END_TIME + "END_TIME] "
            + "[" + PREFIX_NOTE_LOCATION + "LOCATION]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NOTE_TITLE + "My First Note "
            + PREFIX_NOTE_START_DATE + "30-10-2020 "
            + PREFIX_NOTE_LOCATION + "Columbia, Schermerhorn 614";

    public static final String MESSAGE_SUCCESS = "Note has been added to %1$s.";
    public static final String MESSAGE_CANCEL = "Note creation has been cancelled.";

    private Note noteToAdd;

    public NoteAddCommand(Note note) {
        requireNonNull(note);
        noteToAdd = note;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        NoteManager noteManager = NoteManager.getInstance();

        NoteTextEditWindow noteTextEditWindow = new NoteTextEditWindow(noteToAdd);
        noteTextEditWindow.showAndWait();

        if (!noteTextEditWindow.isCancelled()) {
            noteManager.addNote(noteToAdd);
            noteManager.saveNoteList();

            String noteList = noteManager.getHtmlNoteList();

            return new CommandResult(String.format(MESSAGE_SUCCESS, noteToAdd.getModuleCode().toString()), noteList);
        } else {
            return new CommandResult(MESSAGE_CANCEL);
        }
    }
}
