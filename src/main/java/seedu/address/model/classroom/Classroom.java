package seedu.address.model.classroom;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import seedu.address.ui.HtmlTableProcessor;

/**
 * Represents a classroom in the app.
 */
public class Classroom {
    private ClassName className;
    private ClassModule moduleCode;
    private Enrollment maxEnrollment;

    public Classroom(ClassName className, ClassModule moduleCode, Enrollment maxEnrollment) {
        requireNonNull(className);
        this.className = className;
        this.moduleCode = moduleCode;
        this.maxEnrollment = maxEnrollment;
    }

    public ClassName getClassName() {
        return className;
    }

    public ClassModule getModuleCode() {
        return moduleCode;
    }

    public Enrollment getMaxEnrollment() {
        return maxEnrollment;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(HtmlTableProcessor
                .renderTableItem(new ArrayList<String>(Arrays
                        .asList(className.getValue(),
                                moduleCode.getValue(),
                                maxEnrollment.getValue()))));
        return builder.toString();
    }

    /**
     * Returns true if both classroom have the same class-specific fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Classroom)) {
            return false;
        }

        Classroom otherClassroom = (Classroom) other;
        return otherClassroom.getClassName().equals(getClassName())
                && otherClassroom.getModuleCode().equals(getModuleCode())
                && otherClassroom.getMaxEnrollment().equals(getMaxEnrollment());
    }

    /**
     * Returns true if both classroom have the same class-specific fields.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(className, moduleCode, maxEnrollment);
    }
}
