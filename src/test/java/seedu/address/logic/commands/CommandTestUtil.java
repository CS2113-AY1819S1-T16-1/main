package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAXENROLLMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_COURSE_CODE_CEG = "CEG";
    public static final String VALID_COURSE_CODE_CS = "CS";
    public static final String VALID_COURSE_NAME_CEG = "Computer Engineering";
    public static final String VALID_COURSE_NAME_CS = "Computer Science";
    public static final String VALID_COURSE_FACULTY_CEG = "Faculty of Engineering";
    public static final String VALID_COURSE_FACULTY_SOC = "School of Computing";
    public static final String VALID_MATRIC_NO_MEGAN = "A0168412C";
    public static final String VALID_MATRIC_NO_TAYLOR = "A0168412D";
    public static final String VALID_CLASS_T16 = "T16";
    public static final String VALID_MAX_ENROLLMENT_20 = "20";
    public static final String VALID_MAX_ENROLLMENT_99 = "99";

    public static final String VALID_MODULE_CODE_CG1111 = "CG1111";
    public static final String VALID_MODULE_CODE_CS2040C = "CS2040C";
    public static final String VALID_MODULE_CODE_MA1508E = "MA1508E";
    public static final String VALID_MODULE_NAME_CS2040C = "Data Structures & Algorithms";
    public static final String VALID_MODULE_NAME_MA1508E = "Linear Algebra for Engineering";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String MATRIC_DESC_MEGAN = " " + PREFIX_MATRIC + VALID_MATRIC_NO_MEGAN;
    public static final String MATRIC_DESC_BOB = " " + PREFIX_MATRIC + VALID_MATRIC_NO_TAYLOR;
    public static final String COURSE_CODE_DESC_CEG = " " + PREFIX_COURSE_CODE + VALID_COURSE_CODE_CEG;
    public static final String COURSE_NAME_DESC_CEG = " " + PREFIX_COURSE_NAME + VALID_COURSE_NAME_CEG;
    public static final String COURSE_FACULTY_DESC_FOE = " " + PREFIX_COURSE_FACULTY + VALID_COURSE_FACULTY_CEG;
    public static final String COURSE_CODE_DESC_CS = " " + PREFIX_COURSE_CODE + VALID_COURSE_CODE_CS;
    public static final String COURSE_NAME_DESC_CS = " " + PREFIX_COURSE_NAME + VALID_COURSE_NAME_CS;
    public static final String COURSE_FACULTY_DESC_SOC = " " + PREFIX_COURSE_FACULTY + VALID_COURSE_FACULTY_SOC;

    public static final String MODULE_CODE_DESC_CG1111 = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_CG1111;
    public static final String MODULE_CODE_DESC_CS2040C = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_CS2040C;
    public static final String MODULE_CODE_DESC_MA1508E = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_MA1508E;
    public static final String MODULE_NAME_DESC_CS2040C = " " + PREFIX_MODULE_NAME + VALID_MODULE_NAME_CS2040C;
    public static final String MODULE_NAME_DESC_MA1508E = " " + PREFIX_MODULE_NAME + VALID_MODULE_NAME_MA1508E;

    public static final String CLASS_NAME_DESC_T16 = " " + PREFIX_CLASS_NAME + VALID_CLASS_T16;
    public static final String CLASS_ENROLLMENT_DESC_20 = " " + PREFIX_MAXENROLLMENT + VALID_MAX_ENROLLMENT_20;
    public static final String CLASS_ENROLLMENT_DESC_99 = " " + PREFIX_MAXENROLLMENT + VALID_MAX_ENROLLMENT_99;

    public static final String INVALID_CLASS_NAME_DESC = " " + PREFIX_CLASS_NAME
            + "T1000"; // more than 3 alphanumeric characters
    public static final String INVALID_CLASS_ENROLLMENT_DESC = " " + PREFIX_MAXENROLLMENT
            + "290E"; // 'e' not allowed in max enrollment size

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_MATRIC_NO = " " + PREFIX_MATRIC + "00000000V"; // missing prefix letter
    public static final String INVALID_COURSE_CODE = " " + PREFIX_COURSE_CODE + "ACE"; // course doesn't exist
    public static final String INVALID_COURSE_CODE_NUMBERS = " "
            + PREFIX_COURSE_CODE + "123"; // course code has numbers

    public static final String INVALID_MODULE_CODE_DESC = " " + PREFIX_MODULE_CODE + "CG2271&"; // '&' not allowed
    public static final String INVALID_MODULE_NAME_DESC = " " + PREFIX_MODULE_NAME + "RTOS@"; // '@' not allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final StudentEditCommand.EditPersonDescriptor DESC_AMY;
    public static final StudentEditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedMessage, result.feedbackToUser);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, CommandHistory actualCommandHistory,
                                            String expectedMessage) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(null, actualCommandHistory);
            assertEquals(expectedMessage, result.feedbackToUser);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book and the filtered person list in the {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Deletes the first person in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstPerson(Model model) {
        Person firstPerson = model.getFilteredPersonList().get(0);
        model.deletePerson(firstPerson);
        model.commitAddressBook();
    }

}
