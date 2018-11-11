package seedu.address.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.NoteListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.StorageController;
import seedu.address.model.note.NoteManager;

/**
 * Contains tests for NoteListCommandParser.
 */
public class NoteListCommandParserTest {

    private static NoteManager noteManager = NoteManager.getInstance();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private NoteListCommandParser parser = new NoteListCommandParser();

    @Before
    public void setUp() {
        StorageController.enterTestMode();
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }

    @Test
    public void parse_invalidArgs_throwsParseException() throws ParseException {
        String expectedMessageInvalidCommand = String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT, NoteListCommand.MESSAGE_USAGE);
        String expectedMessageEmptyModuleCodeArg = NoteListCommand.MESSAGE_EMPTY_MODULE_CODE_ARG;

        String args;

        try {
            // invalid args, missing prefix
            args = " CS2113";
            parser.parse(args);
            fail("Exception not thrown");
        } catch (ParseException e) {
            assertEquals(expectedMessageInvalidCommand, e.getMessage());
        }

        try {
            // invalid args, with prefix but blank param
            args = " " + PREFIX_MODULE_CODE;
            parser.parse(args);
            fail("Exception not thrown");
        } catch (ParseException e) {
            assertEquals(expectedMessageEmptyModuleCodeArg, e.getMessage());
        }
    }

    @Test
    public void parse_validArgs_success() throws ParseException {
        // valid args with empty arguments
        String args = "";
        NoteListCommand noteListCommand = parser.parse(args);

        assertNotNull(noteListCommand);

        // valid args with module code
        args = " " + PREFIX_MODULE_CODE + "CS2113";
        noteListCommand = null;
        noteListCommand = parser.parse(args);

        assertNotNull(noteListCommand);
    }

    @AfterClass
    public static void tearDown() {
        noteManager.clearNotes();
        noteManager.saveNoteList();
    }
}
