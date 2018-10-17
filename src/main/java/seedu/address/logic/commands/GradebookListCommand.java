package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.gradebook.GradebookManager;

/**
 * Lists all gradebook components for module in Trajectory to the user.
 */
public class GradebookListCommand extends Command {

    public static final String COMMAND_WORD = "gradebook list";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        CommandResult result = GradebookManager.listGradebookComponent();
        return result;
    }
}

