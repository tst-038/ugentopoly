package be.ugent.objprog.ugentopoly.model.tile;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.behaviour.Visitable;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.listener.ImageChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.LabelChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.UIChangeVisitor;
import be.ugent.objprog.ugentopoly.ui.manager.TileInfoPaneManager;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;
import java.util.stream.IntStream;

public class ChanceTile extends Tile implements UIUpdatable, LabelChangeListener, ImageChangeListener, Visitable {
    public ChanceTile(String id, int position, GameManager gameManager) {
        super(id, position, TileType.CHANCE, gameManager);
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("tile.", "") + ".png")));
    }

    @Override
    public void accept(TileVisitor visitor, boolean onVisit) {
        visitor.visit(this, onVisit);
    }

    @Override
    public void acceptUIUpdate(UIChangeVisitor visitor, Node tileNode, Pane rootPane) {
        visitor.visit(this, tileNode, rootPane);
    }

    @Override
    public void updateUI(Node tileNode, Pane rootPane) {
        updateLabel(tileNode);
        updateImage(tileNode);
    }

    @Override
    public void onVisit(Player player) {
        TileInfoPaneManager tileInfoPaneManager = gameManager.getTileInfoPaneManager();
        tileInfoPaneManager.showTileInfo(this, true);
        AnchorPane pane = tileInfoPaneManager.getTileInfoPane();

        pane.lookup("#close-button").setOnMouseClicked(event -> { // Makes sure that the previous tile info pane is closed and hidden
            tileInfoPaneManager.setPaneClosableAndHide();
            IntStream.range(0, player.getInventory().getCards().size()).forEachOrdered(i -> player.getInventory().getCards().get(i).execute(player, gameManager));
        });
    }
}
