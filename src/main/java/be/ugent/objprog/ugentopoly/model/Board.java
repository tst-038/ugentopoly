package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.data.readers.BoardReader;
import be.ugent.objprog.ugentopoly.data.readers.CardsReader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;

import java.util.List;

public class Board {

    private List<Area> areas;
    private List<Tile> tiles;

    // Initialize the board
    // Reads the areas, tiles and settings from the XML files
    // Reads and sets the names of the tiles from the properties file
    public void init() {
        BoardReader boardReader = new BoardReader();
        areas = boardReader.readAreas();
        tiles = BoardReader.readTiles(areas);

        PropertyReader propertyReader = PropertyReader.getInstance();
        for (Tile tile : tiles) {
            String tileName = propertyReader.getTileName(tile.getId());
            tile.setName(tileName);
        }

        new CardsReader().readCards();
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

    public boolean ownsAllInArea(Player player, Area area) {
        for (Tile tile : tiles) {
            if (tile.getType() == TileType.STREET) {
                StreetTile streetTile = (StreetTile) tile;
                if (streetTile.getArea().equals(area) && !streetTile.getOwner().equals(player)) {
                    return false;
                }
            }
        }
        return true;
    }
}
