package be.ugent.objprog.ugentopoly.ui;

import javafx.scene.Group;

public class LogbookManager {
    private static LogbookManager instance;
    private Group logbookRoot;

    private LogbookManager(Group logbookRoot) {
        this.logbookRoot = logbookRoot;
    }

    public static LogbookManager getInstance(Group logbookRoot) {
        if (instance == null) {
            instance = new LogbookManager(logbookRoot);
        }
        return instance;
    }

    public void toggleLogbookVisibility() {
        logbookRoot.setVisible(!logbookRoot.isVisible());
    }
}