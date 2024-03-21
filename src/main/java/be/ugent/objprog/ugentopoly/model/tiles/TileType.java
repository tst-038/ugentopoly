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
        try {
            TileType.valueOf(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
