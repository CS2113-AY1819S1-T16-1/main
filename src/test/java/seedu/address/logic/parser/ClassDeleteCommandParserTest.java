package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.address.logic.commands.ClassDeleteCommand;

public class ClassDeleteCommandParserTest {
    private ClassDeleteCommandParser parser = new ClassDeleteCommandParser();

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClassDeleteCommand.MESSAGE_USAGE);
        // no parameters
        assertParseFailure(parser, ClassDeleteCommand.COMMAND_WORD, expectedMessage);
    }
}
