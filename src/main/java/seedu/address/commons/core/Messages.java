package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date format!\n"
            + "Please follow any of these valid date formats:\n"
            + "1) dd-MM-yyyy\n"
            + "2) dd/MM-yyyy\n"
            + "3) dd.MM.yyyy\n"
            + "4) dd-MMM-yyyy\n"
            + "5) dd MMM yyyy\n"
            + "6) dd-MMM-yy\n"
            + "7) dd MMM yy";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_TIME_FORMAT = "Invalid time format!\n"
            + "Please enter a time in the 12-hour format (hh:mm AM/PM). Example: 12:45 PM";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_WELCOME = "Welcome to Trajectory.";

}
