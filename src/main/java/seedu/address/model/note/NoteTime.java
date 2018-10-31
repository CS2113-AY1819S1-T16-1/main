package seedu.address.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TIME_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * Represents a time in a note.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class NoteTime {

    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("hh:mm a");
    public static final DateTimeFormatter TIME_FORMATTER =
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("h:m a").toFormatter();

    /**
     * Default time to be used if start time is not specified (12:00 AM).
     */
    public static final LocalTime DEFAULT_START_TIME = LocalTime.of(0, 0);

    /**
     * Default time to be used if end time is not specified (11:59 PM).
     */
    public static final LocalTime DEFAULT_END_TIME = LocalTime.of(23, 59);


    private final LocalTime time;

    public NoteTime(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_INVALID_TIME_FORMAT);
        this.time = LocalTime.parse(time, TIME_FORMATTER);
    }

    /**
     * Returns true if the input is a valid time.
     * A valid time should follow the (h:m a) format or
     * an empty string.
     */
    public static boolean isValidTime(String date) {
        requireNonNull(date);
        if (date.isEmpty()) {
            return true;
        }

        try {
            LocalTime.parse(date, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return time.format(TIME_FORMAT);
    }
}
