package be.ugent.objprog.ugentopoly.ui.animations;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.PlayerPion;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PionAnimation {
    private final Player player;
    private Pane pionContainer;
    private final PlayerPion currentPion;

    public PionAnimation(Player player, Pane pionContainer, PlayerPion currentPion) {
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

        Bounds startBounds = getStartBounds();
        Bounds targetBounds = getTargetBounds(newPion);

        double[] imagePosition = calculateImagePosition(newPosition, newPion);
        double imageX = imagePosition[0];
        double imageY = imagePosition[1];

        currentPion.setLayoutX(startBounds.getMinX());
        currentPion.setLayoutY(startBounds.getMinY());

        TranslateTransition transition = createTranslateTransition(startBounds, targetBounds, imageX, imageY);
        transition.setOnFinished(event -> onAnimationFinished(currentPionContainer, newPion, imageX, imageY));

        transition.play();
    }

    private PlayerPion createNewPion() {
        return new PlayerPion(player, player.getPion().getImage());
    }

    private Bounds getStartBounds() {
        return currentPion.localToScene(currentPion.getBoundsInLocal());
    }

    private Bounds getTargetBounds(PlayerPion newPion) {
        return newPion.localToScene(newPion.getBoundsInLocal());
    }

    private double[] calculateImagePosition(int newPosition, PlayerPion newPion) {
        double containerWidth = pionContainer.getWidth();
        double containerHeight = pionContainer.getHeight();
        double imageWidth = newPion.getBoundsInLocal().getWidth();
        double imageHeight = newPion.getBoundsInLocal().getHeight();
        double marginX = 10.0;
        double marginY = 10.0;

        double imageX, imageY;

        if (newPosition == 0 || newPosition == 10 || newPosition == 20 || newPosition == 30) {
            imageX = (containerWidth - imageWidth) / 2;
            imageY = (containerHeight - imageHeight) / 2;
        } else if (newPosition >= 1 && newPosition <= 9) {
            imageX = (pionContainer.getChildren().size() - 1) * (imageWidth + marginX) + marginX;
            imageY = (containerHeight - imageHeight) / 2;
        } else if (newPosition >= 11 && newPosition <= 19) {
            imageX = (containerWidth - imageWidth) / 2;
            imageY = (pionContainer.getChildren().size() - 1) * (imageHeight + marginY) + marginY;
        } else if (newPosition >= 21 && newPosition <= 29) {
            imageX = containerWidth - (pionContainer.getChildren().size() * (imageWidth + marginX));
            imageY = (containerHeight - imageHeight) / 2;
        } else {
            imageX = (containerWidth - imageWidth) / 2;
            imageY = containerHeight - (pionContainer.getChildren().size() * (imageHeight + marginY));
        }

        return new double[]{imageX, imageY};
    }

    private TranslateTransition createTranslateTransition(Bounds startBounds, Bounds targetBounds, double imageX, double imageY) {
        currentPion.toFront();
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), currentPion);
        transition.setToX(targetBounds.getMinX() - startBounds.getMinX() + imageX);
        transition.setToY(targetBounds.getMinY() - startBounds.getMinY() + imageY);
        return transition;
    }

    private void onAnimationFinished(Pane currentPionContainer, PlayerPion newPion, double imageX, double imageY) {
        currentPionContainer.getChildren().remove(currentPion);
        newPion.setVisible(true);
        newPion.setLayoutX(imageX);
        newPion.setLayoutY(imageY);
        player.setPion(newPion);
    }
}
