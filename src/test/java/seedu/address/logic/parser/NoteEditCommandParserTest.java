package seedu.address.logic.parser;

import static org.junit.Assert.assertNotNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.NoteEditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.NoteManager;
import seedu.address.testutil.NoteBuilder;

/**
 * Contains tests for NoteEditCommandParser.
 */
public class NoteEditCommandParserTest {

    private static NoteManager noteManager = NoteManager.getInstance();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private NoteEditCommandParser parser = new NoteEditCommandParser();

    @Before
    public void setUp() {
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }

    @Test
    public void parse_invalidArgs_throwsParseException() throws ParseException {
        String expectedMessage = String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT, NoteEditCommand.MESSAGE_USAGE);

        // invalid args
        String args = " this is an invalid input";

        thrown.expect(ParseException.class);
        thrown.expectMessage(expectedMessage);

        parser.parse(args);
    }

    @Test
    public void parse_validArgs_success() throws ParseException {
        noteManager.addNote(new NoteBuilder().build());
        noteManager.saveNoteList();

        // valid args
        String args1 = " 1  ";
        String args2 = "1 " + PREFIX_MODULE_CODE + "CS1010J";

        NoteEditCommand noteEditCommand = parser.parse(args1);
        assertNotNull(noteEditCommand);

        noteEditCommand = parser.parse(args2);
        assertNotNull(noteEditCommand);
    }

    @AfterClass
    public static void tearDown() {
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }
}
