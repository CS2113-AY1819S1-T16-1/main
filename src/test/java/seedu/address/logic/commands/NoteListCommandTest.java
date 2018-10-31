package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.note.NoteManager;
import seedu.address.testutil.NoteBuilder;

/**
 * Contains tests for NoteListCommand.
 */
public class NoteListCommandTest {

    private static NoteManager noteManager = NoteManager.getInstance();

    private NoteBuilder note1 = new NoteBuilder();
    private NoteBuilder note2 = new NoteBuilder();
    private NoteBuilder note3 = new NoteBuilder();

    @Before
    public void setUp() {
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }

    @Test
    public void execute_emptyList_displaysMessageNotFound() throws CommandException {
        NoteListCommand noteListCommand = new NoteListCommand(""); // list all
        CommandResult result = noteListCommand.execute(new ModelManager(), new CommandHistory());

        assertEquals(NoteListCommand.MESSAGE_NOT_FOUND, result.feedbackToUser);
    }

    @Test
    public void execute_nonEmptyList_displaysList() throws CommandException {
        noteManager.addNote(note1.build());
        noteManager.addNote(note2.build());
        noteManager.saveNoteList(); // two notes in arraylist, size = 2

        NoteListCommand noteListCommand = new NoteListCommand(""); // list all
        CommandResult result = noteListCommand.execute(new ModelManager(), new CommandHistory());

        assertEquals(
                String.format(NoteListCommand.MESSAGE_SUCCESS, noteManager.getNotes().size()),
                result.feedbackToUser);
    }

    @Test
    public void execute_nonEmptyListWithFilter_displaysListOrMessageNotFound() throws CommandException {
        String expectedMessageFound = NoteListCommand.MESSAGE_SUCCESS;
        String expectedMessageNotFound = NoteListCommand.MESSAGE_NOT_FOUND;

        // setup notes
        note1 = note1.withModuleCode("CS2040C");
        note2 = note2.withModuleCode("CS1010");
        note3 = note3.withModuleCode("CS2040C");

        noteManager.addNote(note1.build());
        noteManager.addNote(note2.build());
        noteManager.addNote(note3.build());
        noteManager.saveNoteList(); // two notes with module code 'CS2040C', one note with 'CS1010'

        NoteListCommand noteListCommand = new NoteListCommand("CS2040C"); // exists
        CommandResult result = noteListCommand.execute(new ModelManager(), new CommandHistory());
        assertEquals(
                String.format(expectedMessageFound, 2),
                result.feedbackToUser);

        noteListCommand = new NoteListCommand("CS1010"); // exists
        result = noteListCommand.execute(new ModelManager(), new CommandHistory());
        assertEquals(
                String.format(expectedMessageFound, 1),
                result.feedbackToUser);


        noteListCommand = new NoteListCommand("CS5000"); // does not exist
        result = noteListCommand.execute(new ModelManager(), new CommandHistory());
        assertEquals(expectedMessageNotFound, result.feedbackToUser);
    }

    @AfterClass
    public static void tearDown() {
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }
}
