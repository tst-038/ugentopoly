package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.tiles.GoToJailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

public class GoToJailTileFactory implements TileFactory {
    @Override
    public Tile createTile(Element element, Game game) {
        return new GoToJailTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")), game);
    }
}
