package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040C;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_MA1508E;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CS2040C;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_MA1508E;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.StorageController;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.ModuleBuilder;

/**
 * Provides test cases for {@code ModuleAddCommand}
 */
public class ModuleAddCommandTest {
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        StorageController.enterTestMode();
    }

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ModuleAddCommand(null);
    }

    @Test
    public void execute_addSuccessful() {
        final ModuleCode moduleCode = new ModuleCode("CS2113");
        final ModuleName moduleName = new ModuleName("Software Engineering");

        assertCommandSuccess(new ModuleAddCommand(new Module(moduleCode, moduleName)), new CommandHistory(),
                String.format(ModuleAddCommand.MESSAGE_SUCCESS, moduleCode.toString(), moduleName.toString()));
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateCourse_throwsCommandException() throws Exception {
        Module validModule = new ModuleBuilder().build();
        ModuleAddCommand addCommand = new ModuleAddCommand(validModule);

        thrown.expect(CommandException.class);
        thrown.expectMessage(String.format(
                ModuleAddCommand.MESSAGE_DUPLICATE_MODULE, validModule.getModuleCode().toString()));
        addCommand.execute(null, commandHistory);
        addCommand.execute(null, commandHistory);
    }

    @Test
    public void equals() {
        Module ma1508e = new ModuleBuilder().withModuleCode(VALID_MODULE_CODE_MA1508E)
                .withModuleName(VALID_MODULE_NAME_MA1508E).build();
        Module cs2040c = new ModuleBuilder().withModuleCode(VALID_MODULE_CODE_CS2040C)
                .withModuleName(VALID_MODULE_NAME_CS2040C).build();

        ModuleAddCommand addMa1508eCommand = new ModuleAddCommand(ma1508e);
        ModuleAddCommand addCs2040cCommand = new ModuleAddCommand(cs2040c);

        assertTrue(addMa1508eCommand.equals(addMa1508eCommand));

        ModuleAddCommand addMa1508eCommandCopy = new ModuleAddCommand(ma1508e);
        assertTrue(addMa1508eCommand.equals(addMa1508eCommandCopy));
        assertFalse(addMa1508eCommand.equals(1));
        assertFalse(addMa1508eCommand.equals(null));
        assertFalse(addMa1508eCommand.equals(addCs2040cCommand));


    }
}
