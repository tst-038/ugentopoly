package be.ugent.objprog.ugentopoly.model.tile;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.listener.LabelChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.UIChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.UIChangeVisitor;
import be.ugent.objprog.ugentopoly.ui.manager.TileInfoPaneManager;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class StartTile extends Tile implements UIChangeListener, LabelChangeListener {
    public StartTile(String id, int position, GameManager gameManager) {
        super(id, position, TileType.START, gameManager);
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
    }

    @Override
    public void onVisit(Player player) {
        TileInfoPaneManager tileInfoPaneManager = gameManager.getTileInfoPaneManager();
        tileInfoPaneManager.showTileInfo(this, true);

        AnchorPane pane = tileInfoPaneManager.getTileInfoPane();
        pane.lookup("#close-button").setOnMouseClicked(event -> {
            gameManager.getBank().deposit(player, gameManager.getSettings().getStartBonus(), true);
            tileInfoPaneManager.setPaneClosableAndHide();
        });
    }
}
