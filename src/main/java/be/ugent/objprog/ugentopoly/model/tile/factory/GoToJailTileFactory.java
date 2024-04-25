package be.ugent.objprog.ugentopoly.model.tile.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.GoToJailTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import org.jdom2.Element;

public class GoToJailTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element, GameManager gameManager) {
        return new GoToJailTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")), gameManager);
    }
}
