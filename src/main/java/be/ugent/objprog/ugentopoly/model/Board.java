package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.BoardReader;
import be.ugent.objprog.ugentopoly.data.PropertyReader;
import be.ugent.objprog.ugentopoly.exceptions.PropertyReadException;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Board {

    private List<Area> areas;
    private List<Tile> tiles;
    private Settings settings;


    public void init() {
        BoardReader boardReader = new BoardReader();
        settings = boardReader.readSettings();
        areas = boardReader.readAreas();
        tiles = BoardReader.readTiles(areas);

        try (InputStream propertiesInputStream = Ugentopoly.class.getResourceAsStream("ugentopoly.properties")) {
            PropertyReader propertyReader = new PropertyReader(propertiesInputStream);
            for (Tile tile : tiles) {
                String tileName = propertyReader.getTileName(tile.getId());
                tile.setName(tileName);
            }
        } catch (IOException e) {
            throw new PropertyReadException("Failed to read properties file", e);
        }
    }

    public List<Area> getAreas() {
        return areas;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile getTileById(String id) {
        return tiles.stream().filter(tile -> tile.getId().equals(id)).findFirst().orElse(null);
    }

    public Settings getSettings() {
        return settings;
    }
}
