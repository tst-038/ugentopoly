package be.ugent.objprog.ugentopoly.model.tiles;

public enum TileType {
    START("start"),
    JAIL("corner"),
    FREE_PARKING("corner"),
    GO_TO_JAIL("corner"),
    CHANCE("image"),
    CHEST("image"),
    TAX("image"),
    UTILITY("utility"),
    RAILWAY("image"),
    STREET("street");

    private final String fxmlFilePrefix;

    TileType(String fileName) {
        this.fxmlFilePrefix = fileName;
    }

    public String getFXMLFileName() {
        return fxmlFilePrefix.toLowerCase() + "-tile.fxml";
    }
}