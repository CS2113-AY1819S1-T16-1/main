package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEBOOK_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEBOOK_MODULE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.StorageController;
import seedu.address.model.gradebook.GradebookComponent;

/**
 * Deletes gradebook component for module in Trajectory to the user.
 */
public class GradebookDeleteCommand extends Command {
    public static final String COMMAND_WORD = "gradebook delete";
    public static final String MESSAGE_SUCCESS = "Successfully deleted!";
    public static final String MESSAGE_FAIL = "Unsuccessful!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a gradebook component to Trajectory. "
            + "Parameters: "
            + PREFIX_GRADEBOOK_MODULE + "MODULE_CODE  "
            + PREFIX_GRADEBOOK_ITEM + "ITEM "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GRADEBOOK_MODULE + "CS2113 "
            + PREFIX_GRADEBOOK_ITEM + "Assignment 1";

    private final GradebookComponent toDeleteGradebookComponent;

    public GradebookDeleteCommand(GradebookComponent gradebookComponent) {
        toDeleteGradebookComponent = gradebookComponent;
    }

    @Override
    public CommandResult execute (Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        StorageController.retrieveData();
        String result = MESSAGE_FAIL;
        for (GradebookComponent gc : StorageController.getGradebookStorage()) {
            if (gc.getModuleCode().equals(toDeleteGradebookComponent.getModuleCode())) {
                if (gc.getGradeItemName().equals(toDeleteGradebookComponent.getGradeItemName())) {
                    StorageController.getGradebookStorage().remove(gc.getGradeItemName());
                    StorageController.storeData();
                    result = MESSAGE_SUCCESS;
                }
            }
        }
        return new CommandResult("\n" + result);
    }
}
