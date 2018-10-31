package seedu.address.testutil;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULE_CODE = "CS2113";
    public static final String DEFAULT_MODULE_NAME = "Software Engineering & Objected-Oriented Programming";

    private ModuleCode moduleCode;
    private ModuleName moduleName;

    public ModuleBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        moduleName = new ModuleName(DEFAULT_MODULE_NAME);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code toCopy}
     */
    public ModuleBuilder(Module toCopy) {
        moduleCode = toCopy.getModuleCode();
        moduleName = toCopy.getModuleName();
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code ModuleName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleName(String moduleName) {
        this.moduleName = new ModuleName(moduleName);
        return this;
    }

    public Module build() {
        return new Module(moduleCode, moduleName);
    }
}
