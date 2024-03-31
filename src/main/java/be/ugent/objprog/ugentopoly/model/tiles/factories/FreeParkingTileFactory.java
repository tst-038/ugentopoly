package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.model.tiles.FreeParkingTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

public class FreeParkingTileFactory  implements TileFactory {
    @Override
    public Tile createTile(Element element) {
        return new FreeParkingTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")));
    }
}
