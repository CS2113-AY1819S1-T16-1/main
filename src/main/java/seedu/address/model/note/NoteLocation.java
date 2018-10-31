package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

/**
 * Represents a location in a note.
 */
public class NoteLocation {

    private final String location;

    public NoteLocation(String location) {
        requireNonNull(location);
        this.location = location;
    }

    @Override
    public String toString() {
        return this.location;
    }
}
