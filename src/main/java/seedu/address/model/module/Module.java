package seedu.address.model.module;

import java.util.ArrayList;

import seedu.address.model.person.Person;

/**
 * Represents a module in Trajectory
 */
public class Module {
    private ModuleCode moduleCode;
    private ModuleName moduleName;
    private ArrayList<Person> students;

    public Module(ModuleCode moduleCode, ModuleName moduleName) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.students = new ArrayList<>();
    }

    public ModuleCode getModuleCode() {
        return this.moduleCode;
    }

    public ModuleName getModuleName() {
        return this.moduleName;
    }

    public ArrayList<Person> getEnrolledStudents() {
        return this.students;
    }

    public void addStudent(Person student) {
        this.students.add(student);
    }

    public void removeStudent(Person student) {
        this.students.remove(student);
    }

    /**
     * Returns true if both modules have the same module code and name.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModuleCode().equals(getModuleCode())
                && otherModule.getModuleName().equals(getModuleName());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Module Code: ")
                .append(getModuleCode())
                .append(" Module Name: ")
                .append(getModuleName());
        return builder.toString();
    }

    /**
     * Returns true if both modules have the same module code and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getModuleCode().equals((getModuleCode()))
                && otherModule.getModuleName().equals(getModuleName());
    }
}
