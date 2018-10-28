package seedu.address.storage.adapter;

import java.util.ArrayList;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.classroom.ClassName;
import seedu.address.model.classroom.Classroom;
import seedu.address.model.classroom.Enrollment;
import seedu.address.model.module.ModuleCode;

/**
 * JAXB-friendly adapted version of the Classroom.
 */
@XmlRootElement(name = "class")
public class XmlAdaptedClassroom {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Classroom's %s field is missing!";

    //class-specific fields
    @XmlElement(name = "className", required = true, nillable = true)
    private String className;
    @XmlElement(name = "moduleCode", required = true, nillable = true)
    private String moduleCode;
    @XmlElement(name = "maxEnrollment", required = true, nillable = true)
    private String maxEnrollment;
    @XmlElementWrapper(name = "students")
    @XmlElement(name = "matricNo", required = true, nillable = true)
    private ArrayList<String> studentList;

    /**
     * Constructs an XmlAdaptedClassroom.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedClassroom() {
    }

    /**
     * Constructs an {@code XmlAdaptedClassroom} with the given classroom details
     */
    public XmlAdaptedClassroom(String className, String moduleCode, String maxEnrollment,
                               ArrayList<String> studentsList) {
        this.className = className;
        this.moduleCode = moduleCode;
        this.maxEnrollment = maxEnrollment;
        this.studentList = studentsList;
    }

    /**
     * Converts a Classroom into an {@code XmlAdaptedClassroom} for JAXB use
     */
    public XmlAdaptedClassroom(Classroom classroom) {
        this.className = classroom.getClassName().getValue();
        this.moduleCode = classroom.getModuleCode().moduleCode;
        this.maxEnrollment = classroom.getMaxEnrollment().getValue();
        this.studentList = classroom.getStudents();
    }

    /**
     * Converts this XmlAdaptedClassroom into the model's Classroom object
     */
    public Classroom toModelType() throws IllegalValueException {
        if (className == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClassName.class.getSimpleName()));
        }
        if (!ClassName.isValidClassName(className)) {
            throw new IllegalValueException(ClassName.MESSAGE_CLASSNAME_CONSTRAINTS);
        }
        final ClassName modelClassName = new ClassName(className);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_MODULE_CODE_CONSTRAINT);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        if (maxEnrollment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Enrollment.class.getSimpleName()));
        }
        if (!Enrollment.isValidEnrollment(maxEnrollment)) {
            throw new IllegalValueException(Enrollment.MESSAGE_ENROLLMENT_CONSTRAINTS);
        }
        final Enrollment modelEnrollment = new Enrollment(maxEnrollment);
        Classroom classroom = new Classroom(modelClassName, modelModuleCode, modelEnrollment);
        classroom.setStudents(studentList);
        return classroom;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedClassroom)) {
            return false;
        }

        XmlAdaptedClassroom otherClassroom = (XmlAdaptedClassroom) other;
        return Objects.equals(className, otherClassroom.className)
                && Objects.equals(moduleCode, otherClassroom.moduleCode)
                && Objects.equals(maxEnrollment, otherClassroom.maxEnrollment);
    }
}
