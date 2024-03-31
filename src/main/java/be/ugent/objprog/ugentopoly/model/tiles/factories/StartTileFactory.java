package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.model.tiles.StartTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

public class StartTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element) {
        return new StartTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")));
    }
}