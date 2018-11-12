package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.NoteManager;

/**
 * Lists notes based on given predicate.
 */
public class NoteListCommand extends Command {

    public static final String COMMAND_WORD = "note list";

    public static final String MESSAGE_EMPTY_MODULE_CODE_ARG =
            "Please do not leave the MODULE_CODE blank for parameter mc/MODULE_CODE.\n"
            + "If you wish to list all notes, enter the command without the parameter.";

    public static final String MESSAGE_NOT_FOUND = "No notes were found.";

    public static final String MESSAGE_SUCCESS = "Listed %1$s note(s).";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists notes. "
            + "Parameters: "
            + "[" + PREFIX_MODULE_CODE + "MODULE_CODE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2113";

    private final String moduleCode;

    public NoteListCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        NoteManager noteManager = NoteManager.getInstance();

        noteManager.setFilteredNotesByModuleCode(moduleCode);

        if (noteManager.getFilteredNotes().size() == 0) {
            noteManager.refreshFilteredNotes();
            return new CommandResult(MESSAGE_NOT_FOUND);
        }

        int size = noteManager.getFilteredNotes().size();
        String noteList = noteManager.getHtmlNoteList();

        System.out.println(noteList);

        return new CommandResult(String.format(MESSAGE_SUCCESS, size), noteList);
    }
}
