package seedu.address.storage.adapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.model.gradebook.Gradebook;

/**
 * JAXB-friendly adapted version of the Gradebook.
 */
@XmlRootElement(name = "gradeComponent")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlAdaptedGradebook {
    @XmlElement(name = "moduleCode", required = true, nillable = true)
    private String moduleCode;
    @XmlElement(name = "gradeComponentName", required = true, nillable = true)
    private String gradeComponentName;
    @XmlElement(name = "gradeComponentMaxMarks", required = true, nillable = true)
    private int gradeComponentMaxMarks;
    @XmlElement(name = "gradeComponentWeightage", required = true, nillable = true)
    private int gradeComponentWeightage;

    /**
     * Constructs an XmlAdaptedModule.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedGradebook() {

    }

    /**
     * Constructs an {@code XmlAdaptedGradebook} with the given gradebook component details
     */
    public XmlAdaptedGradebook(String moduleCode, String gradeComponentName, int gradeComponentMaxMarks,
                               int gradeComponentWeightage) {
        this.moduleCode = moduleCode;
        this.gradeComponentName = gradeComponentName;
        this.gradeComponentMaxMarks = gradeComponentMaxMarks;
        this.gradeComponentWeightage = gradeComponentWeightage;
    }

    /**
     * Constructs an {@code XmlAdaptedGradebook} with the given gradebook component details
     */
    public XmlAdaptedGradebook(Gradebook gradebook) {
        this.moduleCode = gradebook.getModuleCode();
        this.gradeComponentName = gradebook.getGradeComponentName();
        this.gradeComponentMaxMarks = gradebook.getGradeComponentMaxMarks();
        this.gradeComponentWeightage = gradebook.getGradeComponentWeightage();
    }

    /**
     * Constructs an {@code XmlAdaptedGradebook} with the given gradebook componenet details
     */
    public XmlAdaptedGradebook(String moduleCode, String gradeComponentName) {
        this.moduleCode = moduleCode;
        this.gradeComponentName = gradeComponentName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getGradeComponentName() {
        return gradeComponentName;
    }

    public int getGradeComponentMaxMarks() {
        return gradeComponentMaxMarks;
    }

    public int getGradeComponentWeightage() {
        return gradeComponentWeightage;
    }
}
