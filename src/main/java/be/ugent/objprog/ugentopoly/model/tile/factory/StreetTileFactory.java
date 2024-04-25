package be.ugent.objprog.ugentopoly.model.tile.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.board.Area;
import be.ugent.objprog.ugentopoly.model.tile.StreetTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import org.jdom2.Element;

import java.util.List;

public class StreetTileFactory implements TileFactory {
    private final List<Area> areas;

    public StreetTileFactory(List<Area> areas) {
        this.areas = areas;
    }

    @Override
    public Tile createTile(Element element, GameManager gameManager) {
        int streetCost = Integer.parseInt(element.getAttributeValue("cost"));
        String areaString = element.getAttributeValue("area");
        int rent = Integer.parseInt(element.getAttributeValue("rent0"));

        Area area = areas.stream().filter(ar -> ar.getId().equals(areaString)).findFirst().orElse(null);
        StreetTile streetTile = new StreetTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")),
                streetCost, area, rent, gameManager);
        if (area != null) {
           area.addTile(streetTile);
        }
        return streetTile;
    }
}