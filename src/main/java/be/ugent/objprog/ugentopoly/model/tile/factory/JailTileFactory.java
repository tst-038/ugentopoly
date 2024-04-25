package be.ugent.objprog.ugentopoly.model.tile.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.JailTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import org.jdom2.Element;

public class JailTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element, GameManager gameManager) {
        return new JailTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")), gameManager);
    }
}
