package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.model.tiles.RailwayTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

public class RailwayTileFactory  implements TileFactory {
    @Override
    public Tile createTile(Element element) {
        return new RailwayTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")),
                Integer.parseInt(element.getAttributeValue("cost")), Integer.parseInt(element.getAttributeValue("rent")));
    }
}