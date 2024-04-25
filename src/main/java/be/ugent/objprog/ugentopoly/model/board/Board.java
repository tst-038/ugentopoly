package be.ugent.objprog.ugentopoly.model.board;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.data.reader.BoardReader;
import be.ugent.objprog.ugentopoly.data.reader.CardsReader;
import be.ugent.objprog.ugentopoly.data.reader.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tile.Tile;

import java.util.List;

public class Board {

    private List<Area> areas;
    private List<Tile> tiles;

    public void init(GameManager gameManager) {
        BoardReader boardReader = new BoardReader();
        areas = boardReader.readAreas();
        tiles = boardReader.readTiles(areas, gameManager);

        PropertyReader propertyReader = gameManager.getPropertyreader();
        for (Tile tile : tiles) {
            String tileName = propertyReader.getTileName(tile.getId());
            tile.setName(tileName);
        }

        new CardsReader(gameManager).readCards();
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