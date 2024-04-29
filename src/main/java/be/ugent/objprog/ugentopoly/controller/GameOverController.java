package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.exception.ui.UIInitializationException;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.ui.animation.MoneyRainAnimation;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GameOverController {
    private final Ugentopoly ugentopoly;
    private final GameManager gameManager;

    public GameOverController(Ugentopoly ugentopoly, GameManager gameManager) {
        this.ugentopoly = ugentopoly;
        this.gameManager = gameManager;
    }

    private void init(Node rootPane) {
        List<Player> playersByMoney = gameManager.getPlayers().stream().sorted(Comparator.comparingInt(Player::getBalance)).toList().reversed();
        for (int i = 0; i < Math.min(playersByMoney.size(), 3); i++) {
            Player p = playersByMoney.get(i);
            Label pos = (Label) rootPane.lookup("#pos" + (i + 1));
            ImageView pion = (ImageView) pos.getGraphic();
            pion.setImage(p.getPion().getImage());
            pos.setGraphic(pion);
            pos.setTextFill(p.getColor());
            pos.setText(p.getName() + "\n" + p.getBalance());
        }
    }

    public void showGameOverAlert() {
        try {
            FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/game_over.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(gameManager.getPropertyreader().get("game_over"));
            alert.setHeaderText(null);

            assert root instanceof DialogPane;
            alert.setDialogPane((DialogPane) root);
            Node content = alert.getDialogPane().getContent();

            // Start the money animation
            MoneyRainAnimation moneyAnimation = new MoneyRainAnimation(-1);
            moneyAnimation.play((Pane) content, 20);

            Button playAgainButton = (Button) root.lookup("#playAgainButton");
            playAgainButton.setText(gameManager.getPropertyreader().get("button.play_again"));
            playAgainButton.setOnAction(e -> {
                alert.setResult(ButtonType.OK);
                alert.close();
            });

            Button closeButton = (Button) root.lookup("#closeButton");
            closeButton.setText(gameManager.getPropertyreader().get("button.quit"));
            closeButton.setOnAction(e -> {
                alert.setResult(ButtonType.CANCEL);
                alert.close();
            });

            init(content);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                playAgain();
            } else {
                closeApplication();
            }
        } catch (Exception e) {
            throw new UIInitializationException("Failed to initialize the gameManager over window", e);
        }
    }

    private void playAgain() {
        ugentopoly.showStartWindow();
    }

    private void closeApplication() {
        Platform.exit();
    }
}