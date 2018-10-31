package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_FILE_NAME;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.NoteExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains tests for NoteExportCommandParser.
 */
public class NoteExportCommandParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private NoteExportCommandParser parser = new NoteExportCommandParser();

    @Test
    public void parse_invalidFileName_throwsParseException() throws ParseException {
        String expectedMessage = NoteExportCommand.MESSAGE_INVALID_FILE_NAME;

        String args = " " + PREFIX_NOTE_FILE_NAME + "invalidFileName!";
        thrown.expect(ParseException.class);
        thrown.expectMessage(expectedMessage);
        parser.parse(args);
    }

    @Test
    public void parse_prefixNotPresent_throwsParseException() throws ParseException {
        String expectedMessage = String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, NoteExportCommand.MESSAGE_USAGE);

        String args = "";
        thrown.expect(ParseException.class);
        thrown.expectMessage(expectedMessage);
        parser.parse(args);
    }
}
