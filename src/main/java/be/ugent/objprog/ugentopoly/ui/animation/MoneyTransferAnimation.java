package be.ugent.objprog.ugentopoly.ui.animation;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.behaviour.IBuyable;
import be.ugent.objprog.ugentopoly.model.player.Player;
import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class MoneyTransferAnimation {
    private static final String MONEY_IMAGE_PATH = "assets/money.png";
    private static final int ANIMATION_DURATION = 1000; // Duration in milliseconds
    private static final int DOLLARS_PER_IMAGE = 10;
    private static final double SCALE_FACTOR = 1.2;

    private void animateMoneyTransfer(Pane gameBoard, double startX, double startY, double endX, double endY, int amount,
                                  Label fromLabel, Label toLabel, Runnable onFinished) {
    int numImages = amount / DOLLARS_PER_IMAGE + 1;
    AtomicInteger completedAnimations = new AtomicInteger();

    for (int i = 0; i < numImages; i++) {
        ImageView moneyImage = createMoneyImage();
        moneyImage.setX(startX - moneyImage.getFitWidth() / 2);
        moneyImage.setY(startY - moneyImage.getFitHeight() / 2);
        gameBoard.getChildren().add(moneyImage);

        Path path = createAnimationPath(startX, startY, endX, endY);
        PathTransition pathTransition = createPathTransition(moneyImage, path);

        Timeline fromFontSizeTimeline = createFontSizeTimeline(fromLabel, 1 / SCALE_FACTOR);
        Timeline toFontSizeTimeline = createFontSizeTimeline(toLabel, SCALE_FACTOR);

        pathTransition.setOnFinished(event -> {
            gameBoard.getChildren().remove(moneyImage);
            toFontSizeTimeline.setOnFinished(e -> {
                completedAnimations.getAndIncrement();
                if (completedAnimations.get() == numImages) {
                    onFinished.run();
                }
            });
            toFontSizeTimeline.play();
        });

        pathTransition.setDelay(Duration.millis(i * 50));

        if (fromFontSizeTimeline != null) {
            fromFontSizeTimeline.setOnFinished(e -> {
                pathTransition.play();
            });
            fromFontSizeTimeline.play();
        }else{

            pathTransition.play();
        }
    }
}

    public void animateMoneyTransfer(Player fromPlayer, Player toPlayer, int amount, Pane gameBoard) {
        Region fromPlayerNode = getPlayerBalanceNode(gameBoard, fromPlayer);
        Region toPlayerNode = getPlayerBalanceNode(gameBoard, toPlayer);

        if (fromPlayerNode != null && toPlayerNode != null) {
            Bounds fromNodeBounds = fromPlayerNode.localToScene(fromPlayerNode.getBoundsInLocal());
            Bounds toNodeBounds = toPlayerNode.localToScene(toPlayerNode.getBoundsInLocal());

            double startX = fromNodeBounds.getMinX() + fromNodeBounds.getWidth() / 2;
            double startY = fromNodeBounds.getMinY() + fromNodeBounds.getHeight() / 2;
            double endX = toNodeBounds.getMinX() + toNodeBounds.getWidth() / 2;
            double endY = toNodeBounds.getMinY() + toNodeBounds.getHeight() / 2;

            Label fromLabel = (Label) fromPlayerNode.lookup("#playerBalance");
            Label toLabel = (Label) toPlayerNode.lookup("#playerBalance");
            animateMoneyTransfer(gameBoard, startX, startY, endX, endY, amount, fromLabel, toLabel, () -> {});
        }
    }

    public void animateToJackpot(Player player, Pane gameBoard, int amount) {
        Region playerNode = getPlayerBalanceNode(gameBoard, player);
        Region jackpot = (Region) gameBoard.lookup("#jackpot");

        if (playerNode != null && jackpot != null) {
            Bounds fromNodeBounds = playerNode.localToScene(playerNode.getBoundsInLocal());
            Bounds toNodeBounds = jackpot.localToScene(jackpot.getBoundsInLocal());

            double startX = fromNodeBounds.getMinX() + fromNodeBounds.getWidth() / 2;
            double startY = fromNodeBounds.getMinY() + fromNodeBounds.getHeight() / 2;
            double endX = toNodeBounds.getMinX() + toNodeBounds.getWidth() / 2;
            double endY = toNodeBounds.getMinY() + toNodeBounds.getHeight() / 2;

            Label fromLabel = (Label) playerNode.lookup("#playerBalance");
            Label toLabel = (Label) jackpot;
            animateMoneyTransfer(gameBoard, startX, startY, endX, endY, amount, fromLabel, toLabel, () -> {});
        }
    }

    public void animateClaimJackpot(Player player, Pane gameBoard, int amount) {
        Region playerNode = getPlayerBalanceNode(gameBoard, player);
        Region jackpot = (Region) gameBoard.lookup("#jackpot");

        if (playerNode != null && jackpot != null) {
            Bounds fromNodeBounds = jackpot.localToScene(jackpot.getBoundsInLocal());
            Bounds toNodeBounds = playerNode.localToScene(playerNode.getBoundsInLocal());

            double startX = fromNodeBounds.getMinX() + fromNodeBounds.getWidth() / 2;
            double startY = fromNodeBounds.getMinY() + fromNodeBounds.getHeight() / 2;
            double endX = toNodeBounds.getMinX() + toNodeBounds.getWidth() / 2;
            double endY = toNodeBounds.getMinY() + toNodeBounds.getHeight() / 2;

            Label fromLabel = (Label) jackpot;
            Label toLabel = (Label) playerNode.lookup("#playerBalance");
            animateMoneyTransfer(gameBoard, startX, startY, endX, endY, amount, fromLabel, toLabel, () -> {});
        }
    }

    public void animateDepositFromBank(Player player, Pane gameBoard, int amount) {
        Region playerNode = getPlayerBalanceNode(gameBoard, player);

        if (playerNode != null) {
            Bounds fromNodeBounds = gameBoard.localToScene(gameBoard.getBoundsInLocal());
            Bounds toNodeBounds = playerNode.localToScene(playerNode.getBoundsInLocal());

            double startX = fromNodeBounds.getMinX() + fromNodeBounds.getWidth() / 2;
            double startY = fromNodeBounds.getMinY() + fromNodeBounds.getHeight() / 2;
            double endX = toNodeBounds.getMinX() + toNodeBounds.getWidth() / 2;
            double endY = toNodeBounds.getMinY() + toNodeBounds.getHeight() / 2;

            Label toLabel = (Label) playerNode.lookup("#playerBalance");
            animateMoneyTransfer(gameBoard, startX, startY, endX, endY, amount, null, toLabel, () -> {});
        }
    }

    public void animatePropertyBought(Player player, Pane gameBoard, IBuyable tile, Runnable onFinished) {
        Region playerNode = getPlayerBalanceNode(gameBoard, player);
        Region tileNode = (Region) gameBoard.lookup("#_" + tile.getPosition());

        if (playerNode != null && tileNode != null) {
            Bounds fromNodeBounds = playerNode.localToScene(playerNode.getBoundsInLocal());
            Bounds toNodeBounds = tileNode.localToScene(tileNode.getBoundsInLocal());

            double startX = fromNodeBounds.getMinX() + fromNodeBounds.getWidth() / 2;
            double startY = fromNodeBounds.getMinY() + fromNodeBounds.getHeight() / 2;
            double endX = toNodeBounds.getMinX() + toNodeBounds.getWidth() / 2;
            double endY = toNodeBounds.getMinY() + toNodeBounds.getHeight() / 2;

            Label fromLabel = (Label) playerNode.lookup("#playerBalance");

            // Check if the tile has a Label or an ImageView
            Label toLabel = (Label) gameBoard.lookup("#_" + tile.getPosition()).lookup("Label");
            ImageView utilityImage = (ImageView) gameBoard.lookup("#_" + tile.getPosition()).lookup("#utilityImage");

            if (toLabel != null) {
                // If the tile has a Label, animate the money transfer with the Label
                animateMoneyTransfer(gameBoard, startX, startY, endX, endY, tile.getPrice(), fromLabel, toLabel, onFinished);
            } else if (utilityImage != null) {
                // If the tile has an ImageView, animate the money transfer with the ImageView
                animateMoneyTransferToImageView(gameBoard, startX, startY, endX, endY, tile.getPrice(), fromLabel, utilityImage, onFinished);
            }
        }
    }

    private void animateMoneyTransferToImageView(Pane gameBoard, double startX, double startY, double endX, double endY, int amount,
                                                 Label fromLabel, ImageView toImageView, Runnable onFinished) {
        int numImages = amount / DOLLARS_PER_IMAGE + 1;
        AtomicInteger completedAnimations = new AtomicInteger();

        for (int i = 0; i < numImages; i++) {
            ImageView moneyImage = createMoneyImage();
            moneyImage.setX(startX - moneyImage.getFitWidth() / 2);
            moneyImage.setY(startY - moneyImage.getFitHeight() / 2);
            gameBoard.getChildren().add(moneyImage);

            Path path = createAnimationPath(startX, startY, endX, endY);
            PathTransition pathTransition = createPathTransition(moneyImage, path);

            Timeline fromFontSizeTimeline = createFontSizeTimeline(fromLabel, 1 / SCALE_FACTOR);
            ScaleTransition scaleTransition = createScaleTransition(toImageView, SCALE_FACTOR);

            pathTransition.setOnFinished(event -> {
                gameBoard.getChildren().remove(moneyImage);
                scaleTransition.setOnFinished(e -> {
                    completedAnimations.getAndIncrement();
                    if (completedAnimations.get() == numImages) {
                        onFinished.run();
                    }
                });
                scaleTransition.play();
            });

            pathTransition.setDelay(Duration.millis(i * 50));

            if (fromFontSizeTimeline != null) {
                fromFontSizeTimeline.setOnFinished(e -> {
                    pathTransition.play();
                });
                fromFontSizeTimeline.play();
            } else {
                pathTransition.play();
            }
        }
    }

    private ScaleTransition createScaleTransition(ImageView imageView, double scaleFactor) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(50), imageView);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(scaleFactor);
        scaleTransition.setToY(scaleFactor);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        return scaleTransition;
    }

    private Region getPlayerBalanceNode(Pane gameBoard, Player player) {
        return (Region) gameBoard.lookup("#player_" + player.getId()).lookup("#playerBalance");
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

    private Timeline createFontSizeTimeline(Label label, double scaleFactor) {
        if (label != null) {
            double initialFontSize = label.getFont().getSize();
            double scaledFontSize = initialFontSize * scaleFactor;

            return new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(label.fontProperty(), Font.font(initialFontSize))),
                    new KeyFrame(Duration.millis(25), new KeyValue(label.fontProperty(), Font.font(scaledFontSize))),
                    new KeyFrame(Duration.millis(50), new KeyValue(label.fontProperty(), Font.font(initialFontSize)))
            );
        }
        return null;
    }
}