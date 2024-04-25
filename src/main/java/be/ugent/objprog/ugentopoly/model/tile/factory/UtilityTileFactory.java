package be.ugent.objprog.ugentopoly.model.tile.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import be.ugent.objprog.ugentopoly.model.tile.UtilityTile;
import org.jdom2.Element;

public class UtilityTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element, GameManager gameManager) {
        return new UtilityTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")), Integer.parseInt(element.getAttributeValue("cost")), gameManager);
    }
}