package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;

import java.util.stream.Stream;

import seedu.address.logic.commands.ClassDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClassDeleteCommand object
 */
public class ClassDeleteCommandParser implements Parser<ClassDeleteCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param args
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ClassDeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLASSNAME, PREFIX_MODULECODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLASSNAME, PREFIX_MODULECODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClassDeleteCommand.MESSAGE_USAGE));
        }

        String className = argMultimap.getValue(PREFIX_CLASSNAME).get();
        String moduleCode = argMultimap.getValue(PREFIX_MODULECODE).get();

        return new ClassDeleteCommand(className, moduleCode);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
