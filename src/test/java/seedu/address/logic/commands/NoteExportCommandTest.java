package seedu.address.logic.commands;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.note.NoteManager;
import seedu.address.testutil.NoteBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Contains tests for NoteExportCommand.
 */
public class NoteExportCommandTest {

    private static NoteManager noteManager = NoteManager.getInstance();

    private NoteBuilder note1 = new NoteBuilder();

    @Before
    public void setUp() {
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }

    @Test
    public void execute_containsExportableNotes_success() throws CommandException {
        // String expectedMessage = NoteExportCommand.MESSAGE_SUCCESS;
        String unwantedMessage = NoteExportCommand.MESSAGE_NO_EXPORTABLE_NOTES;

        noteManager.addNote(new NoteBuilder().build());
        noteManager.addNote(new NoteBuilder().build());
        noteManager.addNote(new NoteBuilder().build());
        noteManager.saveNoteList(); // three exportable notes

        NoteExportCommand noteExportCommand = new NoteExportCommand("valid_file_name");
        CommandResult result = noteExportCommand.execute(new ModelManager(), new CommandHistory());

        // assertEquals(String.format(expectedMessage, 3), result.feedbackToUser);
        assertNotEquals(unwantedMessage, result.feedbackToUser);
    }

    @Test
    public void execute_noExportableNotes_displaysMessageNoExportableNotes() throws CommandException {
        String expectedMessage = NoteExportCommand.MESSAGE_NO_EXPORTABLE_NOTES;

        // setup notes with null start dates
        NoteBuilder noteA = new NoteBuilder(note1.build());
        NoteBuilder noteB = new NoteBuilder(note1.build());
        NoteBuilder noteC = new NoteBuilder(note1.build());

        noteA = noteA.withNullStartDate();
        noteB = noteB.withNullStartDate();
        noteC = noteC.withNullStartDate();

        noteManager.addNote(noteA.build());
        noteManager.addNote(noteB.build());
        noteManager.addNote(noteC.build());
        noteManager.saveNoteList(); // three notes with null start date, zero exportable notes

        NoteExportCommand noteExportCommand = new NoteExportCommand("valid_file_name");
        CommandResult result = noteExportCommand.execute(new ModelManager(), new CommandHistory());

        assertEquals(expectedMessage, result.feedbackToUser);
    }

    @AfterClass
    public static void tearDown() {
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }
}
