package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.tiles.ChestTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

public class ChestTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element, Game game) {
        return new ChestTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")), game);
    }
}
