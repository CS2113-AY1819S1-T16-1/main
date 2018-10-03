package seedu.address.model.classroom;

import static java.util.Objects.requireNonNull;

/**
 * Represents a module code in the app.
 */
public class ClassModule {
    public String value;

    public ClassModule() {
    }

    public ClassModule(String moduleCode) {
        requireNonNull(moduleCode);
        value = moduleCode;
    }
    @Override
    public String toString() {
        return value;
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassModule // instanceof handles nulls
                && value.equals(((ClassModule) other).value)); // state check
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
