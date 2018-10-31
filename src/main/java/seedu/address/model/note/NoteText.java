package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

/**
 * Represents the content of a note.
 */
public class NoteText {

    private final String noteText;

    public NoteText(String text) {
        requireNonNull(text);
        this.noteText = text;
    }

    @Override
    public String toString() {
        return this.noteText;
    }
}
