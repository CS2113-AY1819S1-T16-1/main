package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEBOOK_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.gradebook.GradebookManager;
import seedu.address.storage.adapter.XmlAdaptedGradebook;

/**
 * Deletes gradebook component for module in Trajectory to the user.
 */
public class GradebookDeleteCommand extends Command {
    public static final String COMMAND_WORD = "gradebook delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a gradebook component to Trajectory. "
            + "Parameters: "
            + PREFIX_MODULECODE + "MODULE_CODE  "
            + PREFIX_GRADEBOOK_ITEM + "ITEM "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULECODE + "CS2113 "
            + PREFIX_GRADEBOOK_ITEM + "Assignment 1";

    private final XmlAdaptedGradebook toDeleteGradebookComponent;

    public GradebookDeleteCommand(XmlAdaptedGradebook gradebookComponent) {
        toDeleteGradebookComponent = gradebookComponent;
    }

    @Override
    public CommandResult execute (Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        CommandResult result = GradebookManager.deleteGradebookComponent(toDeleteGradebookComponent.getModuleCode(),
                toDeleteGradebookComponent.getGradeComponentName());
        return result;
    }
}
