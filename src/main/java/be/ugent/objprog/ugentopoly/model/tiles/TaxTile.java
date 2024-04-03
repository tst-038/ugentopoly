package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import javafx.scene.image.Image;

import java.util.Objects;

public class TaxTile extends Tile {
    private final int amount;

    public TaxTile(String id, int position, int amount) {
        super(id, position, TileType.TAX);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("(tile.)|\\d", "") + ".png")));
    }
    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
