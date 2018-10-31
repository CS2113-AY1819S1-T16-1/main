package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TIME_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_TITLE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.NoteEditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.note.NoteDate;
import seedu.address.model.note.NoteLocation;
import seedu.address.model.note.NoteTime;
import seedu.address.model.note.NoteTitle;

/**
 * Parses input arguments and creates a new NoteEditCommand object
 */
public class NoteEditCommandParser implements Parser<NoteEditCommand> {

    /**
     * Used to separate the index and optional parameters in the arguments.
     */
    private static final Pattern ARGS_FORMAT = Pattern.compile("^\\s*(?<index>\\S+)(?<optionalParams>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the NoteEditCommand
     * and returns a NoteEditCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public NoteEditCommand parse(String args) throws ParseException {
        Matcher matcher = ARGS_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteEditCommand.MESSAGE_USAGE));
        }

        final String index = matcher.group("index");
        final String optionalParams = matcher.group("optionalParams");

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(optionalParams,
                        PREFIX_MODULE_CODE,
                        PREFIX_NOTE_TITLE,
                        PREFIX_NOTE_START_DATE,
                        PREFIX_NOTE_START_TIME,
                        PREFIX_NOTE_END_DATE,
                        PREFIX_NOTE_END_TIME,
                        PREFIX_NOTE_LOCATION);

        if (!index.matches("\\d+") || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteEditCommand.MESSAGE_USAGE));
        }

        ModuleCode moduleCode = null;
        NoteTitle title = null;
        NoteDate startDate = null;
        NoteTime startTime = null;
        NoteDate endDate = null;
        NoteTime endTime = null;
        NoteLocation location = null;

        StringBuilder messageExceptions = new StringBuilder();
        boolean dateFormatErrorFound = false;
        boolean timeFormatErrorFound = false;

        if (argMultimap.getValue(PREFIX_MODULE_CODE).isPresent()) {
            // TODO: Check validity of input moduleCode value
            try {
                moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());
            } catch (ParseException e) {
                e.printStackTrace();
                messageExceptions.append(ModuleCode.MESSAGE_MODULE_CODE_CONSTRAINT);
                messageExceptions.append("\n\n");
            }
        }

        if (argMultimap.getValue(PREFIX_NOTE_TITLE).isPresent()) {
            title = new NoteTitle(argMultimap.getValue(PREFIX_NOTE_TITLE).get());
        }

        if (argMultimap.getValue(PREFIX_NOTE_START_DATE).isPresent()) {
            try {
                startDate = ParserUtil.parseNoteDate(argMultimap.getValue(PREFIX_NOTE_START_DATE).get());
            } catch (ParseException e) {
                e.printStackTrace();
                messageExceptions.append(MESSAGE_INVALID_DATE_FORMAT);
                messageExceptions.append("\n\n");
                dateFormatErrorFound = true;
            }
        }

        if (argMultimap.getValue(PREFIX_NOTE_START_TIME).isPresent()) {
            try {
                startTime = ParserUtil.parseNoteTime(argMultimap.getValue(PREFIX_NOTE_START_TIME).get());
            } catch (ParseException e) {
                e.printStackTrace();
                messageExceptions.append(MESSAGE_INVALID_TIME_FORMAT);
                messageExceptions.append("\n\n");
                timeFormatErrorFound = true;
            }
        }

        if (argMultimap.getValue(PREFIX_NOTE_END_DATE).isPresent()) {
            try {
                endDate = ParserUtil.parseNoteDate(argMultimap.getValue(PREFIX_NOTE_END_DATE).get());
            } catch (ParseException e) {
                e.printStackTrace();
                if (!dateFormatErrorFound) {
                    messageExceptions.append(MESSAGE_INVALID_DATE_FORMAT);
                    messageExceptions.append("\n\n");
                }
            }

        }

        if (argMultimap.getValue(PREFIX_NOTE_END_TIME).isPresent()) {
            try {
                endTime = ParserUtil.parseNoteTime(argMultimap.getValue(PREFIX_NOTE_END_TIME).get());
            } catch (ParseException e) {
                e.printStackTrace();
                if (!timeFormatErrorFound) {
                    messageExceptions.append(MESSAGE_INVALID_TIME_FORMAT);
                    messageExceptions.append("\n\n");
                }
            }
        }

        if (argMultimap.getValue(PREFIX_NOTE_LOCATION).isPresent()) {
            location = new NoteLocation(argMultimap.getValue(PREFIX_NOTE_LOCATION).get());
        }

        if (messageExceptions.length() > 0) {
            throw new ParseException(messageExceptions.substring(0, messageExceptions.length() - 2));
        }

        return new NoteEditCommand(
                Integer.parseInt(index),
                moduleCode,
                title,
                startDate,
                startTime,
                endDate,
                endTime,
                location);
    }
}
