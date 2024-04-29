package be.ugent.objprog.ugentopoly.model.tile;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.listener.ImageChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.LabelChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.UIChangeVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class JailTile extends Tile implements UIUpdatable, LabelChangeListener, ImageChangeListener {

    public JailTile(String id, int position, GameManager gameManager) {
        super(id, position, TileType.JAIL, gameManager);
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
        gameManager.getTileInfoPaneManager().notifyInfoPaneClosed();
    }
}
