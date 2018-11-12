package seedu.address.logic.commands;

import java.util.HashSet;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.StorageController;
import seedu.address.model.classroom.Attendance;
import seedu.address.model.classroom.ClassName;
import seedu.address.model.classroom.Classroom;
import seedu.address.model.classroom.ClassroomManager;
import seedu.address.model.classroom.Enrollment;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseCode;
import seedu.address.model.course.CourseManager;
import seedu.address.model.course.CourseName;
import seedu.address.model.course.FacultyName;
import seedu.address.model.gradebook.Gradebook;
import seedu.address.model.gradebook.GradebookManager;
import seedu.address.model.grades.Grades;
import seedu.address.model.grades.GradesManager;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleManager;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNo;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.student.StudentManager;
import seedu.address.model.user.User;
import seedu.address.model.user.UserManager;
import seedu.address.ui.HtmlTableProcessor;


/**
 * This class is designed for debug use only.
 */
public class DebugCommand extends Command {
    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        StringBuilder sb = new StringBuilder();

        ModuleManager moduleManager = ModuleManager.getInstance();
        ClassroomManager classroomManager = ClassroomManager.getInstance();
        GradebookManager gradebookManager = new GradebookManager();
        GradesManager gradesManager = new GradesManager();

        StorageController.wipeAllProductionData();
        StorageController.createFiles();
        CourseManager.getInstance().clearAll();
        StudentManager.getInstance().initializeModel(model);
        moduleManager.clearModules();
        model.resetData(new AddressBook());
        model.commitAddressBook();

        UserManager.getInstance().addUser(new User("defaultUser", "password", 1));
        UserManager.getInstance().saveUserList();

        CourseManager.getInstance().addCourse(new Course(new CourseCode("CEG"),
                new CourseName("Computer Engineering"), new FacultyName("Faculty of Engineering")));
        CourseManager.getInstance().addCourse(new Course(new CourseCode("MECHENG"),
                new CourseName("Mechanical Engineering"), new FacultyName("Faculty of Engineering")));
        CourseManager.getInstance().addCourse(new Course(new CourseCode("EEE"),
                new CourseName("Electrical Engineering"), new FacultyName("Faculty of Engineering")));
        CourseManager.getInstance().addCourse(new Course(new CourseCode("CS"),
                new CourseName("Computer Science"), new FacultyName("School of Computing")));
        CourseManager.getInstance().addCourse(new Course(new CourseCode("IS"),
                new CourseName("Information Systems"), new FacultyName("School of Computing")));
        CourseManager.getInstance().addCourse(new Course(new CourseCode("INFOSEC"),
                new CourseName("Information Security"), new FacultyName("School of Computing")));
        CourseManager.getInstance().addCourse(new Course(new CourseCode("BA"),
                new CourseName("Business Analytics"), new FacultyName("School of Computing")));
        CourseManager.getInstance().addCourse(new Course(new CourseCode("POLSCI"),
                new CourseName("Political Science"), new FacultyName("Faculty of Arts and Social Sciences")));
        CourseManager.getInstance().saveCourseList();

        model.addPerson(new Person(new Name("Megan Nicole"), new Phone("91234567"),
                new Email("megannicole@u.nus.edu"), new Address("1 Park Avenue"),
                new HashSet<>(), new CourseCode("CEG"), new MatricNo("A0168372L")));
        model.addPerson(new Person(new Name("James Bond"), new Phone("98367527"),
                new Email("jamesbond@u.nus.edu"), new Address("Blk 211 Bedok Avenue 3"),
                new HashSet<>(), new CourseCode("CEG"), new MatricNo("A0166371K")));
        model.addPerson(new Person(new Name("Natalie Shimmie"), new Phone("81112222"),
                new Email("natalie_s@u.nus.edu"), new Address("Blk 92 Punggol"),
                new HashSet<>(), new CourseCode("EEE"), new MatricNo("A0196361C")));
        model.commitAddressBook();

        moduleManager.addModule(new Module(new ModuleCode("CG1111"),
                new ModuleName("Engineering Principles and Practices I")));
        moduleManager.addModule(new Module(new ModuleCode("IS3150"),
                new ModuleName("Digital Media Marketing")));
        moduleManager.addModule(new Module(new ModuleCode("NM2213"),
                new ModuleName("Introduction to Human-Computer Interaction Design")));
        moduleManager.addModule(new Module(new ModuleCode("CS1010"),
                new ModuleName("Programming Methodology")));
        moduleManager.addModule(new Module(new ModuleCode("CS2040C"),
                new ModuleName("Data Structures and Algorithms")));
        moduleManager.addModule(new Module(new ModuleCode("CS2113"),
                new ModuleName("Software Engineering & Object-Oriented Programming")));
        moduleManager.addModule(new Module(new ModuleCode("MA1508E"),
                new ModuleName("Linear Algebra for Engineering")));
        moduleManager.addModule(new Module(new ModuleCode("GEQ1000"),
                new ModuleName("Asking Questions")));
        moduleManager.addModule(new Module(new ModuleCode("GER1000"),
                new ModuleName("Quantitative Reasoning")));
        moduleManager.addModule(new Module(new ModuleCode("MA1511"),
                new ModuleName("Engineering Calculus")));
        moduleManager.saveModuleList();

