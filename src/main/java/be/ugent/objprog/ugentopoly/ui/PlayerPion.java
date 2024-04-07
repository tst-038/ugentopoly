package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.model.Player;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PlayerPion extends ImageView {
    public HBox pionContainer;
    private Player player;

    public PlayerPion(Player player, Image image, HBox pionContainer) {
        this.player = player;
        this.pionContainer = pionContainer;
        setImage(image);
        setFitWidth(25);
        setPreserveRatio(true);
        HBox.setMargin(this, new Insets(5, 5, 5, 5));
        pionContainer.getChildren().add(this);
        this.toFront();
    }

    public Player getPlayer() {
        return player;
    }
}