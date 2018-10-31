package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import seedu.address.model.note.Note;
import seedu.address.model.note.NoteText;

/**
 * This is the controller for the NoteEntryPromptWindow FXML.
 * It consists of a TextArea for the user to enter input in multiple lines.
 */
public class NoteEntryPrompt {

    private static final String NOTE_TAB_OVERWRITE = "    ";
    private static final String NOTE_TEXT_EMPTY_MESSAGE = "Saving failed! The field is empty.";
    private final KeyCombination keyCombinationSave = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCombinationCancel = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);

    @FXML
    private TextArea noteContent;

    @FXML
    private Label feedbackLabel;

    private Stage dialogStage;
    private Note note;

    private boolean feedbackLabelIsDisplayed = false;
    private boolean isCancelled = false;

    public NoteEntryPrompt() { }

    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Populates the TextArea with the editable note.
     *
     * @param note
     */
    public void setNote(Note note) {
        this.note = note;

        noteContent.setText(note.getNoteText().toString());
        noteContent.setPromptText("Enter note here.");
    }

    /**
     * Handles the key press event, {@code keyEvent}.
     *
     * Changes the behaviour of TAB key to add four spaces instead.
     *
     * Key combination CTRL+S will save the note if the TextArea is not blank,
     * otherwise, displays an error message.
     *
     * Key combination CTRL+Q will close the window without saving the note.
     */
    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.TAB) {
            String tempStr = noteContent.getText();

            tempStr = tempStr.substring(0, tempStr.length() - 1);

            noteContent.setText(tempStr);
            noteContent.appendText(NOTE_TAB_OVERWRITE);
        }

        if (feedbackLabelIsDisplayed && !keyEvent.getCode().isModifierKey()) {
            feedbackLabel.setText("");
            feedbackLabelIsDisplayed = false;
        }

        if (keyCombinationSave.match(keyEvent)) {
            saveAndCloseWindow();
        }

        if (keyCombinationCancel.match(keyEvent)) {
            closeWindow();
        }
    }

    /**
     * Saves the note and closes the window.
     */
    public void saveAndCloseWindow() {
        String tempNote = noteContent.getText();

        if (!tempNote.trim().isEmpty()) {
            note.setNoteText(new NoteText(tempNote));
            dialogStage.close();
        } else {
            displayFeedbackLabel();
            feedbackLabelIsDisplayed = true;
        }
    }

    private void displayFeedbackLabel() {
        feedbackLabel.setStyle("-fx-font-size: 12; -fx-text-fill: red;");
        feedbackLabel.setText(NOTE_TEXT_EMPTY_MESSAGE);
    }

    /**
     * Closes the window without saving the note data.
     */
    public void closeWindow() {
        isCancelled = true;
        dialogStage.close();
    }

    /**
     * Returns true if the user pressed the key combination to cancel.
     * Otherwise, return false.
     *
     * @return value of isCancelled.
     */
    public boolean isCancelled() {
        return isCancelled;
    }
}

