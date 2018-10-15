package seedu.address.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import seedu.address.commons.util.XmlUtil;
import seedu.address.model.classroom.Classroom;
import seedu.address.model.classroom.ClassroomManager;
import seedu.address.model.gradebook.GradebookComponent;
import seedu.address.model.gradebook.GradebookManager;

/**
 * This class is a storage controller for the other datasets that work alongside the main student list.
 */
public class StorageController {

    private static final String BASE_DIRECTORY = "data/";
    private static final String STORAGE_COURSES = BASE_DIRECTORY + "courseList.xml";
    private static final String STORAGE_MODULES = BASE_DIRECTORY + "modules.xml";
    private static final String STORAGE_CLASSES = BASE_DIRECTORY + "classes.xml";
    private static final String STORAGE_GRADEBOOK = BASE_DIRECTORY + "gradebook.xml";

    private static ArrayList<Course> courseStorage = new ArrayList<Course>();
    private static ArrayList<Module> moduleStorage = new ArrayList<Module>();
    private static ArrayList<Classroom> classesStorage = new ArrayList<Classroom>();
    private static ArrayList<GradebookComponent> gradebookStorage = new ArrayList<GradebookComponent>();

    /**
     * This method retrieves all datasets saved locally.
     */
    public static void retrieveData() {
        createFiles();

        try {
            CourseManager cm = XmlUtil.getDataFromFile(Paths.get(STORAGE_COURSES), CourseManager.class);
            courseStorage = cm.getList();

            ModuleManager moduleManager = XmlUtil.getDataFromFile(Paths.get(STORAGE_MODULES), ModuleManager.class);
            moduleStorage = moduleManager.getModules();

            ClassroomManager crm = (ClassroomManager) XmlUtil.getDataFromFile(
                    Paths.get(STORAGE_CLASSES), ClassroomManager.class);
            classesStorage = crm.getList();

            GradebookManager gradeManager = XmlUtil.getDataFromFile(Paths.get(STORAGE_GRADEBOOK),
                    GradebookManager.class);
            gradebookStorage = gradeManager.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates files for all datasets if they do not exist on the local filesystem.
     */
    private static void createFiles() {
        File gradebook = new File(STORAGE_GRADEBOOK);
        File classes = new File(STORAGE_CLASSES);
        File courses = new File(STORAGE_COURSES);
        File modules = new File(STORAGE_MODULES);

        try {
            gradebook.createNewFile();
            classes.createNewFile();
            courses.createNewFile();
            modules.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method stores all data within the arraylists above to local storage.
     */
    public static void storeData() {
        try {
            CourseManager cm = new CourseManager();
            cm.setCourseList(courseStorage);
            XmlUtil.saveDataToFile(Paths.get(STORAGE_COURSES), cm);

            ModuleManager moduleManager = new ModuleManager();
            moduleManager.setModules(moduleStorage);
            XmlUtil.saveDataToFile(Paths.get(STORAGE_MODULES), moduleManager);

            ClassroomManager crm = new ClassroomManager();
            crm.setClassroomList(classesStorage);
            XmlUtil.saveDataToFile(Paths.get(STORAGE_CLASSES), crm);

            GradebookManager gm = new GradebookManager();
            gm.setGradebookComponentList(gradebookStorage);
            XmlUtil.saveDataToFile(Paths.get(STORAGE_GRADEBOOK), gm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Course> getCourseStorage() {
        return courseStorage;
    }

    public static void setCourseStorage(ArrayList<Course> courseStorage) {
        StorageController.courseStorage = courseStorage;
    }

    public static ArrayList<Module> getModuleStorage() {
        return moduleStorage;
    }

    public static void setModuleStorage(ArrayList<Module> moduleStorage) {
        StorageController.moduleStorage = moduleStorage;
    }

    public static ArrayList<Classroom> getClassesStorage() {
        return classesStorage;
    }

    public static void setClassesStorage(ArrayList<Classroom> classesStorage) {
        StorageController.classesStorage = classesStorage;
    }

    public static ArrayList<GradebookComponent> getGradebookStorage() {
        return gradebookStorage;
    }

    public static void setGradebookStorage(ArrayList<GradebookComponent> gradebookStorage) {
        StorageController.gradebookStorage = gradebookStorage;
    }
}
