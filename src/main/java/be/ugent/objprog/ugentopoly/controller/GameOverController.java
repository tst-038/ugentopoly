package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.exceptions.ui.UIInitializationException;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.animations.MoneyAnimation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Comparator;
import java.util.List;

public class GameOverController {

    //TODO move stuff to properties file

    private static void initialize(Node rootPane){
        List<Player> playersByMoney = PlayerManager.getInstance().getPlayers().stream().sorted(Comparator.comparingInt(Player::getBalance)).toList().reversed();
        for (int i = 0; i < Math.min(playersByMoney.size(), 3); i++) {
            Player p = playersByMoney.get(i);
            Label pos = (Label) rootPane.lookup("#pos" + (i + 1));
            ImageView pion = (ImageView) pos.getGraphic();
            pion.setImage(p.getPion().getImage());
            pos.setGraphic(pion);
            pos.setTextFill(p.getColor());
            pos.setText(p.getName()+ "\n" + p.getBalance());
        }
    }

    public static void showGameOverAlert() {
        try {
            FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/game_over.fxml"));
            Parent root = loader.load();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("The game has ended!");

            assert root instanceof DialogPane;
            alert.setDialogPane((DialogPane) root);
            Node content = alert.getDialogPane().getContent();

            // Start the money animation
            MoneyAnimation moneyAnimation = new MoneyAnimation(-1);
            moneyAnimation.play((Pane) content, 20);

           initialize(content);
           alert.showAndWait();
        } catch (Exception e) {
            throw new UIInitializationException("Failed to initialize the start window", e);
        }
    }
}