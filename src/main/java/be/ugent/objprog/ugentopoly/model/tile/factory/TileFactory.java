package be.ugent.objprog.ugentopoly.model.tile.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import org.jdom2.Element;

public interface TileFactory {
    Tile createTile(Element element, GameManager gameManager);
}
