package be.ugent.objprog.ugentopoly.model.tile;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.behaviour.ITaxable;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.manager.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.listener.IImageChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.ILabelChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.IUIChangeVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class TaxTile extends Tile implements UIUpdatable, ILabelChangeListener, IImageChangeListener, ITaxable {
    private final int amount;

    public TaxTile(String id, int position, int amount, GameManager gameManager) {
        super(id, position, TileType.TAX, gameManager);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("(tile.)|\\d", "") + ".png")));
    }

    @Override
    public void accept(TileVisitor visitor, boolean onVisit) {
        visitor.visit(this, onVisit);
    }

    @Override
    public void acceptUIUpdate(IUIChangeVisitor visitor, Node tileNode, Pane rootPane) {
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

        pane.lookup("#tax-pay-button").setOnMouseClicked(event -> {
            payTax(player, gameManager);
            tileInfoPaneManager.setPaneClosableAndHide();
        });
    }
}
