package be.ugent.objprog.ugentopoly.model;

public class Area {

    private final String id;
    private final String color;
    private final int price;

    public Area(String id, String color, int price) {
        this.id = id;
        this.color = color;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }
}
