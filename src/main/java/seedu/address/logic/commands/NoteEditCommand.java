package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_TITLE;

import java.time.LocalDateTime;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteDate;
import seedu.address.model.note.NoteLocation;
import seedu.address.model.note.NoteManager;
import seedu.address.model.note.NoteTime;
import seedu.address.model.note.NoteTitle;
import seedu.address.ui.NoteTextEditWindow;

/**
 * Edits a note in Trajectory.
 */
public class NoteEditCommand extends Command {

    public static final String COMMAND_WORD = "note edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a note. "
            + "Parameters: "
            + "INDEX "
            + "[" + PREFIX_MODULE_CODE
            + "NEW_MODULE_CODE] "
            + "[" + PREFIX_NOTE_TITLE
            + "NEW_TITLE] "
            + "[" + PREFIX_NOTE_START_DATE
            + "NEW_START_DATE] "
            + "[" + PREFIX_NOTE_START_TIME
            + "NEW_START_TIME] "
            + "[" + PREFIX_NOTE_END_DATE
            + "NEW_END_DATE] "
            + "[" + PREFIX_NOTE_END_TIME
            + "NEW_END_TIME] "
            + "[" + PREFIX_NOTE_LOCATION
            + "NEW_LOCATION]\n"
            + "Example: " + COMMAND_WORD + " "
            + "4 "
            + PREFIX_NOTE_TITLE + "My second note "
            + PREFIX_NOTE_START_TIME + "10:45 AM";

    public static final String MESSAGE_SUCCESS = "Note has been edited.";
    public static final String MESSAGE_CANCEL = "Edit note operation has been cancelled.";
    public static final String MESSAGE_INVALID_INDEX = "Invalid input!\nINDEX %1$s is out of bounds.";
    public static final String MESSAGE_INVALID_DATE_TIME_DIFFERENCE =
            "Invalid input! Please make sure the start date/time is earlier than the end date/time.";

    private final int index;
    private final ModuleCode moduleCode;
    private final NoteTitle title;
    private final NoteDate startDate;
    private final NoteTime startTime;
    private final NoteDate endDate;
    private final NoteTime endTime;
    private final NoteLocation location;

    public NoteEditCommand(
            int index,
            ModuleCode moduleCode,
            NoteTitle title,
            NoteDate startDate,
            NoteTime startTime,
            NoteDate endDate,
            NoteTime endTime,
            NoteLocation location) {
        this.index = index;
        this.moduleCode = moduleCode;
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.location = location;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        NoteManager noteManager = NoteManager.getInstance();

        if (index > noteManager.getFilteredNotes().size() || index < 1) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX, index));
        }

        Note noteToEdit = noteManager.getNoteAt(index - 1);

        NoteDate newStartDate;
        NoteTime newStartTime;
        NoteDate newEndDate;
        NoteTime newEndTime;

        if (startDate != null) {
            newStartDate = startDate;
        } else {
            newStartDate = noteToEdit.getStartDate();
        }

        if (startTime != null) {
            newStartTime = startTime;
        } else {
            newStartTime = noteToEdit.getStartTime();
        }

        if (endDate != null) {
            newEndDate = endDate;
        } else {
            newEndDate = noteToEdit.getEndDate();
        }

        if (endTime != null) {
            newEndTime = endTime;
        } else {
            newEndTime = noteToEdit.getEndTime();
        }

        if (newStartDate != null && newEndDate == null) {
            newEndDate = new NoteDate(newStartDate.getDate().format(NoteDate.DATE_FORMAT));
        }

        if (startDate != null) {
            LocalDateTime newStartDateTime = LocalDateTime.of(newStartDate.getDate(), newStartTime.getTime());
            LocalDateTime newEndDateTime = LocalDateTime.of(newEndDate.getDate(), newEndTime.getTime());

            // result = 0, equal, valid
            // result > 0, newEndDateTime > newStartDateTime, valid
            // result < 0, newEndDateTime < newStartDateTime, invalid
            int result = newEndDateTime.compareTo(newStartDateTime);
            if (result < 0) {
                throw new CommandException(MESSAGE_INVALID_DATE_TIME_DIFFERENCE);
            }
        }

        NoteTextEditWindow noteTextEditWindow = new NoteTextEditWindow(noteToEdit);
        noteTextEditWindow.showAndWait();

        if (!noteTextEditWindow.isCancelled()) {
            if (moduleCode != null) {
                noteToEdit.setModuleCode(moduleCode);
            }

            if (title != null) {
                noteToEdit.setTitle(title);
            }

            if (startDate != null) {
                noteToEdit.setStartDate(startDate);
            }

            if (startTime != null) {
                noteToEdit.setStartTime(startTime);
            }

            if (endDate != null) {
                noteToEdit.setEndDate(endDate);
            } else {
                if (startDate != null) {
                    noteToEdit.setEndDate(new NoteDate(newStartDate.getDate().format(NoteDate.DATE_FORMAT)));
                }
            }

            if (endTime != null) {
                noteToEdit.setEndTime(endTime);
            }

            if (location != null) {
                noteToEdit.setLocation(location);
            }

            noteManager.saveNoteList();

            String noteList = noteManager.getHtmlNoteList();

            return new CommandResult(MESSAGE_SUCCESS, noteList);
        } else {
            return new CommandResult(MESSAGE_CANCEL);
        }
    }
}
