package be.ugent.objprog.ugentopoly.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameLogBook {

    private static GameLogBook instance;
    private ObservableList<Log> entries;

    private GameLogBook() {
        entries = FXCollections.observableArrayList();
    }

    public static GameLogBook getInstance() {
        if (instance == null) {
            instance = new GameLogBook();
        }
        return instance;
    }

    public ObservableList<Log> getEntries() {
        return entries;
    }

    public void addEntry(Log entry){
        entries.add(entry);
    }


}
