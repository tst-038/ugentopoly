package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.ImageUpdatable;
import be.ugent.objprog.ugentopoly.ui.LabelUpdatable;
import be.ugent.objprog.ugentopoly.ui.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class RailwayTile extends Tile implements UIUpdatable, LabelUpdatable, ImageUpdatable {

    private final int price;
    private final int rent;

    public RailwayTile(String id, int position, int cost, int rent) {
        super(id, position, TileType.RAILWAY);
        this.price = cost;
        this.rent = rent;
    }

    public int getPrice() {
        return price;
    }
    public int getRent() { return rent;}
    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("(tile.)|\\d", "") + ".png")));
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
        updateLabel(tileNode);
        updateImage(tileNode);
    }
}
