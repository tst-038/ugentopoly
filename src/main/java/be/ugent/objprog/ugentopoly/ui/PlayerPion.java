package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.logic.PositionListener;
import be.ugent.objprog.ugentopoly.model.Player;
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
        Pane currentPionContainer = pionContainer;

        if (currentPionContainer == null) {
            System.out.println("Pion container not set for player " + player.getName() + " (" + player.getId() + ")");
            return;
        }

        // Get the target pionContainer based on the position
        String tileId = "#_" + newPosition;
        pionContainer = (Pane) pionContainer.getScene().lookup(tileId).lookup("#pionContainer");

        PlayerPion newPion = new PlayerPion(player, player.getPion().getImage());
        newPion.addToContainer(pionContainer);
        newPion.setVisible(false);

        // Calculate the start position coordinates in the scene
        Bounds startBounds = this.localToScene(this.getBoundsInLocal());
        double startX = startBounds.getMinX();
        double startY = startBounds.getMinY();

        // Calculate the target position coordinates in the scene
        Bounds targetBounds = newPion.localToScene(newPion.getBoundsInLocal());
        double targetX = targetBounds.getMinX();
        double targetY = targetBounds.getMinY();

        // Calculate the position of the image within the container
        double containerWidth = pionContainer.getWidth();
        double containerHeight = pionContainer.getHeight();
        double imageWidth = newPion.getBoundsInLocal().getWidth();
        double imageHeight = newPion.getBoundsInLocal().getHeight();
        double marginX = 10.0; // Adjust this value to change the horizontal margin
        double marginY = 10.0; // Adjust this value to change the vertical margin

        double imageX, imageY;

        if (newPosition == 0 || newPosition == 10 || newPosition == 20 || newPosition == 30) {
            // Corner tiles: always center the pion
            imageX = (containerWidth - imageWidth) / 2;
            imageY = (containerHeight - imageHeight) / 2;
        } else if (newPosition >= 1 && newPosition <= 9) {
            // Tiles 1-9: horizontal positioning (HBox)
            imageX = (pionContainer.getChildren().size() - 1) * (imageWidth + marginX) + marginX;
            imageY = (containerHeight - imageHeight) / 2;
        } else if (newPosition >= 11 && newPosition <= 19) {
            // Tiles 11-19: vertical positioning (VBox)
            imageX = (containerWidth - imageWidth) / 2;
            imageY = (pionContainer.getChildren().size() - 1) * (imageHeight + marginY) + marginY;
        } else if (newPosition >= 21 && newPosition <= 29) {
            // Tiles 21-29: horizontal positioning (HBox) from right to left
            imageX = containerWidth - (pionContainer.getChildren().size() * (imageWidth + marginX));
            imageY = (containerHeight - imageHeight) / 2;
        } else {
            // Tiles 31-39: vertical positioning (VBox) from bottom to top
            imageX = (containerWidth - imageWidth) / 2;
            imageY = containerHeight - (pionContainer.getChildren().size() * (imageHeight + marginY));
        }

        // Set the initial position of the pion in the scene
        this.setLayoutX(startX);
        this.setLayoutY(startY);

        // Create a TranslateTransition to animate the pion movement
        toFront();
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), this);
        transition.setToX(targetX - startX + imageX);
        transition.setToY(targetY - startY + imageY);

        transition.setToY(targetY - startY + imageY);

        // Set an event handler to remove the current pion and make the new pion visible
        transition.setOnFinished(event -> {
            currentPionContainer.getChildren().remove(this);
            newPion.setVisible(true);
            newPion.setLayoutX(imageX);
            newPion.setLayoutY(imageY);
            player.setPion(newPion);
        });

        // Start the animation
        transition.play();
    }
}