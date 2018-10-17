package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.classroom.Classroom;
import seedu.address.model.classroom.ClassroomManager;
import seedu.address.ui.HtmlTableProcessor;

/**
 * Lists all all the class from the classlist.
 */
public class ClassListCommand extends Command {

    public static final String COMMAND_WORD = "class list";

    public static final String MESSAGE_SUCCESS = "Listed all classes";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        ClassroomManager classroomManager = new ClassroomManager();

        final StringBuilder builder = new StringBuilder();

        builder.append(HtmlTableProcessor.getH3Representation("Class List"));
        builder.append(HtmlTableProcessor.renderTableStart(new ArrayList<String>(
                Arrays.asList("Class Name", "Module Code", "Max Enrollment Size"))));

        builder.append(HtmlTableProcessor.getTableItemStart());

        for (Classroom c : classroomManager.getClassroomList()) {
            builder.append(c.toString());
        }

        builder.append(HtmlTableProcessor.getTableItemEnd());
        return new CommandResult(MESSAGE_SUCCESS + "\n", builder.toString());
    }
}
