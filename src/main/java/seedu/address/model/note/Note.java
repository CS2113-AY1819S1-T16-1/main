package seedu.address.model.note;

import java.time.LocalDateTime;

import seedu.address.model.module.ModuleCode;

/**
 * Represents a note in Trajectory.
 */
public class Note {

    private ModuleCode moduleCode;
    private NoteTitle title;
    private NoteDate startDate;
    private NoteTime startTime;
    private NoteDate endDate;
    private NoteTime endTime;
    private NoteLocation location;
    private NoteText noteText;


    public Note(ModuleCode moduleCode,
                NoteTitle title,
                NoteDate startDate,
                NoteTime startTime,
                NoteDate endDate,
                NoteTime endTime,
                NoteLocation location,
                NoteText noteText) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.location = location;
        this.noteText = noteText;
    }

    // Module Code
    public ModuleCode getModuleCode() {
        return this.moduleCode;
    }

    public void setModuleCode(ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
    }

    // Title
    public NoteTitle getTitle() {
        return this.title;
    }

    public void setTitle(NoteTitle title) {
        this.title = title;
    }

    // Start Date
    public NoteDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(NoteDate startDate) {
        this.startDate = startDate;
    }

    // Start Time
    public NoteTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(NoteTime startTime) {
        this.startTime = startTime;
    }

    // End Date
    public NoteDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(NoteDate endDate) {
        this.endDate = endDate;
    }

    // End Time
    public NoteTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(NoteTime endTime) {
        this.endTime = endTime;
    }

    // Location
    public NoteLocation getLocation() {
        return this.location;
    }

    public void setLocation(NoteLocation location) {
        this.location = location;
    }

    // Note Text
    public NoteText getNoteText() {
        return this.noteText;
    }

    public void setNoteText(NoteText noteText) {
        this.noteText = noteText;
    }

    /**
     * Combines LocalDate and LocalTime into LocalDateTime.
     *
     * @return LocalDateTime object of start date and time
     */
    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(this.startDate.getDate(), this.startTime.getTime());
    }

    /**
     * Combines LocalDate and LocalTime into LocalDateTime.
     *
     * @return LocalDateTime object of end date and time
     */
    public LocalDateTime getEndDateTime() {
        return LocalDateTime.of(this.endDate.getDate(), this.endTime.getTime());
    }
}
