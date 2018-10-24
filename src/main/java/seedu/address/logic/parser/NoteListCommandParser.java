package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.NoteListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new NoteListCommand object
 */
public class NoteListCommandParser implements Parser<NoteListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the NoteListCommand
     * and returns a NoteListCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public NoteListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE);

        if (arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE)) {
            String moduleCode = argMultimap.getValue(PREFIX_MODULE_CODE).get();
            return new NoteListCommand(moduleCode);
        } else if (args.trim().isEmpty()) {
            return new NoteListCommand("");
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteListCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
