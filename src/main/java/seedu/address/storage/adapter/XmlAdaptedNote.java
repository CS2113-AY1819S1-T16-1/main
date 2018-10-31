package seedu.address.storage.adapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteDate;
import seedu.address.model.note.NoteLocation;
import seedu.address.model.note.NoteText;
import seedu.address.model.note.NoteTime;
import seedu.address.model.note.NoteTitle;

/**
 * JAXB-friendly adapted version of the Note.
 */
@XmlRootElement(name = "note")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlAdaptedNote {

    @XmlElement(name = "moduleCode", required = true, nillable = true)
    private String moduleCode;

    @XmlElement(name = "title", required = true, nillable = true)
    private String title;

    @XmlElement(name = "startDate", required = true, nillable = true)
    private String startDate;

    @XmlElement(name = "startTime", required = true, nillable = true)
    private String startTime;

    @XmlElement(name = "endDate", required = true, nillable = true)
    private String endDate;

    @XmlElement(name = "endTime", required = true, nillable = true)
    private String endTime;

    @XmlElement(name = "location", required = true, nillable = true)
    private String location;

    @XmlElement(name = "noteText", required = true, nillable = true)
    private String noteText;

    /**
     * Constructs an XmlAdaptedNote.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedNote() { }

    /**
     * Constructs an {@code XmlAdaptedNote} with the given note details
     */
    public XmlAdaptedNote(
            String moduleCode,
            String title,
            String startDate,
            String startTime,
            String endDate,
            String endTime,
            String location,
            String noteText) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.location = location;
        this.noteText = noteText;
    }

    /**
     * Converts a Note into an {@code XmlAdaptedNote} for JAXB use
     */
    public XmlAdaptedNote(Note note) {
        this.moduleCode = note.getModuleCode().toString();
        this.title = note.getTitle().toString();
        this.startDate = (note.getStartDate() == null) ? "" : note.getStartDate().toString();
        this.startTime = note.getStartTime().toString();
        this.endDate = (note.getEndDate() == null) ? "" : note.getEndDate().toString();
        this.endTime = note.getEndTime().toString();
        this.location = note.getLocation().toString();
        this.noteText = note.getNoteText().toString();
    }

    /**
     * Converts this XmlAdaptedNote into the model's Note object
     */
    public Note toModelType() {
        return new Note(
                new ModuleCode(moduleCode),
                new NoteTitle(title), (!startDate.trim().isEmpty())
                ? new NoteDate(startDate) : null,
                new NoteTime(startTime), (!endDate.trim().isEmpty())
                ? new NoteDate(endDate) : null,
                new NoteTime(endTime),
                new NoteLocation(location),
                new NoteText(noteText)
        );
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public String getTitle() {
        return this.title;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getLocation() {
        return this.location;
    }

    public String getNoteText() {
        return this.noteText;
    }
}
