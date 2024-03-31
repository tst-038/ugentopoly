package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.model.tiles.ChanceTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

public class ChanceTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element) {
        return new ChanceTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")));
    }
}