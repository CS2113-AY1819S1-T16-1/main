package seedu.address.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import seedu.address.commons.util.XmlUtil;
import seedu.address.model.NoteManager;


/**
 * Handles saving/retrieving in-memory dataset to/from local storage.
 */
public class StorageHandler {

    private static final String DEFAULT_DIRECTORY = "data/";

    private static final String STORAGE_COURSES = DEFAULT_DIRECTORY + "courseList.xml";
    private static final String STORAGE_MODULES = DEFAULT_DIRECTORY + "modules.xml";
    private static final String STORAGE_CLASSES = DEFAULT_DIRECTORY + "classes.xml";
    private static final String STORAGE_GRADEBOOK = DEFAULT_DIRECTORY + "gradebook.xml";
    private static final String STORAGE_NOTES = DEFAULT_DIRECTORY + "notes.xml";

    private static NoteManager noteManager = new NoteManager();

    public StorageHandler() {

    }

    /**
     * This method retrieves the notes dataset from local storage.
     */
    public void retrieveNoteData() {
        createNoteFile();

        try {
            noteManager = XmlUtil.getDataFromFile(Paths.get(STORAGE_NOTES), noteManager.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method stores all data within the notes arraylist to local storage.
     */
    public void saveNoteData() {
        try {
            XmlUtil.saveDataToFile(Paths.get(STORAGE_NOTES), noteManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates an empty note file.
     */
    private void createNoteFile() {
        File filePath = new File(STORAGE_NOTES);

        try {
            filePath.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
