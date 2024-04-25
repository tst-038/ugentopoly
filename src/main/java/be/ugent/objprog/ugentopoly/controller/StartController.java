package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.ui.PlayerPion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
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
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 4;
    private static final Color[] DEFAULT_COLORS = {
            Color.GREEN, Color.RED, Color.CYAN, Color.MAGENTA
    };

    @FXML private Slider playerSlider;
    @FXML private VBox playerFieldsContainer;
    @FXML private Label playerNamesNotUnique;
    @FXML private Label playerColorsNotUnique;
    @FXML private Label playerPionNotUnique;
    @FXML private Label playerAmountTitle;
    @FXML private Label playerAmount;
    @FXML private Label playerColor;
    @FXML private Label playerName;
    @FXML private Button playButton;

    private Ugentopoly ugentopoly;
    private PropertyReader propertyReader;
    private Settings settings;
    private List<Player> players;
    private ObservableList<Image> availablePions;

    public StartController(Ugentopoly ugentopoly, PropertyReader propertyReader, Settings settings) {
        this.ugentopoly = ugentopoly;
        this.propertyReader = propertyReader;
        this.settings = settings;
    }

    @FXML
    private void initialize() {
        players = new ArrayList<>();
        availablePions = FXCollections.observableArrayList(loadPionImages());
        initializePionComboBoxes();
        playerSlider.valueProperty().addListener((observable, oldValue, newValue) -> updatePlayerAmount(newValue.intValue()));
        playerSlider.setMax(MAX_PLAYERS);
    }

    public void updateFields() {
        updateText();
    }

    @FXML
    private void handlePlayButtonAction() {
        if (areAllFieldsUnique()) {
            ugentopoly.startGame(players);
        }
    }

    private void updateText() {
        updatePlayerFields(MIN_PLAYERS);
        playButton.setText(propertyReader.get("button.play"));
        playerAmountTitle.setText(propertyReader.get("label.player_amount_title"));
        playerColor.setText(propertyReader.get("label.color"));
        playerName.setText(propertyReader.get("label.username"));
        String notUnique = propertyReader.get("label.not_unique");
        playerColorsNotUnique.setText(notUnique);
        playerPionNotUnique.setText(notUnique);
        playerNamesNotUnique.setText(notUnique);
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
            if (node instanceof AnchorPane playerFieldPane && playerFieldPane.getChildren().get(2) instanceof ComboBox<?>){
                ComboBox<Image> pionComboBox = (ComboBox<Image>) playerFieldPane.getChildren().get(2);
                initializePionComboBox(pionComboBox);
            }
        }
    }

    private void initializePionComboBox(ComboBox<Image> pionComboBox) {
        pionComboBox.setItems(availablePions);
        pionComboBox.setCellFactory(param -> createPionListCell());
        pionComboBox.setButtonCell(createPionListCell());
        pionComboBox.addEventHandler(ComboBoxBase.ON_HIDDEN, event -> handlePionComboBoxHidden(event, pionComboBox));
    }

    private ListCell<Image> createPionListCell() {
        return new ListCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(item);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        };
    }

    private void handlePionComboBoxHidden(Event event, ComboBox<Image> pionComboBox) {
        Player player = getPlayerForComboBox(pionComboBox);
        if (player != null) {
            Image selectedImage = pionComboBox.getSelectionModel().getSelectedItem();
            if (isImageInUseByOtherPlayer(selectedImage, player)) {
                pionComboBox.getSelectionModel().selectPrevious();
            } else {
                player.setPion(new PlayerPion(player, selectedImage));
            }
        }
    }

    private Player getPlayerForComboBox(ComboBox<Image> pionComboBox) {
        int index = playerFieldsContainer.getChildren().indexOf(pionComboBox.getParent());
        if (index >= 0 && index < players.size()) {
            return players.get(index);
        }
        return null;
    }

    private boolean isImageInUseByOtherPlayer(Image image, Player currentPlayer) {
        return players.stream()
                .filter(player -> player != currentPlayer)
                .anyMatch(player -> player.getPion() != null && player.getPion().getImage() != null && player.getPion().getImage().equals(image));
    }

    private void updatePlayerAmount(int numPlayers) {
        playerAmount.setText(String.format(propertyReader.get("label.player_amount"), numPlayers));
        updatePlayerFields(numPlayers);
    }

    private void updatePlayerFields(int numPlayers) {
        int currentNumPlayers = playerFieldsContainer.getChildren().size();

        if (numPlayers > currentNumPlayers) {
            for (int i = currentNumPlayers; i < numPlayers; i++) {
                AnchorPane playerFieldPane = createPlayerField(i);
                playerFieldsContainer.getChildren().add(playerFieldPane);
            }
        } else if (numPlayers < currentNumPlayers) {
            while (playerFieldsContainer.getChildren().size() > numPlayers) {
                playerFieldsContainer.getChildren().removeLast();
                players.removeLast();
            }
        }

        updateNotUniqueLabels();
    }

    private AnchorPane createPlayerField(int index) {
        AnchorPane playerFieldPane = new AnchorPane();
        playerFieldPane.setMinHeight(50);
        playerFieldPane.setPrefSize(200, 50);

        TextField usernameField = createUsernameField(index);
        ColorPicker colorPicker = createColorPicker(index);
        ComboBox<Image> pionComboBox = createPionComboBox();

        Player player = createPlayer(index, usernameField.getText(), colorPicker.getValue());

        usernameField.textProperty().addListener(createUsernameListener(player));
        colorPicker.valueProperty().addListener(createColorListener(player));
        pionComboBox.valueProperty().addListener(createPionListener(player));

        playerFieldPane.getChildren().addAll(usernameField, colorPicker, pionComboBox);
        VBox.setMargin(playerFieldPane, new Insets(0, 10, 0, 10));

        return playerFieldPane;
    }

    private TextField createUsernameField(int index) {
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(125);
        usernameField.setText(String.format(propertyReader.get("label.default_player_name"), (index + 1)));
        AnchorPane.setLeftAnchor(usernameField, 0.0);
        AnchorPane.setTopAnchor(usernameField, 10.0);
        return usernameField;
    }

    private ColorPicker createColorPicker(int index) {
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(DEFAULT_COLORS[index]);
        colorPicker.setPrefSize(25, 25);
        colorPicker.setStyle("");
        AnchorPane.setRightAnchor(colorPicker, 60.0);
        AnchorPane.setTopAnchor(colorPicker, 10.0);
        return colorPicker;
    }

    private ComboBox<Image> createPionComboBox() {
        ComboBox<Image> pionComboBox = new ComboBox<>();
        pionComboBox.setMinHeight(40);
        pionComboBox.setPrefSize(30, 40);
        AnchorPane.setRightAnchor(pionComboBox, 0.0);
        AnchorPane.setTopAnchor(pionComboBox, 3.0);
        initializePionComboBox(pionComboBox);
        return pionComboBox;
    }

    private Player createPlayer(int index, String name, Color color) {
        Player player;
        if (index < players.size()) {
            player = players.get(index);
            player.setName(name);
            player.setColor(color);
        } else {
            player = new Player(index, name, color, settings.getStartingBalance());
            players.add(player);
        }
        return player;
    }

    private javafx.beans.value.ChangeListener<String> createUsernameListener(Player player) {
        return (observable, oldValue, newValue) -> {
            player.setName(newValue);
            updateNotUniqueLabels();
        };
    }

    private javafx.beans.value.ChangeListener<Color> createColorListener(Player player) {
        return (observable, oldValue, newValue) -> {
            player.setColor(newValue);
            updateNotUniqueLabels();
        };
    }

    private javafx.beans.value.ChangeListener<Image> createPionListener(Player player) {
        return (observable, oldValue, newValue) -> {
            player.setPion(new PlayerPion(player, newValue));
            updateNotUniqueLabels();
        };
    }

    private void updateNotUniqueLabels() {
        playerNamesNotUnique.setVisible(!playersAreUnique());
        playerColorsNotUnique.setVisible(!colorsAreUnique());
        playerPionNotUnique.setVisible(!pionsAreUnique());
        playButton.setDisable(!areAllFieldsUnique());
    }

    private List<Image> loadPionImages() {
        return List.of(
                ResourceLoader.loadImage("assets/token1.png"),
                ResourceLoader.loadImage("assets/token2.png"),
                ResourceLoader.loadImage("assets/token3.png"),
                ResourceLoader.loadImage("assets/token4.png"),
                ResourceLoader.loadImage("assets/token5.png"),
                ResourceLoader.loadImage("assets/token6.png"),
                ResourceLoader.loadImage("assets/token7.png"),
                ResourceLoader.loadImage("assets/token8.png")
        );
    }
}