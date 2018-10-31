package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TIME_FORMAT;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseCode;
import seedu.address.model.course.CourseName;
import seedu.address.model.course.FacultyName;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.note.NoteDate;
import seedu.address.model.note.NoteTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNo;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    private static final DateTimeFormatter TIME_FORMATTER =
            new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("h:m a").toFormatter();

    private static final List<DateTimeFormatter> DATE_FORMATTER_LIST =
            new ArrayList<>(Arrays.asList(
                    DateTimeFormatter.ofPattern("d-M-yyyy"),
                    DateTimeFormatter.ofPattern("d/M/yyyy"),
                    DateTimeFormatter.ofPattern("d.M.yyyy"),
                    new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d-MMM-yyyy").toFormatter(),
                    new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d MMM yyyy").toFormatter(),
                    new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d-MMM-yy").toFormatter(),
                    new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d MMM yy").toFormatter()
            ));

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_NAME_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_PHONE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_ADDRESS_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String matricNo} into an {@code MatricNo}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code matricNo} is invalid.
     */
    public static MatricNo parseMatric(String matricNo) throws ParseException {
        requireNonNull(matricNo);
        String trimmedMatricNo = matricNo.trim();
        if (!MatricNo.isValidMatricNo(trimmedMatricNo)) {
            throw new ParseException(MatricNo.MESSAGE_MATRIC_NO_CONSTRAINTS);
        }
        return new MatricNo(trimmedMatricNo);
    }

    /**
     * Parses a {@code Collection<String> matricNums} into a {@code Set<MatricNo>}
     */
    public static Set<MatricNo> parseMatricNums(Collection<String> matricNums) throws ParseException {
        requireNonNull(matricNums);
        final Set<MatricNo> matricNoSet = new HashSet<>();
        for (String matricNumber : matricNums) {
            MatricNo matricNo = parseMatric(matricNumber);
            matricNoSet.add(matricNo);
        }
        return matricNoSet;
    }

    /**
     * Parses a {@code String courseName} into an {@code CourseName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code courseName} is invalid.
     */
    public static CourseName parseCourseName(String courseName) throws ParseException {
        requireNonNull(courseName);
        String trimmedCourseName = courseName.trim();
        if (!CourseName.isValidCourseName(trimmedCourseName)) {
            throw new ParseException(CourseName.MESSAGE_COURSE_NAME_CONSTRAINTS);
        }
        return new CourseName(trimmedCourseName);
    }

    /**
     * Parses a {@code String facultyName} into an {@code FacultyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code facultyName} is invalid.
     */
    public static FacultyName parseFacultyName(String facultyName) throws ParseException {
        requireNonNull(facultyName);
        String trimmedFacultyName = facultyName.trim();
        if (!FacultyName.isValidFacultyName(trimmedFacultyName)) {
            throw new ParseException(FacultyName.MESSAGE_COURSE_FACULTY_NAME_CONSTRAINTS);
        }
        return new FacultyName(trimmedFacultyName);
    }

    /**
     * Parses a {@code String courseCode} into an {@code CourseCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code courseCode} is invalid.
     */
    public static CourseCode parseCourseCode(String courseCode) throws ParseException {
        requireNonNull(courseCode);
        String trimmedCourseCode = courseCode.trim();
        if (!CourseCode.isValidCourseCode(trimmedCourseCode)) {
            throw new ParseException(CourseCode.MESSAGE_COURSE_CODE_CONSTRAINTS);
        }
        return new CourseCode(trimmedCourseCode);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_EMAIL_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_TAG_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String moduleCode} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCode} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_MODULE_CODE_CONSTRAINT);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    /**
     * Parses a {@code String moduleName} into a {@code ModuleName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleName} is invalid.
     */
    public static ModuleName parseModuleName(String moduleName) throws ParseException {
        requireNonNull(moduleName);
        String trimmedModuleName = moduleName.trim();
        if (!ModuleName.isValidModuleName(trimmedModuleName)) {
            throw new ParseException(ModuleName.MESSAGE_MODULE_NAME_CONSTRAINTS);
        }
        return new ModuleName(trimmedModuleName);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed and
     * spaces in between non-space characters will be reduced to
     * a single space.
     */
    public static NoteDate parseNoteDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim().replaceAll(" +", " ");
        if (!NoteDate.isValidDate(trimmedDate)) {
            throw new ParseException(MESSAGE_INVALID_DATE_FORMAT);
        }
        return new NoteDate(trimmedDate);
    }

    /**
     * Parses a {@code String time} into a {@code LocalTime}.
     * Leading and trailing whitespaces will be trimmed and
     * spaces in between non-space characters will be reduced to
     * a single space.
     */
    public static NoteTime parseNoteTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim().replaceAll(" +", " ");
        if (!NoteTime.isValidTime(trimmedTime)) {
            throw new ParseException(MESSAGE_INVALID_TIME_FORMAT);
        }
        return new NoteTime(trimmedTime);
    }
}
