package be.ugent.objprog.ugentopoly.ui.manager;

import be.ugent.objprog.ugentopoly.model.board.Board;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class BoardManager {
    private final Board board;
    private final UIUpdater uiUpdater;
    private final TileInfoPaneManager tileInfoPaneManager;

    public BoardManager(Board board, UIUpdater uiUpdater, TileInfoPaneManager tileInfoPaneManager) {
        this.board = board;
        this.uiUpdater = uiUpdater;
        this.tileInfoPaneManager = tileInfoPaneManager;
    }

    public void initializeBoard(AnchorPane rootPane) {
        uiUpdater.colorAreaPanes(board.getAreas());
        uiUpdater.updateTiles(board.getTiles());
        uiUpdater.bindJackpot();
        attachTileClickHandlers(rootPane);
    }

    private void attachTileClickHandlers(AnchorPane rootPane) {
        for (Tile tile : board.getTiles()) {
            String tileId = "_" + tile.getPosition();
            Pane tilePane = findTilePane(rootPane, tileId);
            if (tilePane != null) {
                tileInfoPaneManager.attachTileClickHandler(tilePane);
            }
        }
        ((BorderPane) rootPane.getChildren().getFirst()).getCenter().setOnMouseClicked(event -> tileInfoPaneManager.clearTileSelection());
    }

    private Pane findTilePane(Pane parent, String tileId) {
        if (parent.getId() != null && parent.getId().equals(tileId)) {
            return parent;
        }

        return parent.getChildren().stream()
                .filter(Pane.class::isInstance)
                .map(Pane.class::cast)
                .map(childPane -> findTilePane(childPane, tileId))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    public Node findPlayerNode(Player player, AnchorPane rootPane) {
        return rootPane.lookup("#player_" + player.getId());
    }
}