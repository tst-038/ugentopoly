package be.ugent.objprog.ugentopoly.ui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public interface UIUpdatable {
    void updateUI(Node tileNode, Pane rootPane);
}
