package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleManager;
import seedu.address.model.module.ModuleName;

/**
 * Edits the details of an existing module in Trajectory
 */
public class ModuleEditCommand extends Command {

    public static final String COMMAND_WORD = "module edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the module identified "
            + "by its module code. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + "[" + PREFIX_MODULE_NAME + "MODULE_NAME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2113 "
            + PREFIX_MODULE_NAME + "SE & OOP";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_MODULE_NOT_FOUND = "Module with code %s doesn't exist in Trajectory!";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in Trajectory.";

    private final String moduleCode;
    private final EditModuleDescriptor editModuleDescriptor;

    public ModuleEditCommand(String moduleCode, EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(editModuleDescriptor);

        this.moduleCode = moduleCode;
        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        ModuleManager moduleManager = ModuleManager.getInstance();
        Module moduleToEdit = moduleManager.getModuleByModuleCode(moduleCode);
        if (moduleToEdit == null) {
            throw new CommandException(String.format(MESSAGE_MODULE_NOT_FOUND, moduleCode));
        }
        Module editedModule = createEditedModule(moduleToEdit, editModuleDescriptor);

        moduleManager.updateModule(moduleToEdit, editedModule);
        moduleManager.saveModuleList();

        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, moduleToEdit));
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Module createEditedModule(Module moduleToEdit, EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;

        ModuleCode moduleCode = moduleToEdit.getModuleCode();
        ModuleName moduleName = editModuleDescriptor.getModuleName().orElse(moduleToEdit.getModuleName());

        return new Module(moduleCode, moduleName);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleEditCommand)) {
            return false;
        }

        // state check
        ModuleEditCommand e = (ModuleEditCommand) other;
        return moduleCode.equals(e.moduleCode)
                && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value (other than the module code)
     * will replace the corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        private ModuleCode moduleCode;
        private ModuleName moduleName;

        public EditModuleDescriptor() { }

        /**
         * Copy constructor
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setModuleCode(toCopy.moduleCode);
            setModuleName(toCopy.moduleName);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleName);
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<ModuleName> getModuleName() {
            return Optional.ofNullable(moduleName);
        }

        public void setModuleName(ModuleName moduleName) {
            this.moduleName = moduleName;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditModuleDescriptor)) {
                return false;
            }

            // state check
            EditModuleDescriptor e = (EditModuleDescriptor) other;
            return getModuleCode().equals(e.getModuleCode())
                    && getModuleName().equals(e.getModuleName());
        }
    }
}
