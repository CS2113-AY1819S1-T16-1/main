package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2040C;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_MA1508E;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_CS2040C;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_MA1508E;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040C;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CS2040C;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.CS2040C;

import org.junit.Test;
import seedu.address.logic.commands.ModuleAddCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains unit tests for {@code ModuleAddCommandParser}
 */
public class ModuleAddCommandParserTest {
    private ModuleAddCommandParser parser = new ModuleAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(CS2040C).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_CODE_DESC_CS2040C + MODULE_NAME_DESC_CS2040C,
                new ModuleAddCommand(expectedModule));

        // multiple module codes - last module code accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_MA1508E + MODULE_CODE_DESC_CS2040C
                + MODULE_NAME_DESC_CS2040C, new ModuleAddCommand(expectedModule));

        // multiple module names - last module name accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS2040C + MODULE_NAME_DESC_MA1508E
                + MODULE_NAME_DESC_CS2040C, new ModuleAddCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleAddCommand.MESSAGE_USAGE);

        // module code prefix missing
        assertParseFailure(parser, VALID_MODULE_CODE_CS2040C + MODULE_NAME_DESC_CS2040C, expectedMessage);

        // module name prefix missing
        assertParseFailure(parser, MODULE_CODE_DESC_CS2040C + VALID_MODULE_NAME_CS2040C, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_MODULE_CODE_CS2040C + VALID_MODULE_NAME_CS2040C, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module code
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC + MODULE_NAME_DESC_CS2040C,
                ModuleCode.MESSAGE_MODULE_CODE_CONSTRAINT);

        // invalid module name
        assertParseFailure(parser, MODULE_CODE_DESC_CS2040C + INVALID_MODULE_NAME_DESC,
                ModuleName.MESSAGE_MODULE_NAME_CONSTRAINTS);

        // non-empty preable
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MODULE_CODE_DESC_CS2040C + MODULE_NAME_DESC_CS2040C,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleAddCommand.MESSAGE_USAGE));
    }
}
