package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.exceptions.ui.UIInitializationException;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.animations.MoneyAnimation;
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
    private final Game game;
    private List<Player> playersByMoney;

    public GameOverController(Ugentopoly ugentopoly, Game game) {
        this.ugentopoly = ugentopoly;
        this.game = game;
    }

    private void initPlayersByMoney() {
        playersByMoney = game.getPlayers().stream().sorted(Comparator.comparingInt(Player::getBalance)).toList().reversed();
    }

    private void initPodium(Node rootPane) {
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
        initPlayersByMoney();
        try {
            FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/game_over.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(game.getPropertyreader().get("game_over"));
            alert.setHeaderText(null);
            alert.setContentText(String.format(game.getPropertyreader().get("game_over_message"), playersByMoney.getLast().getName()));

            assert root instanceof DialogPane;
            alert.setDialogPane((DialogPane) root);
            Node content = alert.getDialogPane().getContent();

            // Start the money animation
            MoneyAnimation moneyAnimation = new MoneyAnimation(-1);
            moneyAnimation.play((Pane) content, 20);

            Button playAgainButton = (Button) root.lookup("#playAgainButton");
            playAgainButton.setOnAction(e -> {
                alert.setResult(ButtonType.OK);
                alert.close();
            });

            Button closeButton = (Button) root.lookup("#closeButton");
            closeButton.setOnAction(e -> {
                alert.setResult(ButtonType.CANCEL);
                alert.close();
            });

            initPodium(content);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                playAgain();
            } else {
                closeApplication();
            }
        } catch (Exception e) {
            throw new UIInitializationException("Failed to initialize the game over window", e);
        }
    }

    private void playAgain() {
        ugentopoly.showStartWindow();
    }

    private void closeApplication() {
        Platform.exit();
    }
}