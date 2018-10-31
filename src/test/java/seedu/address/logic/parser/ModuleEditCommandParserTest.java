package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2040C;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_MA1508E;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_CS2040C;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_MA1508E;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_MA1508E;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CS2040C;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_MA1508E;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;
import seedu.address.logic.commands.ModuleEditCommand;
import seedu.address.logic.commands.ModuleEditCommand.EditModuleDescriptor;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class ModuleEditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleEditCommand.MESSAGE_USAGE);

    private static final ModuleEditCommandParser parser = new ModuleEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no module code specified
        assertParseFailure(parser, MODULE_NAME_DESC_CS2040C, MESSAGE_INVALID_FORMAT);

        // no fields specified
        assertParseFailure(parser, MODULE_CODE_DESC_CS2040C, ModuleEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module name
        assertParseFailure(parser, MODULE_CODE_DESC_CS2040C + INVALID_MODULE_NAME_DESC,
                ModuleName.MESSAGE_MODULE_NAME_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, MODULE_CODE_DESC_CS2040C + INVALID_MODULE_NAME_DESC
                + INVALID_MODULE_NAME_DESC, ModuleName.MESSAGE_MODULE_NAME_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = MODULE_CODE_DESC_MA1508E + MODULE_NAME_DESC_MA1508E;
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptorBuilder()
                .withModuleCode(VALID_MODULE_CODE_MA1508E)
                .withModuleName(VALID_MODULE_NAME_MA1508E).build();
        ModuleEditCommand expectedCommand = new ModuleEditCommand(VALID_MODULE_CODE_MA1508E, editModuleDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_repeatedFields_acceptsLast() {
        String userInput = MODULE_CODE_DESC_MA1508E + MODULE_NAME_DESC_MA1508E + MODULE_NAME_DESC_MA1508E
                + MODULE_NAME_DESC_CS2040C;
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptorBuilder()
                .withModuleCode(VALID_MODULE_CODE_MA1508E)
                .withModuleName(VALID_MODULE_NAME_CS2040C).build();
        ModuleEditCommand expectedCommand = new ModuleEditCommand(VALID_MODULE_CODE_MA1508E, editModuleDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = MODULE_CODE_DESC_MA1508E + INVALID_MODULE_NAME_DESC + MODULE_NAME_DESC_CS2040C;
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptorBuilder()
                .withModuleCode(VALID_MODULE_CODE_MA1508E)
                .withModuleName(VALID_MODULE_NAME_CS2040C).build();
        ModuleEditCommand expectedCommand = new ModuleEditCommand(VALID_MODULE_CODE_MA1508E, editModuleDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
