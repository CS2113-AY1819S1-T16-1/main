package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.ModuleAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;

/**
 * Parses input arguments and creates a new ModuleAddCommand object.
 */
public class ModuleAddCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ModuleAddCommand
     * and returns a ModuleAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ModuleAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_NAME, PREFIX_MODULE_CODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_NAME, PREFIX_MODULE_CODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleAddCommand.MESSAGE_USAGE));
        }

        String moduleName = argMultimap.getValue(PREFIX_MODULE_NAME).get();
        String moduleCode = argMultimap.getValue(PREFIX_MODULE_CODE).get();

        Module module = new Module(moduleName, moduleCode);
        return new ModuleAddCommand(module);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
