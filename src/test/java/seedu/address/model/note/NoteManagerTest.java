package seedu.address.model.note;

import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.NoteDeleteCommand;
import seedu.address.testutil.NoteBuilder;

/**
 * Contains tests for NoteManager.
 */
public class NoteManagerTest {

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
    public void addNote_nullNote_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        noteManager.addNote(null);
    }

    @Test
    public void deleteNote_indexOutOfBounds_throwsIndexOutOfBoundsException() {
        String expectedMessage = NoteDeleteCommand.MESSAGE_INVALID_INDEX;

        noteManager.addNote(note1.build());
        noteManager.addNote(note2.build());
        noteManager.saveNoteList();

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(expectedMessage);
        noteManager.deleteNote(2);
    }

    @Test
    public void getNoteAt_outOfBoundsIndex_returnsNull() throws NullPointerException {
        noteManager.addNote(note1.build()); // at index 0
        noteManager.addNote(note2.build()); // at index 1
        noteManager.addNote(note3.build()); // at index 2
        noteManager.saveNoteList();

        int index = 3; // try to access index 3
        Note note = noteManager.getNoteAt(index);

        assertNull(note);
    }

    @AfterClass
    public static void tearDown() {
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }
}
