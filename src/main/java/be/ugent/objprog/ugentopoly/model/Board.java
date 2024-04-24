package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.data.readers.BoardReader;
import be.ugent.objprog.ugentopoly.data.readers.CardsReader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;

import java.util.List;

public class Board {

    private List<Area> areas;
    private List<Tile> tiles;

    public void init(Game game) {
        BoardReader boardReader = new BoardReader();
        areas = boardReader.readAreas();
        tiles = boardReader.readTiles(areas, game);

        PropertyReader propertyReader = game.getPropertyreader();
        for (Tile tile : tiles) {
            String tileName = propertyReader.getTileName(tile.getId());
            tile.setName(tileName);
        }

        new CardsReader(game).readCards();
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