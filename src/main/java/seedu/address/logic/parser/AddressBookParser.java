package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.ClassCreateCommand;
import seedu.address.logic.commands.ClassListCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CourseAddCommand;
import seedu.address.logic.commands.CourseListCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GradebookAddCommand;
import seedu.address.logic.commands.GradebookDeleteCommand;
import seedu.address.logic.commands.GradebookFindCommand;
import seedu.address.logic.commands.GradebookListCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ModuleAddCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Used to separate multiple command words and args
     */
    private static final Pattern ADVANCED_COMMAND_FORMAT =
            Pattern.compile("(?<commandWords>.*?\\S+((?<=find)|(?=(?: [0-9]| [a-z]\\/))|$))(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = ADVANCED_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWords");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case GradebookAddCommand.COMMAND_WORD:
            return new GradebookAddCommandParser().parse(arguments);

        case GradebookListCommand.COMMAND_WORD:
            return new GradebookListCommand();

        case GradebookDeleteCommand.COMMAND_WORD:
            return new GradebookDeleteCommandParser().parse(arguments);

        case GradebookFindCommand.COMMAND_WORD:
            return new GradebookFindCommandParser().parse(arguments);

        case CourseAddCommand.COMMAND_WORD:
            return new CourseAddCommandParser().parse(arguments);

        case CourseListCommand.COMMAND_WORD:
            return new CourseListCommand();

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ModuleAddCommand.COMMAND_WORD:
            return new ModuleAddCommandParser().parse(arguments);

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ClassCreateCommand.COMMAND_WORD:
            return new ClassCreateCommandParser().parse(arguments);

        case ClassListCommand.COMMAND_WORD:
            return new ClassListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
