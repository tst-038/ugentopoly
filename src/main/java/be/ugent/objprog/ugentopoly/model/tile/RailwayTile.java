package be.ugent.objprog.ugentopoly.model.tile;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.behaviour.IBuyable;
import be.ugent.objprog.ugentopoly.model.behaviour.IRentable;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.listener.IImageChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.ILabelChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.IUIChangeVisitor;
import be.ugent.objprog.ugentopoly.ui.manager.TileInfoPaneManager;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class RailwayTile extends Tile implements UIUpdatable, ILabelChangeListener, IImageChangeListener, IBuyable, IRentable {

    private final int price;
    private final int rent;
    private Player owner;

    public RailwayTile(String id, int position, int cost, int rent, GameManager gameManager) {
        super(id, position, TileType.RAILWAY, gameManager);
        this.price = cost;
        this.rent = rent;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return (int) (rent * Math.pow(2, getOwner().getInventory().getOwnedRailways() - 1.));
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
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public void buy(Player player, GameManager gameManager) {
        IBuyable.super.buy(player, gameManager);
        player.getInventory().addOwnedRailway();
    }

    @Override
    public void onVisit(Player player) {
        TileInfoPaneManager tileInfoPaneManager = gameManager.getTileInfoPaneManager();
        if (owner == player) {
            tileInfoPaneManager.notifyInfoPaneClosed();
            return;
        }
        tileInfoPaneManager.showTileInfo(this, true);
        AnchorPane pane = tileInfoPaneManager.getTileInfoPane();

        if (owner != null) {
            pane.lookup("#pay-rent-button").setOnMouseClicked(event -> {
                payRent(player, gameManager);
                tileInfoPaneManager.setPaneClosableAndHide();
            });
        } else {
            Button buy = (Button) pane.lookup("#buy-button");
            buy.setOnMouseClicked(event -> {
                buy(player, gameManager);
                tileInfoPaneManager.setPaneClosableAndHide();
            });
            pane.lookup("#close-button").setOnMouseClicked(event -> tileInfoPaneManager.setPaneClosableAndHide());
        }
    }
}
