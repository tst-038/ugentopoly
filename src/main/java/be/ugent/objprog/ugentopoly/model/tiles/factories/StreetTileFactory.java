package be.ugent.objprog.ugentopoly.model.tiles.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import org.jdom2.Element;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class StreetTileFactory implements TileFactory {
    private final List<Area> areas;

    public StreetTileFactory(List<Area> areas) {
        this.areas = areas;
    }

    @Override
    public Tile createTile(Element element, Game game) {
        int streetCost = Integer.parseInt(element.getAttributeValue("cost"));
        String areaString = element.getAttributeValue("area");
        int rent = Integer.parseInt(element.getAttributeValue("rent0"));

        Area area = areas.stream().filter(ar -> ar.getId().equals(areaString)).findFirst().orElse(null);
        StreetTile streetTile = new StreetTile(element.getAttributeValue("id"), Integer.parseInt(element.getAttributeValue("position")),
                streetCost, area, rent, game);
        if (area != null) {
           area.addTile(streetTile);
        }
        return streetTile;
    }
}