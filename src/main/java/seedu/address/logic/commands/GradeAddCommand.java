package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEBOOK_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_MARKS;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.gradebook.GradebookManager;
import seedu.address.model.grades.Grades;
import seedu.address.model.grades.GradesManager;


/**
 * Adds student grade to Trajectory.
 */
public class GradeAddCommand extends Command {
    public static final String COMMAND_WORD = "grade add";
    public static final String MESSAGE_MARKS_EXCEED = "Marks assigned is above maximum marks %1$s.";
    public static final String MESSAGE_ADD_GRADE_SUCCESS = "\nSuccessfully assigned! \nNumber of grade components: ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds grade to Trajectory. "
            + "\nParameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE  "
            + PREFIX_GRADEBOOK_ITEM + "COMPONENT_NAME "
            + PREFIX_MATRIC + "MATRIC_NO "
            + PREFIX_STUDENT_MARKS + "MARKS "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2113 "
            + PREFIX_GRADEBOOK_ITEM + "Assignment 1 "
            + PREFIX_MATRIC + "A0167789S "
            + PREFIX_STUDENT_MARKS + "50";
    private final Grades toAddGrade;
    public GradeAddCommand(Grades grade) {
        this.toAddGrade = grade;
    }

    @Override
    public CommandResult execute (Model model, CommandHistory history) throws CommandException {
        GradesManager gradesManager = new GradesManager();
        GradebookManager gradebookManager = new GradebookManager();
        boolean hasMarksExceed = gradebookManager.hasMarksExceed(
                toAddGrade.getModuleCode(),
                toAddGrade.getGradeComponentName(),
                toAddGrade.getMarks());
        if (!hasMarksExceed) {
            int maxMarks = gradebookManager.getMaxMarks(toAddGrade.getModuleCode(), toAddGrade.getGradeComponentName());
            return new CommandResult(String.format(MESSAGE_MARKS_EXCEED, maxMarks));
        }
        gradesManager.addGrade(toAddGrade);
        gradesManager.saveGradeList();
        String gradeList = gradesManager.listGrade();
        int size = gradesManager.getGradeSize();
        return new CommandResult(MESSAGE_ADD_GRADE_SUCCESS + size + "\n" + "", gradeList);
//        return new CommandResult(String.format(
//                MESSAGE_ADD_GRADE_SUCCESS,
//                toAddGrade.getModuleCode(),
//                toAddGrade.getGradeComponentName(),
//                toAddGrade.getAdminNo(),
//                toAddGrade.getMarks()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same object
                || (other instanceof GradeAddCommand //instanceof handles nulls
                && toAddGrade.equals(((GradeAddCommand) other).toAddGrade));
    }
}
