package seedu.address.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.note.Note;
import seedu.address.storage.StorageHandler;

/**
 * Represents the in-memory model of the Note data.
 */
@XmlRootElement(name = "notes")
public class NoteManager {

    private static ArrayList<Note> notes = new ArrayList<Note>();

    @XmlElement(name = "note")
    private static ObservableList<Note> observableNotesList = FXCollections.observableArrayList();

    private static StorageHandler storage = new StorageHandler();

    public NoteManager() {

    }

    /**
     * Adds the new note data to the ArrayList and ObservableList.
     *
     * @param note
     */
    public void addNote(Note note) {
        notes.add(note);
        observableNotesList.add(note);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotesList(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ObservableList<Note> getObservableNotesList() {
        return observableNotesList;
    }

    /**
     * Write the current notes dataset to an XML file.
     */
    public void saveDataToFile() {
        storage.saveNoteData();
    }

    /**
     * Converts the notes XML file to the model representation for Note.
     * Loads the model object to NoteManager object
     */
    public void getDataFromFile() {
        storage.retrieveNoteData();
    }
}
