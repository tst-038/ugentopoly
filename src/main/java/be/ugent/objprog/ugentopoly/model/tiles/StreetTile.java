package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import be.ugent.objprog.ugentopoly.model.interfaces.Rentable;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.interfaces.LabelUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class StreetTile extends Tile implements UIUpdatable, LabelUpdatable, Buyable, Rentable, Visitable {

    private final int cost;
    private final Area area;
    private final int rent;
    private Player owner;

    public StreetTile(String id, int position, int cost, Area area, int rent, Game game) {
        super(id, position, TileType.STREET, game);
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
    public void acceptUIUpdate(UIUpdateVisitor visitor, Node tileNode, Pane rootPane) {
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
        TileInfoPaneManager tileInfoPaneManager = game.getTileInfoPaneManager();
        tileInfoPaneManager.showTileInfo(this, true);
        AnchorPane pane = tileInfoPaneManager.getTileInfoPane();

        if (owner != null) {
            pane.lookup("#pay-rent-button").setOnMouseClicked(event -> {
                payRent(player, game);
                tileInfoPaneManager.setPaneClosableAndHide();
            });
        } else {
            Button buy = (Button) pane.lookup("#buy-button");
            buy.setOnMouseClicked(event -> {
                buy(player, game);
                tileInfoPaneManager.setPaneClosableAndHide();
            });
            buy.setDisable(game.getBank().hasInsufficientBalance(player, getPrice()));
            pane.lookup("#close-button").setOnMouseClicked(event -> tileInfoPaneManager.setPaneClosableAndHide());
        }
    }
}
