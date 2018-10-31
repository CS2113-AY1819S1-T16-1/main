package seedu.address.testutil;

import seedu.address.logic.commands.ModuleEditCommand.EditModuleDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;

/**
 * A utility class to help with building EditModuleDescriptor objects
 */
public class EditModuleDescriptorBuilder {

    private EditModuleDescriptor descriptor;

    public EditModuleDescriptorBuilder() {
        descriptor = new EditModuleDescriptor();
    }

    public EditModuleDescriptorBuilder(EditModuleDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Returns an {@code EditModuleDescriptor} with fields containing {@code module}'s details
     */
    public EditModuleDescriptorBuilder(Module module) {
        super();
        descriptor.setModuleCode(module.getModuleCode());
        descriptor.setModuleName(module.getModuleName());
    }

    /**
     * Sets the {@code ModuleCode} of the {@code EditModuleDescriptorBuilder} that we are building.
     */
    public EditModuleDescriptorBuilder withModuleCode(String moduleCode) {
        descriptor.setModuleCode(new ModuleCode(moduleCode));
        return this;
    }

    /**
     * Sets the {@code ModuleName} of the {@code EditModuleDescriptorBuilder} that we are building.
     */
    public EditModuleDescriptorBuilder withModuleName(String moduleName) {
        descriptor.setModuleName(new ModuleName(moduleName));
        return this;
    }

    public EditModuleDescriptor build() {
        return descriptor;
    }
}
