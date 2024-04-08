package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileInfoPaneVisitor;
import be.ugent.objprog.ugentopoly.ui.PlayerPion;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GameController {
    @FXML
    private Group logbookRoot;

    private Board board;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane tileInfoPane;

    private TileInfoPaneVisitor tileInfoPaneUpdater;

    private Pane currentlySelectedTile = null;


    private Map<Player, PlayerPion> playerPionMap;

    private void attachTileClickHandlers() {
        for (Tile tile : board.getTiles()) {
            String tileId = "_" + tile.getPosition();
            Pane tilePane = findTilePane(rootPane, tileId);
            if (tilePane != null) {
                attachTileClickHandler(tilePane);
            }
        }
        // Attach click handler to the root pane to close the info pane when clicking outside of a tile
        ((BorderPane) rootPane.getChildren().getFirst()).getCenter().setOnMouseClicked(event -> hideTileInfoPane());
    }

    private Pane findTilePane(Pane parent, String tileId) {
        if (parent.getId() != null && parent.getId().equals(tileId)) {
            return parent;
        }

        for (Node node : parent.getChildren()) {
            if (node instanceof Pane childPane) {
                Pane foundPane = findTilePane(childPane, tileId);
                if (foundPane != null) {
                    return foundPane;
                }
            }
        }

        return null;
    }

    private void attachTileClickHandler(Pane tilePane) {
        tilePane.setOnMouseClicked(event -> showTileInfo(tilePane));
    }

    private void showTileInfo(Pane tilePane) {
        String tileId = tilePane.getId();

        if (currentlySelectedTile != null && currentlySelectedTile.getId().equals(tileId)) {
            // If the clicked tile is already selected, close the info pane
            hideTileInfoPane();
        } else {
            // If a different tile is clicked, update the info pane and show it
            Tile tile = board.getTileByPosition(Integer.parseInt(tileId.replace("_", "")));
            if (tile != null) {
                tile.accept(tileInfoPaneUpdater);
                tileInfoPane.setVisible(true);

                if (currentlySelectedTile != null) {
                    currentlySelectedTile.getStyleClass().remove("tile-selected");
                }

                tilePane.getStyleClass().add("tile-selected");
                currentlySelectedTile = tilePane;
            }
        }
    }

    private void hideTileInfoPane() {
        tileInfoPane.setVisible(false);
        if (currentlySelectedTile == null) {
            return;
        }
        currentlySelectedTile.getStyleClass().remove("tile-selected");
        currentlySelectedTile = null;
    }

    public void initializeBoard(Board board, List<Player> players) {
        this.board = board;
        tileInfoPaneUpdater = new TileInfoPaneVisitor(tileInfoPane);
        UIUpdater uiUpdater = UIUpdater.getInstance(rootPane);
        uiUpdater.colorAreaPanes(board.getAreas());
        uiUpdater.updateTiles(board.getTiles());
        uiUpdater.updatePlayers(players);
        Bank.getInstance().initializeBalances(players);
        initializePlayerPions(players);

        attachTileClickHandlers();
    }

    private void movePlayerPion(Player player, int steps) {
        PlayerPion playerPion = getPlayerPion(player);
        if (playerPion != null) {
            int newPosition = (player.getPosition() + steps) % board.getTiles().size();
            player.setPosition(newPosition);

            Tile newTile = board.getTileByPosition(newPosition);
            HBox newPionContainer = (HBox) rootPane.lookup("#_" + newTile.getPosition()).lookup("#pionContainer");

            TranslateTransition transition = new TranslateTransition(Duration.millis(500), playerPion);
            transition.setToX(newPionContainer.getLayoutX() - playerPion.getLayoutX());
            transition.setToY(newPionContainer.getLayoutY() - playerPion.getLayoutY());
            transition.setOnFinished(e -> {
                playerPion.pionContainer.getChildren().remove(playerPion);
                newPionContainer.getChildren().add(playerPion);
                playerPion.pionContainer = newPionContainer;
                // Voer eventuele aanvullende acties uit
                // ...
            });
            transition.play();
        }
    }

    private void initializePlayerPions(List<Player> players) {
        playerPionMap = new HashMap<>();
        for (Player player : players) {
            Image pionImage = new Image(Objects.requireNonNull(ResourceLoader.loadResource("assets/token" + (playerPionMap.size() + 1) + ".png")));
            HBox pionContainer = (HBox) rootPane.lookup("#_1").lookup("#pionContainer");
            PlayerPion playerPion = new PlayerPion(player, pionImage, pionContainer);
            playerPionMap.put(player, playerPion);
            pionContainer.toFront();
        }
    }

    private PlayerPion getPlayerPion(Player player) {
        return playerPionMap.get(player);
    }

    @FXML
    private void handleLogbookButtonClicked() {
        logbookRoot.setVisible(!logbookRoot.isVisible());
    }
}