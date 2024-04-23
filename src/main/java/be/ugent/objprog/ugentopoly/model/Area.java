package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Area {
    private final String id;
    private final String color;
    private final List<StreetTile> tiles;

    public Area(String id, String color) {
        this.id = id;
        this.color = color;
        this.tiles = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public List<StreetTile> getTiles() {
        return tiles;
    }

    public void addTile(StreetTile tile) {
        this.tiles.add(tile);
    }

    public boolean allTilesOwnedBySamePlayer() {
        if (tiles.isEmpty()) {
            return false;
        }
        Player player = tiles.getFirst().getOwner();
        ListIterator<StreetTile> iterator = tiles.listIterator();
        boolean allOwnedBySamePlayer = true;
        while (iterator.hasNext() && allOwnedBySamePlayer) {
            Player nextPlayer = iterator.next().getOwner();
            if (nextPlayer == null || !nextPlayer.equals(player)) {
                allOwnedBySamePlayer = false;
            }
        }
        return allOwnedBySamePlayer;
    }
}