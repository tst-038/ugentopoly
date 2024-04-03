package be.ugent.objprog.ugentopoly.model.tiles;

public enum Orientation {
    CORNER_1(0),
    CORNER_2(90),
    CORNER_3(180),
    CORNER_4(270),
    HORIZONTAL_LEFT(0),
    HORIZONTAL_RIGHT(180),
    VERTICAL_TOP(0),
    VERTICAL_BOTTOM(180);

    private final int defaultRotation;

    Orientation(int defaultRotation) {
        this.defaultRotation = defaultRotation;
    }
    public int getRotation(TileType tileType) {

        return defaultRotation;
    }

    public static Orientation getOrientation(int position) {
        switch (position) {
            case 0:
                return CORNER_1;
            case 10:
                return CORNER_2;
            case 20:
                return CORNER_3;
            case 30:
                return CORNER_4;
            default:
                int row = position / 10;
                switch (row) {
                    case 0:
                        return HORIZONTAL_LEFT;
                    case 1:
                        return VERTICAL_TOP;
                    case 2:
                        return HORIZONTAL_RIGHT;
                    case 3:
                        return VERTICAL_BOTTOM;
                    default:
                        throw new IllegalArgumentException("Invalid position: " + position);
                }
        }
    }
}
