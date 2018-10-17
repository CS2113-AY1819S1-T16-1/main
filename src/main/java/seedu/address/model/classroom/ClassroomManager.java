package seedu.address.model.classroom;

import java.util.ArrayList;
import java.util.stream.Collectors;

import seedu.address.model.StorageController;
import seedu.address.storage.adapter.XmlAdaptedClassroom;

/**
 * This classroom manager stores classrooms for Trajectory.
 */
public class ClassroomManager {
    private ArrayList<Classroom> classroomList = new ArrayList<>();

    public ClassroomManager() {
        readClassroomList();
    }

    /**
     * Adds a new classroomList to the in-memory array list
     */
    public void addClassroom(Classroom classroom) {
        classroomList.add(classroom);
    }

    /**
     * Finds a classroom from classroomList
     */
    public Classroom findClassroom(String className, String moduleCode) {
        for (Classroom classroom : classroomList) {
            if (classroom.getClassName().getValue().equalsIgnoreCase(className)
                    && classroom.getModuleCode().getValue().equalsIgnoreCase(moduleCode)) {
                return classroom;
            }
        }
        return null;
    }

    /**
     * Removes a classroom from classroomList
     */
    public void deleteClassroom(Classroom classroom) {
        classroomList.remove(classroom);
    }

    /**
     * Gets the classroom list from storage and converts it to a Classroom array list
     */
    private void readClassroomList() {
        ArrayList<XmlAdaptedClassroom> xmlClassroomList = StorageController.getClassesStorage();
        for (XmlAdaptedClassroom xmlClassroom : xmlClassroomList) {
            classroomList.add(xmlClassroom.toModelType());
        }
    }

    /**
     * Converts the classroom array list and invokes the StorageController to save the current classroom list to file
     */
    public void saveClassroomList() {
        ArrayList<XmlAdaptedClassroom> xmlClassroomList =
                classroomList
                        .stream()
                        .map(XmlAdaptedClassroom::new)
                        .collect(Collectors.toCollection(ArrayList::new));
        StorageController.setClassesStorage(xmlClassroomList);
        StorageController.storeData();
    }

    public ArrayList<Classroom> getClassroomList() {
        return this.classroomList;
    }

    public void setClassroomList(ArrayList<Classroom> classroomList) {
        this.classroomList = classroomList;
    }
}
