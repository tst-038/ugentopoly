package be.ugent.objprog.ugentopoly.model.tile.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.RailwayTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import org.jdom2.Element;

public class RailwayTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element, GameManager gameManager) {
        return new RailwayTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")),
                Integer.parseInt(element.getAttributeValue("cost")), Integer.parseInt(element.getAttributeValue("rent")), gameManager);
    }
}