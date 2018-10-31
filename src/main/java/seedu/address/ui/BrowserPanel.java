package seedu.address.ui;

import java.net.URL;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.NewInfoMessageEvent;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.note.NoteManager;
import seedu.address.model.person.Person;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "default.html";
    public static final String SEARCH_PAGE_URL =
            "https://se-edu.github.io/addressbook-level4/DummySearchPage.html?name=";

    private static final String FXML = "BrowserPanel.fxml";

    private static boolean noteListIsLoaded = false;

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private WebView browser;

    public BrowserPanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        loadDefaultPage();
        initializeCss();
        initializeWorkerStateListener();
        registerAsAnEventHandler(this);

    }

    private void loadPersonPage(Person person) {
        loadPage(SEARCH_PAGE_URL + person.getName().fullName);
    }

    public void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
        browser.getEngine().loadContent(HtmlTableProcessor
                .renderCard("Welcome to Trajectory. Please login to use the platform."));
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    private void initializeCss() {
        browser.getEngine().setUserStyleSheetLocation(getClass()
                .getResource("/rendering/bootstrap.min.css").toString());
    }

    /**
     * Adds a listener to automatically scroll to
     * the bottom of the page whenever the WebView page fully loads.
     */
    private void initializeWorkerStateListener() {
        browser.getEngine().getLoadWorker().stateProperty()
                .addListener((ObservableValue<? extends Worker.State> observable,
                              Worker.State oldValue, Worker.State newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED) {
                        if (noteListIsLoaded) {
                            browser.getEngine().executeScript("window.scrollTo(0, document.body.scrollHeight);");
                        }
                    }
                });
    }

    public boolean isNoteListLoaded() {
        return noteListIsLoaded;
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonPage(event.getNewSelection());
    }

    @Subscribe
    private void handleNewInfo(NewInfoMessageEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        browser.getEngine().loadContent(event.message);
        if (event.message != null) {
            noteListIsLoaded = event.message.contains(NoteManager.NOTE_PAGE_IDENTIFIER);
        }
    }
}
