package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAXENROLLMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.StorageController;
import seedu.address.model.classroom.Classroom;

/**
 * Creates a class for a module.
 */
public class ClassCreateCommand extends Command {
    public static final String COMMAND_WORD = "classcreate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a class and assigns it to a module"
            + " for the system. "
            + "Parameters: "
            + PREFIX_CLASSNAME + "CLASS_NAME "
            + PREFIX_MODULECODE + "MODULE_NAME "
            + PREFIX_MAXENROLLMENT + "ENROLLMENT_SIZE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASSNAME + "T16 "
            + PREFIX_MODULECODE + "CG1111 "
            + PREFIX_MAXENROLLMENT + "20";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Class create command not implemented yet.";
    public static final String MESSAGE_SUCCESS = "New class added: %1$s";

    public static final String MESSAGE_ARGUMENTS = "Class name: %1$s, ClassModule code: %2$s, Enrollment size: %3$s";

    private final Classroom classToCreate;

    /**
     * Command creates a classroom to be added.
     */
    public ClassCreateCommand(Classroom classRoom) {
        requireAllNonNull(classRoom);
        this.classToCreate = classRoom;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model   {@code Model} which the command should operate on.
     * @param history {@code CommandHistory} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        StorageController.retrieveData();
        StorageController.getClassesStorage().add(new Classroom(classToCreate.getClassName(),
                classToCreate.getModuleCode(), classToCreate.getMaxEnrollment()));
        StorageController.storeData();
        return new CommandResult(String.format(MESSAGE_SUCCESS, classToCreate.getClassName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassCreateCommand // instanceof handles nulls
                && classToCreate.equals(((ClassCreateCommand) other).classToCreate));

    }
}
