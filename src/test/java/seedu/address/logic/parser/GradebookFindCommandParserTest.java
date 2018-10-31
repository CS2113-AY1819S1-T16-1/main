package seedu.address.logic.parser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.logic.commands.GradebookFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static org.junit.Assert.assertNotNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEBOOK_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.GradebookFindCommandParser.MESSAGE_EMPTY_INPUTS;

public class GradebookFindCommandParserTest {
    private GradebookFindCommandParser parser = new GradebookFindCommandParser();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void parse_invalidFormat_throwsParseException() throws ParseException {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradebookFindCommand.MESSAGE_USAGE);

        //invalid arguments found
        String arg = "this is an invalid format";

        thrown.expect(ParseException.class);
        thrown.expectMessage(expectedMessage);
        parser.parse(arg);
    }

    @Test
    public void parse_validArgs_success() throws ParseException {
        String moduleCode = "CS2113";
        String gradebookComponentName = "Finals";
        String args = " "
                + PREFIX_MODULE_CODE
                + moduleCode
                + " "
                + PREFIX_GRADEBOOK_ITEM
                + gradebookComponentName;
        GradebookFindCommand gradebookFindCommand = parser.parse(args);
        assertNotNull(gradebookFindCommand);
    }

    @Test
    public void parse_emptyModuleCodeArgs_throwsParseException() throws ParseException {
        String expectedMessage = String.format(MESSAGE_EMPTY_INPUTS);
        String gradebookComponentName = "Test";

        //module code empty
        String argsWithEmptyModuleCode = " "
                + PREFIX_MODULE_CODE
                + " "
                + PREFIX_GRADEBOOK_ITEM
                + gradebookComponentName;
        thrown.expect(ParseException.class);
        thrown.expectMessage(expectedMessage);
        parser.parse(argsWithEmptyModuleCode);
    }

    @Test
    public void parse_emptyComponentNameArgs_throwsParseException() throws ParseException {
        String expectedMessage = String.format(GradebookFindCommandParser.MESSAGE_EMPTY_INPUTS);
        String moduleCode = "CS2113";

        //component name empty
        String argsWithEmptyComponentName = " "
                + PREFIX_MODULE_CODE
                + moduleCode
                + " "
                + PREFIX_GRADEBOOK_ITEM;
        thrown.expect(ParseException.class);
        thrown.expectMessage(expectedMessage);
        parser.parse(argsWithEmptyComponentName);
    }
}