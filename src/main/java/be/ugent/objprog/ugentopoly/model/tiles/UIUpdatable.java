package be.ugent.objprog.ugentopoly.model.tiles;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public interface UIUpdatable {
    default void updateUI(Node tileNode, Pane rootPane) {
        // Default implementation
    }

    Image getImage();
}