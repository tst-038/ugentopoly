package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.model.tiles.JailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

public class JailTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element) {
        return new JailTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")));
    }
}