        Module module = moduleManager.getModuleByModuleCode("MA1508E");
        module.addStudent(StudentManager.getInstance().retrieveStudentByMatricNo("A0168372L"));
        module.addStudent(StudentManager.getInstance().retrieveStudentByMatricNo("A0166371K"));
        module.addStudent(StudentManager.getInstance().retrieveStudentByMatricNo("A0196361C"));
        Module module2 = moduleManager.getModuleByModuleCode("CS2113");
        module2.addStudent(StudentManager.getInstance().retrieveStudentByMatricNo("A0168372L"));
        module2.addStudent(StudentManager.getInstance().retrieveStudentByMatricNo("A0166371K"));
        Module module3 = moduleManager.getModuleByModuleCode("GEQ1000");
        module3.addStudent(StudentManager.getInstance().retrieveStudentByMatricNo("A0166371K"));
        Module module4 = moduleManager.getModuleByModuleCode("CS1010");
        module4.addStudent(StudentManager.getInstance().retrieveStudentByMatricNo("A0196361C"));

        classroomManager.clearClassrooms();
        Classroom classroom1 = new Classroom(new ClassName("ALL"),
                new ModuleCode("MA1508E"), new Enrollment("400"));
        Classroom classroom2 = new Classroom(new ClassName("T16"),
                new ModuleCode("CS2113"), new Enrollment("20"));
        Classroom classroom3 = new Classroom(new ClassName("D11"),
                new ModuleCode("GEQ1000"), new Enrollment("15"));
        Classroom classroom4 = new Classroom(new ClassName("F01"),
                new ModuleCode("CS1010"), new Enrollment("1"));
        classroomManager.addClassroom(classroom1);
        classroomManager.addClassroom(classroom2);
        classroomManager.addClassroom(classroom3);
        classroomManager.addClassroom(classroom4);
        classroomManager.assignStudent(classroom1, "A0168372L"); //MA1508E
        classroomManager.assignStudent(classroom1, "A0166371K"); //MA1508E
        classroomManager.assignStudent(classroom1, "A0196361C"); //MA1508E
        classroomManager.assignStudent(classroom2, "A0168372L"); //CS2113
        classroomManager.assignStudent(classroom2, "A0166371K"); //CS2113
        classroomManager.assignStudent(classroom3, "A0168372K"); //GEQ1000
        classroomManager.assignStudent(classroom4, "A0196361C"); //CS1010

        classroomManager.markStudentAttendance(classroom1, new Attendance("12-11-2018"), "A0168372L");
        classroomManager.markStudentAttendance(classroom1, new Attendance("12-11-2018"), "A0166371K");
        classroomManager.markStudentAttendance(classroom1, new Attendance("12-11-2018"), "A0196361C");
        classroomManager.markStudentAttendance(classroom1, new Attendance("14-11-2018"), "A0168372L");
        classroomManager.markStudentAttendance(classroom1, new Attendance("14-11-2018"), "A0166371K");
        classroomManager.markStudentAttendance(classroom1, new Attendance("14-11-2018"), "A0196361C");

        classroomManager.markStudentAttendance(classroom2, new Attendance("09-11-2018"), "A0168372L");
        classroomManager.markStudentAttendance(classroom3, new Attendance("11-11-2018"), "A0168372K");

        //Nat will be absent for class
        Attendance specialAttendance = new Attendance();
        classroomManager.markStudentAttendance(classroom4, specialAttendance, "A0168372L");
        classroomManager.modifyStudentAttendance(classroom4, specialAttendance, "A0168372L");


        classroomManager.saveClassroomList();

        //populated data for Gradebook
        gradebookManager.clearGradebook();
        Gradebook gradebook = new Gradebook("CS2113", "Assignment 1", 100, 50);
        Gradebook gradebook1 = new Gradebook("GEQ1000", "Finals", 50, 20);
        Gradebook gradebook2 = new Gradebook("MA1508E", "Mid Term", 30, 10);
        Gradebook gradebook3 = new Gradebook("CS1010", "Practical Exam", 60, 30);

        gradebookManager.addGradebookComponent(gradebook);
        gradebookManager.addGradebookComponent(gradebook1);
        gradebookManager.addGradebookComponent(gradebook2);
        gradebookManager.addGradebookComponent(gradebook3);
        gradebookManager.saveGradebookList();

        //populated data for Gradebook
        gradesManager.clearGrade();
        Grades grades = new Grades("CS2113", "Assignment 1", "A0168372L", 50);
        Grades grades1 = new Grades("CS2113", "Assignment 1", "A0166371K", 20);

        gradesManager.addGrade(grades);
        gradesManager.addGrade(grades1);
        gradesManager.saveGradeList();






        return new CommandResult("DEBUG COMMAND EXECUTED. ",
                HtmlTableProcessor.getH1Representation("Populated data set."));
    }
}
