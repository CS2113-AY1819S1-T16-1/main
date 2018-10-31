package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.StorageController;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.person.Person;
import seedu.address.model.student.StudentManager;
import seedu.address.storage.adapter.XmlAdaptedModule;
import seedu.address.storage.adapter.XmlAdaptedStudentModule;

/**
 * This module manager stores modules for Trajectory.
 */
public class ModuleManager {

    private static final Logger logger = LogsCenter.getLogger(ModuleManager.class);

    private static ModuleManager instance;

    private ArrayList<Module> modules;

    private ModuleManager() {
        modules = new ArrayList<>();
        readModuleList();
    }

    public static ModuleManager getInstance() {
        if (instance == null) {
            instance = new ModuleManager();
        }
        return instance;
    }

    /**
     * Adds a new module to the in-memory array list
     */
    public void addModule(Module module) throws DuplicateModuleException {
        if (doesModuleExist(module)) {
            throw new DuplicateModuleException();
        }
        modules.add(module);
    }

    /**
     * Replaces the given module {@code target} with {@code editedModule}
     * {@code target} must already exist in Trajectory
     */
    public void updateModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        int targetIndex = modules.indexOf(target);

        modules.set(targetIndex, editedModule);
    }

    public void deleteModule(Module toDelete) {
        modules.remove(toDelete);
    }

    public void enrolStudentInModule(Module module, Person student) {
        module.addStudent(student);
    }

    public void removeStudentFromModule(Module module, Person student) {
        module.removeStudent(student);
    }

    public boolean isStudentEnrolledInModule(Module module, Person student) {
        return module.getEnrolledStudents().stream().anyMatch(s -> s.equals(student));
    }

    /**
     * Gets the module list from storage and converts it to a Module array list.
     * Also reads the association data between Student and Module and stores it in-memory in the module.
     */
    private void readModuleList() {
        ArrayList<XmlAdaptedModule> xmlModuleList = StorageController.getModuleStorage();
        ArrayList<XmlAdaptedStudentModule> xmlAdaptedStudentModuleList = StorageController.getStudentModuleStorage();
        try {
            for (XmlAdaptedModule xmlModule : xmlModuleList) {
                Module m = xmlModule.toModelType();
                modules.add(m);

                // Look for associations between this module and any students (i.e. any enrolled students?)
                for (XmlAdaptedStudentModule xmlStudentModule : xmlAdaptedStudentModuleList) {
                    if (xmlStudentModule.getModuleCode().equals(m.getModuleCode().moduleCode)) {
                        Person student = StudentManager.getInstance()
                                .retrieveStudentByMatricNo(xmlStudentModule.getStudentMatricNo());

                        m.addStudent(student);
                    }
                }
            }
        } catch (NullPointerException npe) {
            logger.info("Illegal values found when reading enrolled students: " + npe.getMessage());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found when reading module list: " + ive.getMessage());
        }
    }

    /**
     * Converts the Module array list and invokes the StorageController to save the current module list to file.
     * Also passes info on the association between Student and Module to be saved to file.
     */
    public void saveModuleList() {
        ArrayList<XmlAdaptedModule> xmlAdaptedModules =
                modules.stream().map(XmlAdaptedModule::new).collect(Collectors.toCollection(ArrayList::new));
        StorageController.setModuleStorage(xmlAdaptedModules);

        ArrayList<XmlAdaptedStudentModule> xmlAdaptedStudentModuleList = new ArrayList<>();
        for (Module m : modules) {
            xmlAdaptedStudentModuleList.addAll(
                    m.getEnrolledStudents()
                            .stream()
                            .map(s -> new XmlAdaptedStudentModule(
                                    s.getMatricNo().matricNo, m.getModuleCode().moduleCode))
                            .collect(Collectors.toCollection(ArrayList::new))
            );
        }
        StorageController.setStudentModuleStorage(xmlAdaptedStudentModuleList);

        StorageController.storeData();
    }

    /**
     * Searches the list of modules to find a module that matches the {@code moduleCode}
     * @param moduleCode The target module's code to find
     * @return The module object that matches the module code, or {@code null} if there isn't a matching module
     */
    public Module getModuleByModuleCode(String moduleCode) {
        return this.modules.stream()
                .filter(module -> module.getModuleCode().moduleCode.equals(moduleCode))
                .findAny()
                .orElse(null);
    }

    /**
     * Checks if the input module already exists in Trajectory.
     * @param module The module whose existence needs to be checked.
     * @return True if the module exists; false otherwise.
     */
    public boolean doesModuleExist(Module module) {
        return this.modules.stream().anyMatch(m -> m.isSameModule(module));
    }

    /**
     * Checks if the input module code matches a module that exists in Trajectory.
     * This is an overload to make it easier to check a module's existence without
     * creating a whole {@oode Module} object.
     * This overload adheres to the DRY principle by invoking the original
     * {@link #doesModuleExist(Module)} mathod.
     * @param moduleCode The module code that will be used to check for the module's existence.
     * @return True if the module exists; false otherwise.
     */
    public boolean doesModuleExist(String moduleCode) {
        Module module = getModuleByModuleCode(moduleCode);
        if (module != null) {
            return doesModuleExist(module);
        }
        return false;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }
}
