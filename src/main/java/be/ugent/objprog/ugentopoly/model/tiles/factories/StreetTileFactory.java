package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

import java.util.List;

public class StreetTileFactory implements TileFactory {
    private final List<Area> areas;

    public StreetTileFactory(List<Area> areas) {
        this.areas = areas;
    }

    @Override
    public Tile createTile(Element element) {
        int streetCost = Integer.parseInt(element.getAttributeValue("cost"));
        String area = element.getAttributeValue("area");
        int rent = Integer.parseInt(element.getAttributeValue("rent0"));
        return new StreetTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")),
                streetCost, areas.stream().filter(ar -> ar.getId().equals(area)).findFirst().orElse(null), rent);
    }
}