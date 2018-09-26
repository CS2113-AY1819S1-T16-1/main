//@@author m-aslam-mj2
package seedu.address.model.student;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;




/**
 * Represents a student in the LMS.
 *
 */

public class Student extends Person {
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */

    private String courseCode;
    private String matricNo;

    public Student(Name name, Phone phone, Email email, Address address,
                   Set<Tag> tags, String courseCode, String matricNo) {
        super(name, phone, email, address, tags);
        this.courseCode = courseCode;
        this.matricNo = matricNo;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }
}
