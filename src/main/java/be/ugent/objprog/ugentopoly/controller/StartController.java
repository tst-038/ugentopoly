package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.PlayerPion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Label playerPionNotUnique;
    @FXML
    private Label playerAmountTitle;
    @FXML
    private Label playerAmount;
    @FXML
    private Label playerColor;
    @FXML
    private Label playerName;
    @FXML
    private Button playButton;

    private List<Player> players;
    private ObservableList<Image> availablePions;
    @FXML
    private void initialize() {
        players = new ArrayList<>();
        availablePions = FXCollections.observableArrayList(List.of(
                ResourceLoader.loadImage("assets/token1.png"),
                ResourceLoader.loadImage("assets/token2.png"),
                ResourceLoader.loadImage("assets/token3.png"),
                ResourceLoader.loadImage("assets/token4.png"),
                ResourceLoader.loadImage("assets/token5.png"),
                ResourceLoader.loadImage("assets/token6.png"),
                ResourceLoader.loadImage("assets/token7.png"),
                ResourceLoader.loadImage("assets/token8.png")
        ));
        initializePionComboBoxes();
        playerSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int numPlayers = newValue.intValue();
            playerAmount.setText(String.format(PropertyReader.getInstance().get("label.player_amount"), numPlayers));
            updatePlayerFields(numPlayers);
        });
        updateText();
    }

    @FXML
    private void handlePlayButtonAction() {
        if (areAllFieldsUnique()) {
            // Pass the list of players to the main class to start the game
            Ugentopoly.startGame(players);
        }
    }

    private void updateText(){
        updatePlayerFields(2);
        playButton.setText(PropertyReader.getInstance().get("button.play"));
        playerAmountTitle.setText(PropertyReader.getInstance().get("label.player_amount_title"));
        playerColor.setText(PropertyReader.getInstance().get("label.color"));
        playerName.setText(PropertyReader.getInstance().get("label.username"));
    }

    private boolean areAllFieldsUnique() {
        return playersAreUnique() && colorsAreUnique() && pionsAreUnique();
    }

    private boolean pionsAreUnique() {
        Set<Image> pionSet = players.stream()
                .map(Player::getPion)
                .filter(java.util.Objects::nonNull)
                .map(PlayerPion::getImage)
                .collect(Collectors.toSet());
        return pionSet.size() == players.size();
    }

    private boolean playersAreUnique() {
        Set<String> playerSet = new HashSet<>(players.stream().map(Player::getName).toList());
        return playerSet.size() == players.size();
    }

    private boolean colorsAreUnique() {
        Set<Color> colorSet = new HashSet<>(players.stream().map(Player::getColor).toList());
        return colorSet.size() == players.size();
    }

    private void initializePionComboBoxes() {
        for (Node node : playerFieldsContainer.getChildren()) {
            if (node instanceof AnchorPane) {
                AnchorPane playerFieldPane = (AnchorPane) node;
                ComboBox<Image> pionComboBox = (ComboBox<Image>) playerFieldPane.getChildren().get(2);
                initializePionComboBox(pionComboBox);
            }
        }
    }

    private void initializePionComboBox(ComboBox<Image> pionComboBox) {
        pionComboBox.setItems(availablePions);

        // Set a custom cell factory to display an ImageView for each Image
        pionComboBox.setCellFactory(param -> new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(item);
                    imageView.setFitHeight(25);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });

        pionComboBox.setButtonCell(new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(item);
                    imageView.setFitHeight(15);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });


    }

    private boolean isImageInUseByOtherPlayer(Image image, Player currentPlayer) {
        // Check if the image is in use by any other player except the current player
        for (Player player : players) {
            if (player != currentPlayer && player.getPion() != null && player.getPion().getImage().equals(image)) {
                return true;
            }
        }
        return false;
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
                usernameField.setText(String.format(PropertyReader.getInstance().get("label.default_player_name"), (i + 1)));
                AnchorPane.setLeftAnchor(usernameField, 0.0);
                AnchorPane.setTopAnchor(usernameField, 2.0);

                ColorPicker colorPicker = new ColorPicker();
                colorPicker.setValue(defaultColors[i]);
                colorPicker.setPrefSize(31, 25);
                colorPicker.setStyle("");
                AnchorPane.setRightAnchor(colorPicker, 60.0);
                AnchorPane.setTopAnchor(colorPicker, 2.0);

                ComboBox<Image> pionComboBox = new ComboBox<>();
                pionComboBox.setPrefSize(25, 25);
                AnchorPane.setRightAnchor(pionComboBox, 0.0);
                AnchorPane.setTopAnchor(pionComboBox, 2.0);
                initializePionComboBox(pionComboBox);




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

                pionComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                    player.setPion(new PlayerPion(player,newValue));
                    updateNotUniqueLabels();
                });

                pionComboBox.addEventFilter(ComboBox.ON_HIDING, event -> {
                    Image newImage = pionComboBox.getSelectionModel().getSelectedItem();
                    if (isImageInUseByOtherPlayer(newImage, player)) {
                        // If the new selection is already in use by another player, revert back to the old selection
                        pionComboBox.getSelectionModel().selectPrevious();
                    } else {
                        // Update the player's pion with the new selection
                        player.setPion(new PlayerPion(player, newImage));
                    }
                });

                playerFieldPane.getChildren().addAll(usernameField, colorPicker, pionComboBox);
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
        playerPionNotUnique.setVisible(!pionsAreUnique());
        playButton.setDisable(!areAllFieldsUnique());
    }
}
