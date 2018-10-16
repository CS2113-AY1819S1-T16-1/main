package seedu.address.model.gradebook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import seedu.address.commons.util.XmlUtil;
import seedu.address.logic.commands.CommandResult;

/**
 * The API of the GradebookModel component.
 */
public class GradebookModel {
    private static ArrayList<GradebookComponent> gradebookStorage = new ArrayList<GradebookComponent>();
    private static final String STORAGE_GRADEBOOK = "gradebook.xml";

    private static final String ADD_MESSAGE_SUCCESS = "Successfully Added! \nModule Code: %1$s"
            + "\nGradebook Item Name: %2$s";
    private static final String LIST_MESSAGE_SUCCESS = "Success! List of components in the module:";
    private static final String DELETE_MESSAGE_SUCCESS = "Successfully deleted!";
    private static final String DELETE_MESSAGE_FAIL = "Unsuccessful Deletion";

    /**
     * This method retrieves all datasets saved locally.
     */
    public static void retrieveGradebookData() {
        createGradebookFiles();
        try {
            GradebookManager gradeManager = XmlUtil.getDataFromFile(Paths.get(STORAGE_GRADEBOOK),
                    GradebookManager.class);
            gradebookStorage = gradeManager.getGradebookComponentsList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     This method creates files for all datasets if they do not exist on the local filesystem.
     */
    private static void createGradebookFiles() {
        File gradebook = new File(STORAGE_GRADEBOOK);
        try {
            gradebook.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     This method stores all data within the arraylists above to local storage.
     */
    public static void storeGradebookData() {
        try {
            GradebookManager gm = new GradebookManager();
            gm.setGradebookComponentList(gradebookStorage);
            XmlUtil.saveDataToFile(Paths.get(STORAGE_GRADEBOOK), gm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<GradebookComponent> getGradebookStorage() {
        return gradebookStorage;
    }

    public static void setGradebookStorage(ArrayList<GradebookComponent> gradebookStorage) {
        GradebookModel.gradebookStorage = gradebookStorage;
    }

    /**
     This method adds gradebook component to a module in Trajectory.
     */
    public static CommandResult addGradebookComponent (String moduleCode, String gradebookComponentName) {
        retrieveGradebookData();
        getGradebookStorage().add(new GradebookComponent(moduleCode, gradebookComponentName));
        storeGradebookData();
        return new CommandResult(String.format(ADD_MESSAGE_SUCCESS, moduleCode, gradebookComponentName));
    }

    /**
     This method lists gradebook component to a module in Trajectory.
     */
    public static CommandResult listGradebookComponent () {
        retrieveGradebookData();
        StringBuilder sb = new StringBuilder();
        for (GradebookComponent gc: getGradebookStorage()) {
            sb.append("Module Code: ");
            sb.append(gc.getModuleCode() + "\n");
            sb.append("List of Grade Component: ");
            sb.append(gc.getGradeItemName() + "\n");
        }
        return new CommandResult("\n" + LIST_MESSAGE_SUCCESS + "\n" + sb.toString());
    }

    /**
    This method deletes gradebook component to a module in Trajectory.
    */
    public static CommandResult deleteGradebookComponent (String moduleCode, String gradebookComponentName) {
        String status = DELETE_MESSAGE_FAIL;
        for (GradebookComponent gc : getGradebookStorage()) {
            if (gc.getModuleCode().equals(moduleCode) && gc.getGradeItemName().equals(gradebookComponentName)) {
                getGradebookStorage().remove(gc);
                storeGradebookData();
                status = DELETE_MESSAGE_SUCCESS;
                break;
            }
        }
        return new CommandResult("\n" + status);
    }
}
