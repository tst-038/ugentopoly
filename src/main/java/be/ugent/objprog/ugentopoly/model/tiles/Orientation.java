package be.ugent.objprog.ugentopoly.model.tiles;


public enum Orientation {
    VERTICAL_TOP("rectangular", 90),
    VERTICAL_BOTTOM("rectangular", 270),
    HORIZONTAL_LEFT("rectangular", 0),
    HORIZONTAL_RIGHT("rectangular", 180),
    CORNER_1("corner", 0),
    CORNER_2("corner", 90),
    CORNER_3("corner", 180),
    CORNER_4("corner", 270);

    private final String directoryName;
    private final int defaultRotation;

    Orientation(String directoryName, int defaultRotation) {
        this.directoryName = directoryName;
        this.defaultRotation = defaultRotation;
    }

    public static Orientation getOrientation(int position) {
        return switch (position) {
            case 0 -> CORNER_1;
            case 10 -> CORNER_2;
            case 20 -> CORNER_3;
            case 30 -> CORNER_4;
            default -> {
                int row = position / 10;
                yield switch (row) {
                    case 0 -> HORIZONTAL_LEFT;
                    case 1 -> VERTICAL_TOP;
                    case 2 -> HORIZONTAL_RIGHT;
                    case 3 -> VERTICAL_BOTTOM;
                    default -> throw new IllegalArgumentException("Invalid position: " + position);
                };
            }
        };
    }

    public int getRotation() {
        return defaultRotation;
    }

    public String getDirectoryName() {
        return directoryName;
    }
}
