package seedu.address.model.gradebook;

import seedu.address.model.StorageController;

/**
 This class includes all necessary validation for gradebook objects.
 */
public class Gradebook {
    private static final String MESSAGE_FIND_SUCCESS = "Successfully found!";
    private static final String MESSAGE_ERROR_EMPTY = "Module code and gradebook component name cannot be empty";
    private static final String MESSAGE_ADD_SUCCESS = "Successfully Added! \nModule Code: %1$s"
            + "\nGradebook Component Name: %2$s" + "\nMaximum Marks: %3$s" + "\nWeightage: %4$s";
    private static final String MESSAGE_ERROR_DUPLICATE = "Gradebook component already exist in Trajectory";
    private String moduleCode;
    private String gradebookComponentName;
    private int gradebookMaxMarks;
    private int gradebookWeightage;

    public Gradebook(String moduleCode, String gradebookComponentName,
                     int gradebookMaxMarks, int gradebookWeightage) {
        this.moduleCode = moduleCode;
        this.gradebookComponentName = gradebookComponentName;
        this.gradebookMaxMarks = gradebookMaxMarks;
        this.gradebookWeightage = gradebookWeightage;
    }

    /**
     This method checks if user inputs empty values for module code or gradebook component name.
     */
    public static boolean isEmptyParams (String moduleCode, String gradebookComponentName) {
        boolean empty = false;
        if (moduleCode.equals("") || gradebookComponentName.equals("")) {
            empty = true;
        }
        return empty;
    }

    /**
     This method checks if component already exists in Trajectory.
     */
    public static boolean isDuplicateComponent (String moduleCode, String gradebookComponentName) {
        StorageController.retrieveData();
        boolean duplicate = false;
        if (GradebookManager.findGradebookComponent(moduleCode, gradebookComponentName)
                .feedbackToUser.contains(MESSAGE_FIND_SUCCESS)) {
            duplicate = true;
        }
        return duplicate;
    }

    /**
     This method checks all validations before adding to Trajectory.
     */
    public static String checkValidation (String moduleCode, String gradebookComponentName,
                                          int gradebookMaxMarks, int gradebookWeightage) {
        boolean empty = isEmptyParams(moduleCode, gradebookComponentName);
        boolean duplicate = isDuplicateComponent(moduleCode, gradebookComponentName);

        String status = MESSAGE_ADD_SUCCESS;

        if (empty) {
            status = MESSAGE_ERROR_EMPTY;
        }

        if (duplicate) {
            status = MESSAGE_ERROR_DUPLICATE;
        }

        return String.format(status,
                moduleCode,
                gradebookComponentName,
                gradebookMaxMarks,
                gradebookWeightage);
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public String getGradeComponentName() {
        return this.gradebookComponentName;
    }

    public int getGradeComponentMaxMarks() {
        return this.gradebookMaxMarks;
    }

    public int getGradeComponentWeightage() {
        return this.gradebookWeightage;
    }


}
