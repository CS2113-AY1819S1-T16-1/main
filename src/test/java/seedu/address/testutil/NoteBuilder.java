package seedu.address.testutil;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteDate;
import seedu.address.model.note.NoteLocation;
import seedu.address.model.note.NoteText;
import seedu.address.model.note.NoteTime;
import seedu.address.model.note.NoteTitle;

/**
 * Creates dummy notes for testing.
 */
public class NoteBuilder {
    public static final String DEFAULT_MODULE_CODE = "CS2113";
    public static final String DEFAULT_TITLE = "My note";
    public static final String DEFAULT_START_DATE = "21-12-2113";
    public static final String DEFAULT_START_TIME = "10:00 AM";
    public static final String DEFAULT_END_DATE = "22-12-2113";
    public static final String DEFAULT_END_TIME = "10:00 AM";
    public static final String DEFAULT_LOCATION = "National University of Singapore";
    public static final String DEFAULT_NOTE_TEXT = "CS2113 Rocks!";

    private ModuleCode moduleCode;
    private NoteTitle title;
    private NoteDate startDate;
    private NoteTime startTime;
    private NoteDate endDate;
    private NoteTime endTime;
    private NoteLocation location;
    private NoteText noteText;

    /**
     * Empty constructor for NoteBuilder that initializes the
     * note with default values.
     */
    public NoteBuilder() {
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        title = new NoteTitle(DEFAULT_TITLE);
        startDate = new NoteDate(DEFAULT_START_DATE);
        startTime = new NoteTime(DEFAULT_START_TIME);
        endDate = new NoteDate(DEFAULT_END_DATE);
        endTime = new NoteTime(DEFAULT_END_TIME);
        location = new NoteLocation(DEFAULT_LOCATION);
        noteText = new NoteText(DEFAULT_NOTE_TEXT);
    }

    /**
     * Initializes the note with given parameters.
     */
    public NoteBuilder(String moduleCode,
                       String title,
                       String startDate,
                       String startTime,
                       String endDate,
                       String endTime,
                       String location,
                       String noteText) {
        this.moduleCode = new ModuleCode(moduleCode);
        this.title = new NoteTitle(title);
        this.startDate = new NoteDate(startDate);
        this.startTime = new NoteTime(startTime);
        this.endDate = new NoteDate(endDate);
        this.endTime = new NoteTime(endTime);
        this.location = new NoteLocation(location);
        this.noteText = new NoteText(noteText);
    }

    /**
     * Initializes the note with the data from {@code noteToCopy}.
     */
    public NoteBuilder(Note noteToCopy) {
        this.moduleCode = noteToCopy.getModuleCode();
        this.title = noteToCopy.getTitle();
        this.startDate = noteToCopy.getStartDate();
        this.startTime = noteToCopy.getStartTime();
        this.endDate = noteToCopy.getEndDate();
        this.endTime = noteToCopy.getEndTime();
        this.location = noteToCopy.getLocation();
        this.noteText = noteToCopy.getNoteText();
    }

    /**
     * Sets the {@code moduleCode} of the {@code Note} that we are building.
     */
    public NoteBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code title} of the {@code Note} that we are building.
     */
    public NoteBuilder withTitle(String title) {
        this.title = new NoteTitle(title);
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code Note} to null.
     */
    public NoteBuilder withNullStartDate() {
        this.startDate = null;
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code Note} to null.
     */
    public NoteBuilder withNullEndDate() {
        this.endDate = null;
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code Note} that we are building.
     */
    public NoteBuilder withStartDate(String startDate) {
        this.startDate = new NoteDate(startDate);
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Note} that we are building.
     */
    public NoteBuilder withStartTime(String startTime) {
        this.startTime = new NoteTime(startTime);
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code Note} that we are building.
     */
    public NoteBuilder withEndDate(String endDate) {
        this.endDate = new NoteDate(endDate);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Note} that we are building.
     */
    public NoteBuilder withEndTime(String endTime) {
        this.endTime = new NoteTime(endTime);
        return this;
    }

    /**
     * Sets the {@code location} of the {@code Note} that we are building.
     */
    public NoteBuilder withLocation(String location) {
        this.location = new NoteLocation(location);
        return this;
    }

    /**
     * Sets the {@code noteText} of the {@code Note} that we are building.
     */
    public NoteBuilder withNoteText(String noteText) {
        this.noteText = new NoteText(noteText);
        return this;
    }

    /**
     * Creates a note from the data in the NoteBuilder object.
     *
     * @return Note object
     */
    public Note build() {
        return new Note(
                moduleCode,
                title,
                startDate,
                startTime,
                endDate,
                endTime,
                location,
                noteText
        );
    }
}
