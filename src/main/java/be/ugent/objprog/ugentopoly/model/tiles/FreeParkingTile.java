package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import javafx.scene.image.Image;

import java.util.Objects;

public class FreeParkingTile extends Tile {
    public FreeParkingTile(String id, int position) {
        super(id, position, TileType.FREE_PARKING);
    }
    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/free_parking.png")));
    }
    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
