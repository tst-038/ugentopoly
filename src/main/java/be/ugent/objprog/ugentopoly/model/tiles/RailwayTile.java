package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import javafx.scene.image.Image;

import java.util.Objects;

public class RailwayTile extends Tile {

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
}
