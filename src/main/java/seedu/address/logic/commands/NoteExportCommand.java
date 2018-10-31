package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_FILE_NAME;

import java.util.ArrayList;

import seedu.address.commons.util.CsvUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteManager;
import seedu.address.storage.adapter.CsvAdaptedNote;

/**
 * Export notes to a CSV file.
 */
public class NoteExportCommand extends Command {

    public static final String COMMAND_WORD = "note export";

    public static final String MESSAGE_FAIL = "Error exporting to CSV!";
    public static final String MESSAGE_INVALID_FILE_NAME = "Please enter a valid filename.\n"
            + "A valid filename should only contain the following:\n"
            + "- Alphanumeric characters [A-Z][a-z][0-9]\n"
            + "- Underscores [_]\n"
            + "- Hyphens [-]";

    public static final String MESSAGE_NO_EXPORTABLE_NOTES = "You have no notes that can be exported.";
    public static final String MESSAGE_SUCCESS = "Exported %1$s note(s).";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": exports notes. "
            + "Parameters: "
            + PREFIX_NOTE_FILE_NAME + "FILE_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NOTE_FILE_NAME + "myNotesAsCsv";

    private final String fileName;


    public NoteExportCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        NoteManager noteManager = NoteManager.getInstance();

        ArrayList<String> noteCsvList = new ArrayList<>();
        CsvAdaptedNote adaptedNote;

        for (Note note : noteManager.getExportableNotes()) {
            adaptedNote = new CsvAdaptedNote(note);
            noteCsvList.add(adaptedNote.toString());
        }

        int size = noteCsvList.size();
        boolean writeSuccess = false;

        if (size > 0) {
            writeSuccess = CsvUtil.writeToCsv(
                    fileName, noteManager.getCsvHeaders(), noteCsvList);
        } else {
            return new CommandResult(MESSAGE_NO_EXPORTABLE_NOTES);
        }

        if (writeSuccess) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, size));
        } else {
            return new CommandResult(MESSAGE_FAIL);
        }
    }
}
