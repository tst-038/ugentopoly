package be.ugent.objprog.ugentopoly.model.tiles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface UIUpdatable {
    default void updateUI(Node tileNode, Label label) {
        ImageView imageView = new ImageView(getImage());
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        label.setGraphic(imageView);
    }

    Image getImage();
}