package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.log.event.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class GameLogBook {
    private final Node logbookRoot;
    private final ObservableList<Event> entries;

    public GameLogBook(Node logbookRoot) {
        this.logbookRoot = logbookRoot;
        entries = FXCollections.observableArrayList();
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