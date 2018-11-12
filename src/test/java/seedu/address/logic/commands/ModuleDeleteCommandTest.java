package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StorageController;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleManager;
import seedu.address.testutil.ModuleBuilder;

/**
 * Provides test cases for {@code ModuleDeleteCommand}
 */
public class ModuleDeleteCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        StorageController.enterTestMode();
    }

    @Test
    public void constructor_nullCourse_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ModuleDeleteCommand(null);
    }

    @Test
    public void execute_deleteSuccessful() {
        Module validModule = new ModuleBuilder().build();
        ModuleManager.getInstance().addModule(validModule);
        ModuleManager.getInstance().saveModuleList();

        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }
}
