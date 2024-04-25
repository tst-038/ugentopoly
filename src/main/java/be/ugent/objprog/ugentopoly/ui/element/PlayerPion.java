package be.ugent.objprog.ugentopoly.ui.element;

import be.ugent.objprog.ugentopoly.logic.listener.PositionListener;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.ui.animation.PionMoveAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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

        new PionMoveAnimation(player, pionContainer, this).animatePionMovement(newPosition);
    }
}