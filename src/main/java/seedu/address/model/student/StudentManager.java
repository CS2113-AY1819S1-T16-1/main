package seedu.address.model.student;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.Model;
import seedu.address.model.course.CourseManager;
import seedu.address.model.person.Person;
import seedu.address.ui.HtmlTableProcessor;


/**
 * This class represents the model-level layer for student management.
 */
public class StudentManager {

    private static StudentManager initSM = new StudentManager();
    private Model studentModel;

    public String getTableRepresentationForModel(Model model) {
        StringBuilder sb = new StringBuilder();

        int index = 1;
        sb.append(HtmlTableProcessor.getH3Representation("Student List"));
        sb.append(HtmlTableProcessor.renderTableStart(new ArrayList<String>(
                Arrays.asList("Index", "Matric No", "Full Name", "Email Address", "Course Code", "Course Name"))));

        sb.append(HtmlTableProcessor.getTableItemStart());
        for (Person p: model.getFilteredPersonList()) {
            sb.append(HtmlTableProcessor
                    .renderTableItem(new ArrayList<String>(Arrays
                            .asList(index + "", p.getMatricNo().toString(), p.getName().toString(),
                                    p.getEmail().toString(), p.getCourseCode().toString(),
                                    CourseManager.getInstance()
                                            .getCourse(p.getCourseCode().courseCode).getCourseName().courseName))));
            index++;
        }
        sb.append(HtmlTableProcessor.getTableEnd());

        return sb.toString();
    }
    public String getTableRepresentation() {
        StringBuilder sb = new StringBuilder();

        int index = 1;
        sb.append(HtmlTableProcessor.getH3Representation("Student List"));
        sb.append(HtmlTableProcessor.renderTableStart(new ArrayList<String>(
                Arrays.asList("Index", "Matric No", "Full Name",
                        "Phone No", "Email Address", "Course Code", "Course Name"))));

        sb.append(HtmlTableProcessor.getTableItemStart());
        for (Person p: studentModel.getAddressBook().getPersonList()) {
            sb.append(HtmlTableProcessor
                    .renderTableItem(new ArrayList<String>(Arrays
                            .asList(index + "", p.getMatricNo().toString(), p.getName().toString(),
                                    p.getPhone().toString(),
                                    p.getEmail().toString(), p.getCourseCode().toString(),
                                    CourseManager.getInstance()
                                            .getCourse(p.getCourseCode().courseCode).getCourseName().courseName))));
            index++;
        }
        sb.append(HtmlTableProcessor.getTableEnd());

        return sb.toString();
    }

    /**
     * Link StudentManager to initialized Model in MainApp.
     * @param model
     */
    public void initializeModel(Model model) {
        studentModel = model;

    }

    /**
     * Returns the list of students for a given course
     * @param courseCode
     * @return
     */
    public ArrayList<Person> getStudentsInCourse(String courseCode) {
        ArrayList<Person> studList = new ArrayList<Person>();
        for (Person p: studentModel.getAddressBook().getPersonList()) {
            if (p.getCourseCode().toString().equals(courseCode)) {
                studList.add(p);
            }
        }

        return studList;
    }


    public String getStudentsInCourseRepresentation(String courseCode) {
        StringBuilder sb = new StringBuilder();

        sb.append(HtmlTableProcessor.getH4Representation(CourseManager.getInstance()
                .getCourse(courseCode).getCourseName().courseName + " (" + courseCode + ")"));


        if (StudentManager.getInstance().getStudentsInCourse(courseCode).size() == 0) {
            sb.append("<div class=\"alert alert-dismissible alert-info\">\n"
                    + " This course does not have any registered students.\n"
                    + "</div>");
        } else {
            sb.append(HtmlTableProcessor.renderTableStart(new ArrayList<String>(
                    Arrays.asList("Matric No", "Full Name", "Email Address"))));
            for (Person p : studentModel.getAddressBook().getPersonList()) {
                if (p.getCourseCode().toString().equals(courseCode)) {
                    sb.append(HtmlTableProcessor
                            .renderTableItem(new ArrayList<String>(Arrays
                                    .asList(p.getMatricNo().toString(), p.getName().toString(),
                                            p.getEmail().toString()))));
                }
            }
            sb.append(HtmlTableProcessor.getTableEnd());
        }
        return sb.toString();

    }

    public static StudentManager getInstance() {
        if (initSM == null) {
            initSM = new StudentManager();
        }
        return initSM;
    }

    /**
     * Checks whether given matric code belongs to an existing student.
     * @param matricNo
     * @return
     */
    public boolean doesStudentExistForGivenMatricNo(String matricNo) {
        for (Person p: studentModel.getAddressBook().getPersonList()) {
            if (p.getMatricNo().matricNo.equals(matricNo)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether given matric code belongs to an existing student.
     * @param email
     * @return
     */
    public boolean doesStudentExistForGivenEmail(String email) {
        for (Person p:studentModel.getAddressBook().getPersonList()) {
            if (p.getEmail().value.equals(email)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Retrives a student object for a given matriculation no.
     * @param matricNo
     * @return
     */
    public Person retrieveStudentByMatricNo(String matricNo) {
        boolean studentFound = false;
        for (Person p:studentModel.getAddressBook().getPersonList()) {
            if (p.getMatricNo().matricNo.equals(matricNo)) {
                studentFound = true;
                return p;
            }
        }

        if (!studentFound) {
            throw new NullPointerException();
        }

        return null;
    }

    /**
     * Retrives a student object for a given matriculation no.
     * @param email
     * @return
     */
    public Person retrieveStudentByEmail(String email) {
        boolean studentFound = false;
        for (Person p:studentModel.getAddressBook().getPersonList()) {
            if (p.getEmail().value.equals(email)) {
                studentFound = true;
                return p;
            }
        }

        if (!studentFound) {
            throw new NullPointerException();
        }

        return null;
    }
}
