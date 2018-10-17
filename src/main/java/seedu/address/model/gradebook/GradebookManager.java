package seedu.address.model.gradebook;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.StorageController;
import seedu.address.storage.adapter.XmlAdaptedGradebook;

/**
 * The API of the GradebookManager component.
 */
public class GradebookManager {
    private static final String LIST_MESSAGE_SUCCESS = "Success! List of components in the module:";
    private static final String DELETE_MESSAGE_SUCCESS = "Successfully deleted!";
    private static final String DELETE_MESSAGE_FAIL = "Unsuccessful Deletion";
    private static final String FIND_MESSAGE_SUCCESS = "Successfully found!";
    private static final String FIND_MESSAGE_FAIL = "Unsuccessful find";

    /**
     This method adds gradebook component to a module in Trajectory.
     */
    public static String addGradebookComponent (String moduleCode,
                                                String gradebookComponentName,
                                                int gradebookMaxMarks,
                                                int gradebookWeightage) {
        String result = Gradebook.checkValidation(moduleCode, gradebookComponentName, gradebookMaxMarks,
                gradebookWeightage);
        if (result.contains("Successfully")) {
            StorageController.getGradebookStorage().add(new XmlAdaptedGradebook(moduleCode, gradebookComponentName,
                    gradebookMaxMarks, gradebookWeightage));
            StorageController.storeData();
        }
        return result;
    }

    /**
     This method lists gradebook component to a module in Trajectory.
     */
    public static CommandResult listGradebookComponent () {
        StorageController.retrieveData();
        StringBuilder sb = new StringBuilder();
        for (XmlAdaptedGradebook gc: StorageController.getGradebookStorage()) {
            sb.append("Module Code: ");
            sb.append(gc.getModuleCode() + "\n");
            sb.append("Grade Component Name: ");
            sb.append(gc.getGradeComponentName() + "\n");
        }
        return new CommandResult("\n" + LIST_MESSAGE_SUCCESS + "\n" + sb.toString());
    }

    /**
    This method deletes gradebook component to a module in Trajectory.
    */
    public static CommandResult deleteGradebookComponent (String moduleCode, String gradebookComponentName) {
        String status = DELETE_MESSAGE_FAIL;
        for (XmlAdaptedGradebook gc : StorageController.getGradebookStorage()) {
            if (gc.getModuleCode().equals(moduleCode) && gc.getGradeComponentName().equals(gradebookComponentName)) {
                StorageController.getGradebookStorage().remove(gc);
                StorageController.storeData();
                status = DELETE_MESSAGE_SUCCESS;
                break;
            }
        }
        return new CommandResult("\n" + status);
    }

    /**
     This method finds gradebook component to a module in Trajectory.
     */
    public static CommandResult findGradebookComponent (String moduleCode, String gradebookComponentName) {
        String status = FIND_MESSAGE_FAIL;
        StorageController.retrieveData();
        StringBuilder sb = new StringBuilder();
        for (XmlAdaptedGradebook gc: StorageController.getGradebookStorage()) {
            if (gc.getModuleCode().equals(moduleCode) && gc.getGradeComponentName().equals(gradebookComponentName)) {
                status = FIND_MESSAGE_SUCCESS;
                sb.append("Module Code: ");
                sb.append(gc.getModuleCode() + "\n");
                sb.append("Grade Component: ");
                sb.append(gc.getGradeComponentName() + "\n");
            }
        }
        return new CommandResult("\n" + status + "\n" + sb.toString());
    }
}
