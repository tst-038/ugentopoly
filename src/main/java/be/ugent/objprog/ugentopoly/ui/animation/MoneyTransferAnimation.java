package be.ugent.objprog.ugentopoly.ui.animation;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.behaviour.IBuyable;
import be.ugent.objprog.ugentopoly.model.player.Player;
import javafx.animation.PathTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class MoneyTransferAnimation {
    private static final String MONEY_IMAGE_PATH = "assets/money.png";
    private static final int ANIMATION_DURATION = 1000; // Duration in milliseconds
    private static final int DOLLARS_PER_IMAGE = 10;

    public void animateMoneyTransfer(Player fromPlayer, Player toPlayer, int amount, Pane gameBoard) {
        Region fromPlayerNode = (Region) gameBoard.lookup("#player_" + fromPlayer.getId()).lookup("#playerBalance");
        Region toPlayerNode = (Region) gameBoard.lookup("#player_" + toPlayer.getId()).lookup("#playerBalance");

        if (fromPlayerNode != null && toPlayerNode != null) {
            Bounds fromNodeBounds = fromPlayerNode.localToScene(fromPlayerNode.getBoundsInLocal());
            Bounds toNodeBounds = toPlayerNode.localToScene(toPlayerNode.getBoundsInLocal());

            double startX = fromNodeBounds.getMinX() + fromNodeBounds.getWidth() / 2;
            double startY = fromNodeBounds.getMinY() + fromNodeBounds.getHeight() / 2;
            double endX = toNodeBounds.getMinX() + toNodeBounds.getWidth() / 2;
            double endY = toNodeBounds.getMinY() + toNodeBounds.getHeight() / 2;

            int numImages = amount / DOLLARS_PER_IMAGE + 1;
            for (int i = 0; i < numImages; i++) {
                ImageView moneyImage = createMoneyImage();
                moneyImage.setX(startX - moneyImage.getFitWidth()/2);
                moneyImage.setY(startY - moneyImage.getFitHeight()/2);
                gameBoard.getChildren().add(moneyImage);

                Path path = createAnimationPath(startX, startY, endX, endY);
                PathTransition pathTransition = createPathTransition(moneyImage, path);

                pathTransition.setOnFinished(event -> gameBoard.getChildren().remove(moneyImage));
                pathTransition.setDelay(Duration.millis(i * 50));
                pathTransition.play();
            }
        }
    }

    public void animateToJackpot(Player player, Pane gameBoard, int amount) {
        Region playerNode = (Region) gameBoard.lookup("#player_" + player.getId()).lookup("#playerBalance");
        Region jackpot = (Region) gameBoard.lookup("#jackpot");

        if (playerNode != null && jackpot != null) {
            Bounds fromNodeBounds = playerNode.localToScene(playerNode.getBoundsInLocal());
            Bounds toNodeBounds = jackpot.localToScene(jackpot.getBoundsInLocal());

            double startX = fromNodeBounds.getMinX() + fromNodeBounds.getWidth() / 2;
            double startY = fromNodeBounds.getMinY() + fromNodeBounds.getHeight() / 2;
            double endX = toNodeBounds.getMinX() + toNodeBounds.getWidth() / 2;
            double endY = toNodeBounds.getMinY() + toNodeBounds.getHeight() / 2;

            int numImages = amount / DOLLARS_PER_IMAGE + 1;
            for (int i = 0; i < numImages; i++) {
                ImageView moneyImage = createMoneyImage();
                moneyImage.setX(startX - moneyImage.getFitWidth()/2);
                moneyImage.setY(startY - moneyImage.getFitHeight()/2);
                gameBoard.getChildren().add(moneyImage);

                Path path = createAnimationPath(startX, startY, endX, endY);
                PathTransition pathTransition = createPathTransition(moneyImage, path);

                pathTransition.setOnFinished(event -> gameBoard.getChildren().remove(moneyImage));
                pathTransition.setDelay(Duration.millis(i * 50)); // Delay each image by 100ms
                pathTransition.play();
            }
        }
    }

    public void animateClaimJackpot(Player player, Pane gameBoard, int amount) {
        Region playerNode = (Region) gameBoard.lookup("#player_" + player.getId()).lookup("#playerBalance");
        Region jackpot = (Region) gameBoard.lookup("#jackpot");

        if (playerNode != null && jackpot != null) {
            Bounds fromNodeBounds = jackpot.localToScene(jackpot.getBoundsInLocal());
            Bounds toNodeBounds = playerNode.localToScene(playerNode.getBoundsInLocal());

            double startX = fromNodeBounds.getMinX() + fromNodeBounds.getWidth() / 2;
            double startY = fromNodeBounds.getMinY() + fromNodeBounds.getHeight() / 2;
            double endX = toNodeBounds.getMinX() + toNodeBounds.getWidth() / 2;
            double endY = toNodeBounds.getMinY() + toNodeBounds.getHeight() / 2;

            int numImages = amount / DOLLARS_PER_IMAGE + 1;
            for (int i = 0; i < numImages; i++) {
                ImageView moneyImage = createMoneyImage();
                moneyImage.setX(startX - moneyImage.getFitWidth()/2);
                moneyImage.setY(startY - moneyImage.getFitHeight()/2);
                gameBoard.getChildren().add(moneyImage);

                Path path = createAnimationPath(startX, startY, endX, endY);
                PathTransition pathTransition = createPathTransition(moneyImage, path);

                pathTransition.setOnFinished(event -> gameBoard.getChildren().remove(moneyImage));
                pathTransition.setDelay(Duration.millis(i * 50)); // Delay each image by 100ms
                pathTransition.play();
            }
        }
    }

    public void animateDepositFromBank(Player player, Pane gameBoard, int amount) {
        Region playerNode = (Region) gameBoard.lookup("#player_" + player.getId()).lookup("#playerBalance");

        if (playerNode != null) {
            Bounds fromNodeBounds = gameBoard.localToScene(gameBoard.getBoundsInLocal());
            Bounds toNodeBounds = playerNode.localToScene(playerNode.getBoundsInLocal());

            double startX = fromNodeBounds.getMinX() + fromNodeBounds.getWidth() / 2;
            double startY = fromNodeBounds.getMinY() + fromNodeBounds.getHeight() / 2;
            double endX = toNodeBounds.getMinX() + toNodeBounds.getWidth() / 2;
            double endY = toNodeBounds.getMinY() + toNodeBounds.getHeight() / 2;

            int numImages = amount / DOLLARS_PER_IMAGE + 1;
            for (int i = 0; i < numImages; i++) {
                ImageView moneyImage = createMoneyImage();
                moneyImage.setX(startX - moneyImage.getFitWidth()/2);
                moneyImage.setY(startY - moneyImage.getFitHeight()/2);
                gameBoard.getChildren().add(moneyImage);

                Path path = createAnimationPath(startX, startY, endX, endY);
                PathTransition pathTransition = createPathTransition(moneyImage, path);

                pathTransition.setOnFinished(event -> gameBoard.getChildren().remove(moneyImage));
                pathTransition.setDelay(Duration.millis(i * 50)); // Delay each image by 100ms
                pathTransition.play();
            }
        }
    }

    public void animatePropertyBought(Player player, Pane gameBoard, IBuyable tile, Runnable onFinished) {
        Region playerNode = (Region) gameBoard.lookup("#player_" + player.getId()).lookup("#playerBalance");
        Region tileNode = (Region) gameBoard.lookup("#_" + tile.getPosition());

        if (playerNode != null && tileNode != null) {
            Bounds fromNodeBounds = playerNode.localToScene(playerNode.getBoundsInLocal());
            Bounds toNodeBounds = tileNode.localToScene(tileNode.getBoundsInLocal());

            double startX = fromNodeBounds.getMinX() + fromNodeBounds.getWidth() / 2;
            double startY = fromNodeBounds.getMinY() + fromNodeBounds.getHeight() / 2;
            double endX = toNodeBounds.getMinX() + toNodeBounds.getWidth() / 2;
            double endY = toNodeBounds.getMinY() + toNodeBounds.getHeight() / 2;

            int numImages = tile.getPrice() / DOLLARS_PER_IMAGE + 1;
            //Thread safe
            AtomicInteger completedAnimations = new AtomicInteger();

            for (int i = 0; i < numImages; i++) {
                ImageView moneyImage = createMoneyImage();
                moneyImage.setX(startX - moneyImage.getFitWidth() / 2);
                moneyImage.setY(startY - moneyImage.getFitHeight() / 2);
                gameBoard.getChildren().add(moneyImage);

                Path path = createAnimationPath(startX, startY, endX, endY);
                PathTransition pathTransition = createPathTransition(moneyImage, path);

                pathTransition.setOnFinished(event -> {
                    gameBoard.getChildren().remove(moneyImage);
                    completedAnimations.getAndIncrement();

                    if (completedAnimations.get() == numImages) {
                        onFinished.run();
                    }
                });
                pathTransition.setDelay(Duration.millis(i * 50)); // Delay each image by 50ms
                pathTransition.play();
            }
        }
    }

    private ImageView createMoneyImage() {
        Image image = ResourceLoader.loadImage(MONEY_IMAGE_PATH);
        ImageView moneyImage = new ImageView(image);
        moneyImage.setFitWidth(30);
        moneyImage.setFitHeight(30);
        return moneyImage;
    }

    private Path createAnimationPath(double startX, double startY, double endX, double endY) {
        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));
        path.getElements().add(new LineTo(endX, endY));
        return path;
    }

    private PathTransition createPathTransition(ImageView moneyImage, Path path) {
        PathTransition pathTransition = new PathTransition(Duration.millis(ANIMATION_DURATION), path, moneyImage);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        return pathTransition;
    }
}
