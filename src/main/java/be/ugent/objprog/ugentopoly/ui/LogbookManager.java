package be.ugent.objprog.ugentopoly.ui;

import javafx.scene.Group;

public class LogbookManager {
    private final Group logbookRoot;

    public LogbookManager(Group logbookRoot) {
        this.logbookRoot = logbookRoot;
    }

    public void toggleLogbookVisibility() {
        logbookRoot.setVisible(!logbookRoot.isVisible());
    }
}