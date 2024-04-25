package be.ugent.objprog.ugentopoly.model.tile.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.TaxTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import org.jdom2.Element;

public class TaxTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element, GameManager gameManager) {
        return new TaxTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")),
                Integer.parseInt(element.getAttributeValue("amount")), gameManager);
    }
}