package seedu.address.logic.commands;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteDate;
import seedu.address.model.note.NoteLocation;
import seedu.address.model.note.NoteManager;
import seedu.address.model.note.NoteTime;
import seedu.address.model.note.NoteTitle;
import seedu.address.testutil.NoteBuilder;

/**
 * Contains tests for NoteEditCommand.
 */
public class NoteEditCommandTest {

    private static NoteManager noteManager = NoteManager.getInstance();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private NoteBuilder note1 = new NoteBuilder();
    private NoteBuilder note2 = new NoteBuilder();
    private NoteBuilder note3 = new NoteBuilder();

    @Before
    public void setUp() {
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }

    @Test
    public void execute_invalidDateTimeDifference_throwsCommandException() throws CommandException {
        String expectedMessage = NoteEditCommand.MESSAGE_INVALID_DATE_TIME_DIFFERENCE;

        // setup Note to edit
        note2 = note2.withStartDate("12-12-2012");
        note2 = note2.withStartTime("10:00 AM");
        note2 = note2.withEndDate("13-12-2012");
        note2 = note2.withEndTime("9:00 AM");

        // Populate the notes list
        noteManager.addNote(note1.build()); // index 0
        noteManager.addNote(note2.build()); // index 1
        noteManager.addNote(note3.build()); // index 2
        noteManager.saveNoteList();

        int index = 2; // arraylist size: 3, accessed index = 1 (zero-based) -> within bounds

        // setup note2 with edited end date
        NoteBuilder editedNote2Builder = new NoteBuilder(note2.build());
        editedNote2Builder = editedNote2Builder.withEndDate("11-12-2012"); // earlier date than current note2's start date

        Note editedNote2 = editedNote2Builder.build();
        NoteEditCommand noteEditCommand =
                new NoteEditCommand(
                        index,
                        editedNote2.getModuleCode(),
                        editedNote2.getTitle(),
                        editedNote2.getStartDate(),
                        editedNote2.getStartTime(),
                        editedNote2.getEndDate(),
                        editedNote2.getEndTime(),
                        editedNote2.getLocation()
                );

        thrown.expect(CommandException.class);
        thrown.expectMessage(expectedMessage);
        noteEditCommand.execute(new ModelManager(), new CommandHistory());
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() throws CommandException {
        // Change some fields
        note2 = note2.withStartDate("22-12-2018");
        note3 = note3.withNoteText("note 3");

        noteManager.addNote(note1.build());
        noteManager.addNote(note2.build());
        noteManager.addNote(note3.build());
        noteManager.saveNoteList();

        int index = 5; // arraylist size: 3, accessed index = 4 (zero-based) -> out of bounds
        ModuleCode newModuleCode = new ModuleCode("CS5000");
        NoteTitle newTitle = new NoteTitle("My new title");
        NoteDate newStartDate = new NoteDate("1-1-2019");
        NoteTime newStartTime = new NoteTime("11:00 AM");
        NoteDate newEndDate = new NoteDate("1/1/2019");
        NoteTime newEndTime = new NoteTime("1:00 PM");
        NoteLocation newLocation = new NoteLocation("National University of Singapore");

        // Try to modify the Note at index 4 of arraylist
        NoteEditCommand noteEditCommand =
                new NoteEditCommand(
                        index,
                        newModuleCode,
                        newTitle,
                        newStartDate,
                        newStartTime,
                        newEndDate,
                        newEndTime,
                        newLocation
                );

        thrown.expect(CommandException.class);
        thrown.expectMessage(String.format(NoteEditCommand.MESSAGE_INVALID_INDEX, index));
        noteEditCommand.execute(new ModelManager(), new CommandHistory());
    }

    @AfterClass
    public static void tearDown() {
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }
}
