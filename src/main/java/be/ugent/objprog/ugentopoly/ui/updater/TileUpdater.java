package be.ugent.objprog.ugentopoly.ui.updater;

import be.ugent.objprog.ugentopoly.exception.ui.UIUpdateException;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import be.ugent.objprog.ugentopoly.ui.listener.IUIChangeVisitor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TileUpdater {
    private final AnchorPane rootPane;

    public TileUpdater(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public void updateTiles(List<Tile> tiles) {
        IUIChangeVisitor visitor = new UIUpdateVisitor();
        for (Tile tile : tiles) {
            try {
                Node node = rootPane.lookup("#_" + tile.getPosition());
                Parent tileNode = loadTileNode(tile);
                rotateTileNode(tileNode, tile.getOrientation().getRotation());
                tile.acceptUIUpdate(visitor, tileNode, rootPane);
                addTileNodeToRootPane(tileNode);
                addTileNodeToGridOrPane(node, tileNode);
            } catch (Exception e) {
                throw new UIUpdateException("Error updating UI for tile " + tile.getId(), e);
            }
        }
    }

    private Parent loadTileNode(Tile tile) throws IOException {
        URL fxmlFileURL = tile.getFxmlURL();
        FXMLLoader loader = new FXMLLoader(fxmlFileURL);
        return loader.load();
    }

    private void rotateTileNode(Parent tileNode, double rotation) {
        tileNode.getChildrenUnmodifiable().getFirst().setRotate(rotation);
    }

    private void addTileNodeToRootPane(Parent tileNode) {
        rootPane.getChildren().add(tileNode);
    }

    private void addTileNodeToGridOrPane(Node node, Parent tileNode) {
        if (node instanceof GridPane grid) {
            grid.add(tileNode, 0, 0);
        } else if (node instanceof Pane pane) {
            pane.getChildren().add(tileNode);
        }
    }
}
