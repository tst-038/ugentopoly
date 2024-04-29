package be.ugent.objprog.ugentopoly.ui.animation;

import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.ui.element.PlayerPion;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PionMoveAnimation {
    private final Player player;
    private Pane pionContainer;
    private final PlayerPion currentPion;

    public PionMoveAnimation(Player player, Pane pionContainer, PlayerPion currentPion) {
        this.player = player;
        this.pionContainer = pionContainer;
        this.currentPion = currentPion;
    }

    public void animatePionMovement(int newPosition) {
        Pane currentPionContainer = pionContainer;

        player.getPosition().removeListener(currentPion);

        String tileId = "#_" + newPosition;
        pionContainer = (Pane) pionContainer.getScene().lookup(tileId).lookup("#pionContainer");

        PlayerPion newPion = createNewPion();
        newPion.addToContainer(pionContainer);
        newPion.setVisible(false);

        Bounds startBounds = currentPionContainer.localToScene(currentPionContainer.getBoundsInParent());
        Bounds targetBounds = pionContainer.localToScene(pionContainer.getBoundsInParent());

        double startCenterX = startBounds.getMinX() + startBounds.getWidth() / 2;
        double startCenterY = startBounds.getMinY() + startBounds.getHeight() / 2;

        double targetCenterX = targetBounds.getCenterX();
        double targetCenterY = targetBounds.getCenterY();

        currentPion.setTranslateX(startCenterX - currentPion.getBoundsInParent().getWidth() / 2);
        currentPion.setTranslateY(startCenterY - currentPion.getBoundsInParent().getHeight() / 2);

        TranslateTransition transition = createTranslateTransition(currentPionContainer, startCenterX, startCenterY, targetCenterX, targetCenterY, newPion);

        transition.play();
    }

    private PlayerPion createNewPion() {
        return new PlayerPion(player, player.getPion().getImage());
    }

    private TranslateTransition createTranslateTransition(Pane currentPionContainer, double startX, double startY, double targetX, double targetY, PlayerPion newPion) {
        currentPion.toFront();
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), currentPion);

        Bounds pionBounds = currentPion.getBoundsInParent();
        Bounds containerBounds = currentPionContainer.getBoundsInParent();

        double containerCenterX = containerBounds.getWidth();
        double containerCenterY = containerBounds.getHeight();

        double pionCenterX = pionBounds.getMinX() + pionBounds.getWidth() / 2;
        double pionCenterY = pionBounds.getMinY() + pionBounds.getHeight() / 2;

        double fromX = pionCenterX - containerCenterX;
        double fromY = pionCenterY - containerCenterY;

        transition.setFromX(0);
        transition.setFromY(0);
        transition.setToX(targetX - startX + fromX/2);
        transition.setToY(targetY - startY + fromY/2);

        transition.setOnFinished(event -> {
            currentPionContainer.getChildren().remove(currentPion);
            newPion.setVisible(true);
            player.setPion(newPion);
        });

        return transition;
    }
}
