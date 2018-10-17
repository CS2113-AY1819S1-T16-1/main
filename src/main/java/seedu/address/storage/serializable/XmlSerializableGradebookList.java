package seedu.address.storage.serializable;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.storage.adapter.XmlAdaptedGradebook;

/**
 * Represents a list of gradebook components that is serializable to XML format
 */
@XmlRootElement(namespace = "seedu.address.model")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSerializableGradebookList {
    @XmlElementWrapper(name = "gradebook")
    @XmlElement(name = "gradeComponent")
    private ArrayList<XmlAdaptedGradebook> gradebookList = new ArrayList<XmlAdaptedGradebook>();

    public ArrayList<XmlAdaptedGradebook> getGradebookList() {
        return this.gradebookList;
    }
    public void setGradebookList(ArrayList<XmlAdaptedGradebook> gradebookList) {
        this.gradebookList = gradebookList;
    }
}


