package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

public interface TileFactory {
    Tile createTile(Element element, Game game);
}
