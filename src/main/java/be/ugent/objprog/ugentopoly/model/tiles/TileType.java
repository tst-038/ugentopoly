package be.ugent.objprog.ugentopoly.model.tiles;

public enum TileType {
    START,
    JAIL,
    FREE_PARKING,
    GO_TO_JAIL,
    CHANCE,
    CHEST,
    TAX,
    UTILITY,
    RAILWAY,
    STREET;

    public static boolean isValidTileType(String id) {
        for (TileType tileType : TileType.values()) {
            if (tileType.name().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
