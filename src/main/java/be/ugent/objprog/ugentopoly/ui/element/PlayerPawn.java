package be.ugent.objprog.ugentopoly.ui.element;

import be.ugent.objprog.ugentopoly.logic.listener.PositionListener;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.ui.animation.PawnMoveAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerPawn extends ImageView implements PositionListener {
    private final Player player;
    private Pane pawnContainer;

    public PlayerPawn(Player player, Image pawnImage) {
        this.player = player;
        setImage(pawnImage);
        setFitWidth(35);
        setFitHeight(35);
        setPreserveRatio(true);
        player.getPosition().addListener(this);
    }

    public void addToContainer(Pane pawnContainer) {
        this.pawnContainer = pawnContainer;
        pawnContainer.getChildren().add(this);
        pawnContainer.toFront();
    }

    @Override
    public void onPositionChanged(int newPosition) {
        player.getPosition().removeListener(this);

        new PawnMoveAnimation(player, pawnContainer, this).animatePawnMovement(newPosition);
    }
}