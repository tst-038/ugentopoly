package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import javafx.scene.image.Image;

import java.util.Objects;

public class StartTile extends Tile {
    public StartTile(String id, int position) {
        super(id, position, TileType.START);
    }
    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("tile.", "") + ".png")));
    }
    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
