package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.UtilityTile;
import org.jdom2.Element;

public class UtilityTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element, Game game) {
        return new UtilityTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")), Integer.parseInt(element.getAttributeValue("cost")), game);
    }
}