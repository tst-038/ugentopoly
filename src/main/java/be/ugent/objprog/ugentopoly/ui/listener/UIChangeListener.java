package be.ugent.objprog.ugentopoly.ui.listener;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public interface UIChangeListener {
    void updateUI(Node tileNode, Pane rootPane);
}
