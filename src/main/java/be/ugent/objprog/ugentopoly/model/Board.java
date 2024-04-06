package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.data.BoardReader;
import be.ugent.objprog.ugentopoly.data.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;

import java.util.List;

public class Board {

    private List<Area> areas;
    private List<Tile> tiles;

    public void init() {
        BoardReader boardReader = new BoardReader();
        areas = boardReader.readAreas();
        tiles = BoardReader.readTiles(areas);
        boardReader.readSettings();
        PropertyReader propertyReader = PropertyReader.getInstance();
        for (Tile tile : tiles) {
            String tileName = propertyReader.getTileName(tile.getId());
            tile.setName(tileName);
        }
    }

    public List<Area> getAreas() {
        return areas;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile getTileByPosition(int position) {
        return tiles.stream().filter(tile -> tile.getPosition() == position).findFirst().orElse(null);
    }
}
