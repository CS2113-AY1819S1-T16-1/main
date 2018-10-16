package seedu.address.model.gradebook;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a component in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
@XmlRootElement(name = "gradebook")
@XmlAccessorType(XmlAccessType.FIELD)
public class GradebookComponent {

    @XmlElement(name = "moduleCode", required = true, nillable = true)
    private String moduleCode;
    @XmlElement(name = "gradeItemName", required = true, nillable = true)
    private String gradeItemName;

    public GradebookComponent() {

    }

    public GradebookComponent(String moduleCode, String gradeItemName) {
        this.moduleCode = moduleCode;
        this.gradeItemName = gradeItemName;
    }

    public String getGradeItemName() {
        return gradeItemName;
    }

    public void setGradeItemName(String gradeItemName) {
        this.gradeItemName = gradeItemName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
}
