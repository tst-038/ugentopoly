package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.log.event.Event;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;

public class GameLogBook {
    private final Node logbookRoot;
    private final ObservableList<Event> entries;

    public GameLogBook(Node logbookRoot, TableView<Event> tableView) {
        this.logbookRoot = logbookRoot;
        entries = FXCollections.observableArrayList();
        entries.addListener((ListChangeListener<Event>) change -> {
            Platform.runLater(() -> tableView.scrollTo(entries.size()-1));
        });
    }

    public ObservableList<Event> getEntries() {
        return entries;
    }

    public void addEntry(Event entry) {
        entries.add(entry);
    }

    public void toggleLogbookVisibility() {
        logbookRoot.setVisible(!logbookRoot.isVisible());
    }
}