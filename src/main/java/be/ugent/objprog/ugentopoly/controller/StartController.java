package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.reader.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.ui.element.PlayerPawn;
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
    @FXML private Label playerPawnNotUnique;
    @FXML private Label playerAmountTitle;
    @FXML private Label playerAmount;
    @FXML private Label playerColor;
    @FXML private Label playerName;
    @FXML private Label playerPawn;
    @FXML private Button playButton;

    private final Ugentopoly ugentopoly;
    private final PropertyReader propertyReader;
    private final Settings settings;
    private List<Player> players;
    private ObservableList<Image> availablePawns;

    public StartController(Ugentopoly ugentopoly, PropertyReader propertyReader, Settings settings) {
        this.ugentopoly = ugentopoly;
        this.propertyReader = propertyReader;
        this.settings = settings;
    }

    @FXML
    private void initialize() {
        players = new ArrayList<>();
        availablePawns = FXCollections.observableArrayList(loadPawnImages());
        initializePawnComboBoxes();
        playerSlider.valueProperty().addListener((observable, oldValue, newValue) -> updatePlayerAmount(newValue.intValue()));
        playerSlider.setMax(MAX_PLAYERS);
    }

    public void updateFields() {
        updateText();
    }

    @FXML
    private void handlePlayButtonAction() {
        if (areAllFieldsUnique()) {
            playButton.setDisable(true);
            ugentopoly.startGame(players);
        }
    }

    private void updateText() {
        updatePlayerFields(MIN_PLAYERS);
        playButton.setText(propertyReader.get("button.play"));
        playerAmountTitle.setText(propertyReader.get("label.player_amount_title"));
        playerColor.setText(propertyReader.get("label.color"));
        playerName.setText(propertyReader.get("label.username"));
        playerPawn.setText(propertyReader.get("label.pawn"));
        String notUnique = propertyReader.get("label.not_unique");
        playerColorsNotUnique.setText(notUnique);
        playerPawnNotUnique.setText(notUnique);
        playerNamesNotUnique.setText(notUnique);
    }

    private boolean areAllFieldsUnique() {
        return playersAreUnique() && colorsAreUnique() && pawnsAreUnique();
    }

    private boolean pawnsAreUnique() {
        Set<Image> pawnSet = players.stream()
                .map(Player::getPawn)
                .filter(java.util.Objects::nonNull)
                .map(PlayerPawn::getImage)
                .collect(Collectors.toSet());
        return pawnSet.size() == players.size();
    }

    private boolean playersAreUnique() {
        Set<String> playerSet = new HashSet<>(players.stream().map(Player::getName).toList());
        return playerSet.size() == players.size();
    }

    private boolean colorsAreUnique() {
        Set<Color> colorSet = new HashSet<>(players.stream().map(Player::getColor).toList());
        return colorSet.size() == players.size();
    }

    private void initializePawnComboBoxes() {
        for (Node node : playerFieldsContainer.getChildren()) {
            if (node instanceof AnchorPane playerFieldPane && playerFieldPane.getChildren().get(2) instanceof ComboBox<?>){
                ComboBox<Image> pawnComboBox = (ComboBox<Image>) playerFieldPane.getChildren().get(2);
                initializePawnComboBox(pawnComboBox);
            }
        }
    }

    private void initializePawnComboBox(ComboBox<Image> pawnComboBox) {
        pawnComboBox.setItems(availablePawns);
        pawnComboBox.setCellFactory(param -> createPawnListCell());
        pawnComboBox.setButtonCell(createPawnListCell());
        pawnComboBox.addEventHandler(ComboBoxBase.ON_HIDDEN, event -> handlePawnComboBoxHidden(event, pawnComboBox));
    }

    private ListCell<Image> createPawnListCell() {
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

    private void handlePawnComboBoxHidden(Event event, ComboBox<Image> pawnComboBox) {
        Player player = getPlayerForComboBox(pawnComboBox);
        if (player != null) {
            Image selectedImage = pawnComboBox.getSelectionModel().getSelectedItem();
            if (isImageInUseByOtherPlayer(selectedImage, player)) {
                pawnComboBox.getSelectionModel().clearSelection();
                player.setPawn(null);
            } else {
                player.setPawn(new PlayerPawn(player, selectedImage));
            }
            updateNotUniqueLabels();
        }
    }

    private Player getPlayerForComboBox(ComboBox<Image> pawnComboBox) {
        int index = playerFieldsContainer.getChildren().indexOf(pawnComboBox.getParent());
        if (index >= 0 && index < players.size()) {
            return players.get(index);
        }
        return null;
    }

    private boolean isImageInUseByOtherPlayer(Image image, Player currentPlayer) {
        return players.stream()
                .filter(player -> player != currentPlayer)
                .anyMatch(player -> player.getPawn() != null &&  image != null && image.equals(player.getPawn().getImage()));
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
        ComboBox<Image> pawnComboBox = createPawnComboBox();

        Player player = createPlayer(index, usernameField.getText(), colorPicker.getValue());

        usernameField.textProperty().addListener(createUsernameListener(player));
        colorPicker.valueProperty().addListener(createColorListener(player));
        pawnComboBox.valueProperty().addListener(createPawnListener(player));

        playerFieldPane.getChildren().addAll(usernameField, colorPicker, pawnComboBox);
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

    private ComboBox<Image> createPawnComboBox() {
        ComboBox<Image> pawnComboBox = new ComboBox<>();
        pawnComboBox.setMinHeight(40);
        pawnComboBox.setPrefSize(30, 40);
        AnchorPane.setRightAnchor(pawnComboBox, 0.0);
        AnchorPane.setTopAnchor(pawnComboBox, 3.0);
        initializePawnComboBox(pawnComboBox);
        return pawnComboBox;
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

    private javafx.beans.value.ChangeListener<Image> createPawnListener(Player player) {
        return (observable, oldValue, newValue) -> {
            player.setPawn(new PlayerPawn(player, newValue));
            updateNotUniqueLabels();
        };
    }

    private void updateNotUniqueLabels() {
        playerNamesNotUnique.setVisible(!playersAreUnique());
        playerColorsNotUnique.setVisible(!colorsAreUnique());
        playerPawnNotUnique.setVisible(!pawnsAreUnique());
        playButton.setDisable(!areAllFieldsUnique());
    }

    private List<Image> loadPawnImages() {
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