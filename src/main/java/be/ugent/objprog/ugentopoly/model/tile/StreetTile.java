package be.ugent.objprog.ugentopoly.model.tile;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.board.Area;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.behaviour.IBuyable;
import be.ugent.objprog.ugentopoly.model.behaviour.IRentable;
import be.ugent.objprog.ugentopoly.model.behaviour.IVisitable;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.manager.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.listener.ILabelChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.IUIChangeVisitor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class StreetTile extends Tile implements UIUpdatable, ILabelChangeListener, IBuyable, IRentable, IVisitable {

    private final int cost;
    private final Area area;
    private final int rent;
    private Player owner;

    public StreetTile(String id, int position, int cost, Area area, int rent, GameManager gameManager) {
        super(id, position, TileType.STREET, gameManager);
        this.cost = cost;
        this.area = area;
        this.rent = rent;
        this.owner = null;
    }

    public int getPrice() {
        return cost;
    }

    public Area getArea() {
        return area;
    }

    public int getRent() {
        return rent * (area.allTilesOwnedBySamePlayer() ? 2 : 1);
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("tile.", "") + ".png")));
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
        tileNode.lookup("#area").setStyle("-fx-background-color: " + getArea().getHexColorString());
        updateLabel(tileNode);
    }

    @Override
    public void onVisit(Player player) {
        if (owner == player) {
            return;
        }
        TileInfoPaneManager tileInfoPaneManager = gameManager.getTileInfoPaneManager();
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
            buy.setDisable(gameManager.getBank().hasInsufficientBalance(player, getPrice()));
            pane.lookup("#close-button").setOnMouseClicked(event -> tileInfoPaneManager.setPaneClosableAndHide());
        }
    }
}
