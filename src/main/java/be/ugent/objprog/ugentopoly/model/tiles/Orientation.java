package be.ugent.objprog.ugentopoly.model.tiles;


public enum Orientation {
    VERTICAL_TOP("vertical", 0),
    VERTICAL_BOTTOM("vertical", 180),
    HORIZONTAL_LEFT("horizontal", 0),
    HORIZONTAL_RIGHT("horizontal", 180),
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

    public int getRotation() {
        return defaultRotation;
    }

    public String getDirectoryName() {
        return directoryName;
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
