package seedu.address.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_START_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a date in a note.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class NoteDate {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final List<DateTimeFormatter> DATE_FORMATTER_LIST =
            new ArrayList<>(Arrays.asList(
                    DateTimeFormatter.ofPattern("d-M-yyyy"),
                    DateTimeFormatter.ofPattern("d/M/yyyy"),
                    DateTimeFormatter.ofPattern("d.M.yyyy"),
                    new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d-MMM-yyyy").toFormatter(),
                    new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d MMM yyyy").toFormatter(),
                    new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d-MMM-yy").toFormatter(),
                    new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d MMM yy").toFormatter()
            ));

    public static final String MESSAGE_START_DATE_MISSING_FIELD =
            "The START_DATE field is missing. Please provide the missing field. "
                    + "[" + PREFIX_NOTE_START_DATE + "START_DATE]";

    private LocalDate date;

    public NoteDate(String date) {
        requireNonNull(date);

        int matchIndex = findMatchingDateFormat(date);
        checkArgument((matchIndex != -1), MESSAGE_INVALID_DATE_FORMAT);

        this.date = LocalDate.parse(date, DATE_FORMATTER_LIST.get(matchIndex));
    }

    /**
     * Returns true if the input date is valid.
     * A valid date should follow the pre-defined date formats
     * or an empty string.
     */
    public static boolean isValidDate(String date) {
        requireNonNull(date);
        if (date.isEmpty()) {
            return true;
        }
        int index = findMatchingDateFormat(date);
        return (index != -1);
    }

    /**
     * Determines if the input date string matches any of the accepted
     * date formats in {@code DATE_FORMATTER_LIST}.
     *
     * @param date
     * @return index of the matching pattern or -1 if no matches found
     */
    public static int findMatchingDateFormat(String date) {
        int foundMatchIndex = -1;
        for (DateTimeFormatter formatter : DATE_FORMATTER_LIST) {
            try {
                LocalDate.parse(date, formatter);
                foundMatchIndex = DATE_FORMATTER_LIST.indexOf(formatter);
            } catch (DateTimeParseException dte) {
                // do nothing, intentional
            }
        }
        return foundMatchIndex;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        if (date == null) {
            return "";
        }
        return date.format(DATE_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteDate // instanceof handles nulls
                && date.equals(((NoteDate) other).date)); // state check
    }
}
