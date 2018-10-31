package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.CourseManager;
import seedu.address.model.person.Person;
import seedu.address.model.student.StudentManager;

/**
 * Adds a person to the address book.
 */
public class StudentAddCommand extends Command {

    public static final String COMMAND_WORD = "student add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student into Trajectory. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MATRIC + "MATRIC_NO "
            + PREFIX_COURSE_CODE + "COURSE_CODE "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Megan Nicole "
            + PREFIX_MATRIC + "A0168000B "
            + PREFIX_COURSE_CODE + "CEG "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 ";


    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in Trajectory";
    public static final String MESSAGE_NO_SUCH_COURSE = "The course you specified cannot be found";

    private final Person toAdd;

    /**
     * Creates an StudentAddCommand to add the specified {@code Person}
     */
    public StudentAddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd) || StudentManager.getInstance()
                .doesStudentExistForGivenMatricNo(toAdd.getMatricNo().matricNo)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        if (!CourseManager.getInstance().hasCourse(toAdd.getCourseCode().courseCode)) {
            throw new CommandException(MESSAGE_NO_SUCH_COURSE);
        }

        model.addPerson(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentAddCommand // instanceof handles nulls
                && toAdd.equals(((StudentAddCommand) other).toAdd));
    }
}
