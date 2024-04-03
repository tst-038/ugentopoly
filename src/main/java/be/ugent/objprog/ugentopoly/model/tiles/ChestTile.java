package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import javafx.scene.image.Image;

import java.util.Objects;

public class ChestTile extends Tile {
    public ChestTile(String id, int position) {
        super(id.replaceAll("[0-9]]", ""), position, TileType.CHEST);
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("(tile.)|\\d", "") + ".png")));
    }

    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }

}
