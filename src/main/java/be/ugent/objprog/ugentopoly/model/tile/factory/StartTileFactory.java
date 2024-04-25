package be.ugent.objprog.ugentopoly.model.tile.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.StartTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import org.jdom2.Element;

public class StartTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element, GameManager gameManager) {
        return new StartTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")), gameManager);
    }
}