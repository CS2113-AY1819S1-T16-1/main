package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;

import org.junit.Test;

public class ClassDeleteCommandTest {

    @Test
    public void equals() {
        final String className = "T16";
        final String moduleCode = "CG1111";
        final ClassDeleteCommand standardCommand = new ClassDeleteCommand(className, moduleCode);
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
    }
}
