package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_TITLE;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.NoteAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteDate;
import seedu.address.model.note.NoteLocation;
import seedu.address.model.note.NoteText;
import seedu.address.model.note.NoteTime;
import seedu.address.model.note.NoteTitle;


/**
 * Parses input arguments and creates a new NoteAddCommand object
 */
public class NoteAddCommandParser implements Parser<NoteAddCommand> {

    public static final String MESSAGE_INVALID_DATE_TIME_DIFFERENCE =
            "Invalid input! Please make sure the start date/time is earlier than the end date/time.";

    /**
     * Parses the given {@code String} of arguments in the context of the NoteAddCommand
     * and returns a NoteAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_MODULE_CODE,
                        PREFIX_NOTE_TITLE,
                        PREFIX_NOTE_START_DATE,
                        PREFIX_NOTE_START_TIME,
                        PREFIX_NOTE_END_DATE,
                        PREFIX_NOTE_END_TIME,
                        PREFIX_NOTE_LOCATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteAddCommand.MESSAGE_USAGE));
        }

        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());

        NoteTitle title = new NoteTitle("");
        NoteDate startDate = null;
        NoteTime startTime = new NoteTime(NoteTime.DEFAULT_START_TIME.format(NoteTime.TIME_FORMAT));
        NoteDate endDate = null;
        NoteTime endTime = new NoteTime(NoteTime.DEFAULT_END_TIME.format(NoteTime.TIME_FORMAT));
        NoteLocation location = new NoteLocation("");
        NoteText noteText = new NoteText("");

        if (argMultimap.getValue(PREFIX_NOTE_TITLE).isPresent()) {
            title = new NoteTitle(argMultimap.getValue(PREFIX_NOTE_TITLE).get());
        }

        if (argMultimap.getValue(PREFIX_NOTE_START_DATE).isPresent()) {
            startDate = ParserUtil.parseNoteDate(argMultimap.getValue(PREFIX_NOTE_START_DATE).get());
        }

        if (argMultimap.getValue(PREFIX_NOTE_START_TIME).isPresent()) {
            startTime = ParserUtil.parseNoteTime(argMultimap.getValue(PREFIX_NOTE_START_TIME).get());
            if (startDate == null) {
                throw new ParseException(NoteDate.MESSAGE_START_DATE_MISSING_FIELD);
            }
        }

        if (argMultimap.getValue(PREFIX_NOTE_END_DATE).isPresent()) {
            endDate = ParserUtil.parseNoteDate(argMultimap.getValue(PREFIX_NOTE_END_DATE).get());
        }

        if (argMultimap.getValue(PREFIX_NOTE_END_TIME).isPresent()) {
            endTime = ParserUtil.parseNoteTime(argMultimap.getValue(PREFIX_NOTE_END_TIME).get());
        }

        if (argMultimap.getValue(PREFIX_NOTE_LOCATION).isPresent()) {
            location = new NoteLocation(argMultimap.getValue(PREFIX_NOTE_LOCATION).get());
        }

        if (startDate == null && endDate != null) {
            throw new ParseException(NoteDate.MESSAGE_START_DATE_MISSING_FIELD);
        }

        if (startDate != null && endDate == null) {
            endDate = new NoteDate(startDate.getDate().format(NoteDate.DATE_FORMAT));
        }

        if (startDate == null && argMultimap.getValue(PREFIX_NOTE_END_TIME).isPresent()) {
            throw new ParseException(NoteDate.MESSAGE_START_DATE_MISSING_FIELD);
        }

        if (startDate != null) {
            LocalDateTime start = LocalDateTime.of(startDate.getDate(), startTime.getTime());
            LocalDateTime end = LocalDateTime.of(endDate.getDate(), endTime.getTime());

            // result = 0, equal, valid
            // result > 0, end > start, valid
            // result < 0, end < start, invalid
            int result = end.compareTo(start);
            if (result < 0) {
                throw new ParseException(MESSAGE_INVALID_DATE_TIME_DIFFERENCE);
            }
        }

        Note note = new Note(
                moduleCode,
                title,
                startDate,
                startTime,
                endDate,
                endTime,
                location,
                noteText);

        return new NoteAddCommand(note);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
