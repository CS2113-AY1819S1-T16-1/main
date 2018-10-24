package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteManager;

/**
 * Lists notes based on given predicate.
 */
public class NoteListCommand extends Command {

    public static final String COMMAND_WORD = "note list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists notes. "
            + "Parameters: "
            + "[" + PREFIX_MODULECODE + "MODULE_CODE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULECODE + "CS2113";

    public static final String MESSAGE_SUCCESS = "Listed %1$s note(s).";

    public static final String MESSAGE_NOT_FOUND = "No notes were found.";

    private final String moduleCode;

    public NoteListCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        NoteManager noteManager = NoteManager.getInstance();

        noteManager.setFilteredNotes(moduleCode);

        StringBuilder sb = new StringBuilder();

        int listId = 1;
        int size = noteManager.getFilteredNotes().size();

        for (Note n: noteManager.getFilteredNotes()) {
            sb.append(listId + ":\n");
            sb.append("Module Code: ");
            sb.append(n.getModuleCode() + "\n");
            sb.append("Date: ");
            sb.append(n.getDate() + "\n");
            sb.append("Note:\n");
            sb.append(n.getNoteText() + ((listId < size) ? "\n\n" : "\n"));
            listId++;
        }

        if (sb.length() > 0) {
            return new CommandResult(
                    String.format(MESSAGE_SUCCESS, size)
                            + "\n\n" + sb.toString());
        } else {
            return new CommandResult(String.format(MESSAGE_NOT_FOUND));
        }
    }
}
