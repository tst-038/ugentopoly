package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Player;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartController {
    @FXML
    private Slider playerSlider;

    @FXML
    private VBox playerFieldsContainer;
    @FXML
    private Label playerNamesNotUnique;
    @FXML
    private Label playerColorsNotUnique;
    @FXML
    private Label playerAmount;
    @FXML
    private Button playButton;

    private List<Player> players;

    @FXML
    private void initialize() {
        players = new ArrayList<>();
        playerSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int numPlayers = newValue.intValue();
            // TODO put in properties file
            playerAmount.setText(numPlayers + " spelers");
            updatePlayerFields(numPlayers);
        });
        updatePlayerFields(2);
    }

    @FXML
    private void handlePlayButtonAction() {
        if (areNamesAndColorsUnique()) {
            // Pass the list of players to the main class to start the game
            Ugentopoly.startGame(players);
        }
    }

    private boolean areNamesAndColorsUnique() {
        return playersAreUnique() && colorsAreUnique();
    }

    private boolean playersAreUnique() {
        Set<String> playerSet = new HashSet<>(players.stream().map(Player::getName).toList());
        return playerSet.size() == players.size();
    }

    private boolean colorsAreUnique() {
        Set<Color> colorSet = new HashSet<>(players.stream().map(Player::getColor).toList());
        return colorSet.size() == players.size();
    }

    private void updatePlayerFields(int numPlayers) {
        int currentNumPlayers = playerFieldsContainer.getChildren().size();

        Color[] defaultColors = {
                Color.GREEN, Color.RED, Color.CYAN, Color.MAGENTA
        };

        if (numPlayers > currentNumPlayers) {
            // Add new player fields
            for (int i = currentNumPlayers; i < numPlayers; i++) {
                AnchorPane playerFieldPane = new AnchorPane();
                playerFieldPane.setPrefSize(200, 25);
                TextField usernameField = new TextField();
                usernameField.setPrefWidth(125);
                usernameField.setText("Speler " + (i + 1));
                AnchorPane.setLeftAnchor(usernameField, 0.0);

                ColorPicker colorPicker = new ColorPicker();
                colorPicker.setValue(defaultColors[i]);
                colorPicker.setPrefSize(31, 25);
                colorPicker.setStyle("");
                AnchorPane.setRightAnchor(colorPicker, 20.0);
                AnchorPane.setTopAnchor(colorPicker, 2.0);

                Player player;
                if (i < players.size()) {
                    player = players.get(i);
                    usernameField.setText(player.getName());
                    colorPicker.setValue(player.getColor());
                } else {
                    player = new Player(usernameField.getText(), colorPicker.getValue());
                    players.add(player);
                }

                usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
                    player.setName(newValue);
                    updateNotUniqueLabels();
                });

                colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                    player.setColor(newValue);
                    updateNotUniqueLabels();
                });

                playerFieldPane.getChildren().addAll(usernameField, colorPicker);
                VBox.setMargin(playerFieldPane, new Insets(5, 10, 5, 10));

                playerFieldsContainer.getChildren().add(playerFieldPane);
            }
        } else if (numPlayers < currentNumPlayers) {
            // Remove excess player fields
            while (playerFieldsContainer.getChildren().size() > numPlayers) {
                playerFieldsContainer.getChildren().removeLast();
                players.removeLast();
            }
        }

        updateNotUniqueLabels();
    }

    private void updateNotUniqueLabels() {
        playerNamesNotUnique.setVisible(!playersAreUnique());
        playerColorsNotUnique.setVisible(!colorsAreUnique());
        playButton.setDisable(!areNamesAndColorsUnique());
    }
}
