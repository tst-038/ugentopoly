package be.ugent.objprog.ugentopoly.ui.interfaces;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface ImageUpdatable {
    default void updateImage(Node tileNode) {
        Label label = (Label) tileNode.lookup("Label");
        if (label != null && getImage() != null) {
            ImageView imageView = new ImageView(getImage());
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setPreserveRatio(true);
            imageView.setPickOnBounds(true);
            label.setGraphic(imageView);
        }
    }

    Image getImage();
}
