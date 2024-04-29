package be.ugent.objprog.ugentopoly.ui.animation;

import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.ui.element.PlayerPawn;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PawnMoveAnimation {
    private final Player player;
    private Pane pawnContainer;
    private final PlayerPawn currentPawn;

    public PawnMoveAnimation(Player player, Pane pawnContainer, PlayerPawn currentPawn) {
        this.player = player;
        this.pawnContainer = pawnContainer;
        this.currentPawn = currentPawn;
    }

    public void animatePawnMovement(int newPosition) {
        Pane currentPawnContainer = pawnContainer;

        player.getPosition().removeListener(currentPawn);

        String tileId = "#_" + newPosition;
        pawnContainer = (Pane) pawnContainer.getScene().lookup(tileId).lookup("#pawnContainer");

        PlayerPawn newPawn = createNewPawn();
        newPawn.addToContainer(pawnContainer);
        newPawn.setVisible(false);

        Bounds startBounds = currentPawnContainer.localToScene(currentPawnContainer.getBoundsInParent());
        Bounds targetBounds = pawnContainer.localToScene(pawnContainer.getBoundsInParent());

        double startCenterX = startBounds.getMinX() + startBounds.getWidth() / 2;
        double startCenterY = startBounds.getMinY() + startBounds.getHeight() / 2;

        double targetCenterX = targetBounds.getCenterX();
        double targetCenterY = targetBounds.getCenterY();

        currentPawn.setTranslateX(startCenterX - currentPawn.getBoundsInParent().getWidth() / 2);
        currentPawn.setTranslateY(startCenterY - currentPawn.getBoundsInParent().getHeight() / 2);

        TranslateTransition transition = createTranslateTransition(currentPawnContainer, startCenterX, startCenterY, targetCenterX, targetCenterY, newPawn);

        transition.play();
    }

    private PlayerPawn createNewPawn() {
        return new PlayerPawn(player, player.getPawn().getImage());
    }

    private TranslateTransition createTranslateTransition(Pane currentPawnContainer, double startX, double startY, double targetX, double targetY, PlayerPawn newPawn) {
        currentPawn.toFront();
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), currentPawn);

        Bounds pawnBounds = currentPawn.getBoundsInParent();
        Bounds containerBounds = currentPawnContainer.getBoundsInParent();

        double containerCenterX = containerBounds.getWidth();
        double containerCenterY = containerBounds.getHeight();

        double pawnCenterX = pawnBounds.getMinX() + pawnBounds.getWidth() / 2;
        double pawnCenterY = pawnBounds.getMinY() + pawnBounds.getHeight() / 2;

        double fromX = pawnCenterX - containerCenterX;
        double fromY = pawnCenterY - containerCenterY;

        transition.setFromX(0);
        transition.setFromY(0);
        transition.setToX(targetX - startX + fromX/2);
        transition.setToY(targetY - startY + fromY/2);

        transition.setOnFinished(event -> {
            currentPawnContainer.getChildren().remove(currentPawn);
            newPawn.setVisible(true);
            player.setPawn(newPawn);
        });

        return transition;
    }
}
