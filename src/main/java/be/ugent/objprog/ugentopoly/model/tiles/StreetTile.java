package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import be.ugent.objprog.ugentopoly.model.interfaces.Rentable;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.LabelUpdatable;
import be.ugent.objprog.ugentopoly.ui.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class StreetTile extends Tile implements UIUpdatable, LabelUpdatable, Buyable, Rentable {

    private final int cost;
    private final Area area;
    private final int rent;
    private Player owner;

    public StreetTile(String id, int position, int cost, Area area, int rent) {
        super(id, position, TileType.STREET);
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
        return rent;
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
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void acceptUIUpdate(UIUpdateVisitor visitor, Node tileNode, Pane rootPane) {
        visitor.visit(this, tileNode, rootPane);
    }

    @Override
    public void updateUI(Node tileNode, Pane rootPane) {
        tileNode.lookup("#area").setStyle("-fx-background-color: " + getArea().color());
        updateLabel(tileNode);
    }
}
