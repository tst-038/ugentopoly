package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public class StreetTile extends Tile {

    private final int cost;
    private final Area area;
    private final int[] rent;
    private final int housesBuild;
    private final Player owner;

    public StreetTile(String id, int position, int cost, Area area, int[] rent) {
        super(id, position, TileType.STREET);
        this.cost = cost;
        this.area = area;
        this.rent = rent;
        this.housesBuild = 0;
        this.owner = null;
    }

    public int getPrice() {
        return cost;
    }

    public Area getArea() {
        return area;
    }

    public int getRent() {
        return rent[housesBuild];
    }

    public int[] getAllRents() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }
}
