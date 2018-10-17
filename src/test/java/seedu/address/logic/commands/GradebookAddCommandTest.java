package seedu.address.logic.commands;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GradebookAddCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new NoteAddCommand(null);
    }
}
