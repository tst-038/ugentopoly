package be.ugent.objprog.ugentopoly.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class GameLogBook {
    private final Node logbookRoot;
    private final ObservableList<Log> entries;

    public GameLogBook(Node logbookRoot) {
        this.logbookRoot = logbookRoot;
        entries = FXCollections.observableArrayList();
    }

    public ObservableList<Log> getEntries() {
        return entries;
    }

    public void addEntry(Log entry) {
        entries.add(entry);
    }

    public void toggleLogbookVisibility() {
        logbookRoot.setVisible(!logbookRoot.isVisible());
    }
}