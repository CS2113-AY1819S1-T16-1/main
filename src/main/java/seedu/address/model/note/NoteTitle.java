package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

/**
 * Represents a title in a note.
 */
public class NoteTitle {

    private final String title;

    public NoteTitle(String title) {
        requireNonNull(title);
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
