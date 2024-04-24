package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.logic.PositionListener;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.animations.PionAnimation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PlayerPion extends ImageView implements PositionListener {
    private final Player player;
    private Pane pionContainer;

    public PlayerPion(Player player, Image pionImage) {
        this.player = player;
        setImage(pionImage);
        setFitWidth(35);
        setFitHeight(35);
        setPreserveRatio(true);
        player.getPosition().addListener(this);
    }

    public void addToContainer(Pane pionContainer) {
        this.pionContainer = pionContainer;
        pionContainer.getChildren().add(this);
        pionContainer.toFront();
    }

    @Override
    public void onPositionChanged(int newPosition) {
        player.getPosition().removeListener(this);

        new PionAnimation(player, pionContainer, this).animatePionMovement(newPosition);
    }
}