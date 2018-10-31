package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleManager;
import seedu.address.model.module.exceptions.DuplicateModuleException;

/**
 * Adds a module to Trajectory
 */
public class ModuleAddCommand extends Command {

    public static final String COMMAND_WORD = "module add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module into Trajectory. "
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_MODULE_NAME + "MODULE_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2113 "
            + PREFIX_MODULE_NAME + "Software Engineering & Object-Oriented Programming";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s %2$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in Trajectory.";

    private final Module moduleToAdd;

    public ModuleAddCommand(Module module) {
        this.moduleToAdd = module;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        try {
            ModuleManager moduleManager = ModuleManager.getInstance();
            moduleManager.addModule(moduleToAdd);
            moduleManager.saveModuleList();
        } catch (DuplicateModuleException dme) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE, dme);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleToAdd.getModuleCode(),
                moduleToAdd.getModuleName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleAddCommand // instanceof handles nulls
                && moduleToAdd.equals(((ModuleAddCommand) other).moduleToAdd));
    }
}
