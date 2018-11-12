package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.classroom.Classroom;
import seedu.address.model.classroom.ClassroomManager;
import seedu.address.model.module.ModuleManager;

/**
 * Lists the attendance for a class in the system.
 */
public class ClassListStudentAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "class listattendance";
    public static final String HTML_TABLE_TITLE_ATTENDANCE = "Attendance list for %1$s, %2$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": list class attendance list for a specified class"
            + " for the system. "
            + "Parameters: "
            + PREFIX_CLASS_NAME + "CLASS_NAME "
            + PREFIX_MODULE_CODE + "MODULE_CODE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASS_NAME + "T16 "
            + PREFIX_MODULE_CODE + "CG1111";

    public static final String MESSAGE_SUCCESS = "Attendance is listed for"
            + " Class: %1$s"
            + ", Module code: %2$s";
    public static final String MESSAGE_FAIL = "Class belonging to module not found!";
    public static final String MESSAGE_MODULE_CODE_INVALID = "Module code does not exist";

    private final String className;
    private final String moduleCode;

    public ClassListStudentAttendanceCommand(String className, String moduleCode) {
        requireAllNonNull(className, moduleCode);
        this.className = className;
        this.moduleCode = moduleCode;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model   {@code Model} which the command should operate on.
     * @param history {@code CommandHistory} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        ClassroomManager classroomManager = ClassroomManager.getInstance();
        ModuleManager moduleManager = ModuleManager.getInstance();

        if (!moduleManager.doesModuleExist(moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_CODE_INVALID);
        }

        Classroom classToListAttendance = classroomManager.findClassroom(className, moduleCode);
        if (classToListAttendance == null) {
            throw new CommandException(MESSAGE_FAIL);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS + "\n", className, moduleCode),
                classroomManager.getClassroomAttendanceHtmlRepresentation(classToListAttendance));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassListStudentAttendanceCommand // instanceof handles nulls
                && className.equals(((ClassListStudentAttendanceCommand) other).className)
                && moduleCode.equals(((ClassListStudentAttendanceCommand) other).moduleCode));

    }
}
