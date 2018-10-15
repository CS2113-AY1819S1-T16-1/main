package seedu.address.model.note;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Represents a note data in Trajectory.
 */
@XmlRootElement(name = "note")
@XmlAccessorType(XmlAccessType.FIELD)
public class Note {

    @XmlElement(name = "moduleCode")
    private String moduleCode;

    @XmlElement(name = "date")
    private String date;

    @XmlElement(name = "text")
    private String noteText;

    public Note() {

    }

    public Note(String moduleCode, String date) {
        this.noteText = "";
        this.moduleCode = moduleCode;
        this.date = date;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoteText() {
        return this.noteText;
    }

    public void setNote(String noteText) {
        this.noteText = noteText;
    }
}
