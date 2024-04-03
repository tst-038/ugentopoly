package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import javafx.scene.image.Image;

import java.util.Objects;

public class GoToJailTile extends Tile {
    public GoToJailTile(String id, int position) {
        super(id, position, TileType.GO_TO_JAIL);
    }
    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/go_to_jail.png")));
    }
    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
