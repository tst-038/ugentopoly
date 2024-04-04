package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import javafx.scene.image.Image;

import java.util.Objects;

public class UtilityTile extends Tile {

    private final int cost;

    public UtilityTile(String id, int position, int cost) {
        super(id, position, TileType.UTILITY);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("tile.", "") + ".png")));
    }

    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
