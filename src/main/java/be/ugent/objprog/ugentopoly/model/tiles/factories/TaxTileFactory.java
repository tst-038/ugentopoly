package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.model.tiles.TaxTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

public class TaxTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element) {
        return new TaxTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")),
                Integer.parseInt(element.getAttributeValue("amount")));
    }
}