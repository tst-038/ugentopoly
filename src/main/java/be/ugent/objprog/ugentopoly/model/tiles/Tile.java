package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;

public abstract class Tile {

    private final String id;
    private final int position;
    private final TileType type;
    private String name;

    protected Tile(String id, int position, TileType type) {
        this.id = id.replace("tile.", "");
        this.position = position;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public TileType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void accept(TileVisitor visitor);
}
