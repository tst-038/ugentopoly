package be.ugent.objprog.ugentopoly.model.tiles;

import java.util.EnumMap;
import java.util.Map;

public class TileConfig {
    private static final Map<TileType, Map<Orientation, String>> TILE_FXML_MAP = new EnumMap<>(TileType.class);

    static {
        Map<Orientation, String> streetTileFXML = new EnumMap<>(Orientation.class);
        streetTileFXML.put(Orientation.VERTICAL_TOP, "vertical/street-tile.fxml");
        streetTileFXML.put(Orientation.VERTICAL_BOTTOM, "vertical/street-tile.fxml");
        streetTileFXML.put(Orientation.HORIZONTAL_LEFT, "horizontal/street-tile.fxml");
        streetTileFXML.put(Orientation.HORIZONTAL_RIGHT, "horizontal/street-tile.fxml");
        TILE_FXML_MAP.put(TileType.STREET, streetTileFXML);

        Map<Orientation, String> imageTileFXML = new EnumMap<>(Orientation.class);
        imageTileFXML.put(Orientation.VERTICAL_TOP, "vertical/image-tile.fxml");
        imageTileFXML.put(Orientation.VERTICAL_BOTTOM, "vertical/image-tile.fxml");
        imageTileFXML.put(Orientation.HORIZONTAL_LEFT, "horizontal/image-tile.fxml");
        imageTileFXML.put(Orientation.HORIZONTAL_RIGHT, "horizontal/image-tile.fxml");
        TILE_FXML_MAP.put(TileType.CHANCE, imageTileFXML);
        TILE_FXML_MAP.put(TileType.RAILWAY, imageTileFXML);
        TILE_FXML_MAP.put(TileType.CHEST, imageTileFXML);
        TILE_FXML_MAP.put(TileType.TAX, imageTileFXML);

        Map<Orientation, String> utilityTileFXML = new EnumMap<>(Orientation.class);
        utilityTileFXML.put(Orientation.VERTICAL_TOP, "vertical/utility-tile.fxml");
        utilityTileFXML.put(Orientation.VERTICAL_BOTTOM, "vertical/utility-tile.fxml");
        utilityTileFXML.put(Orientation.HORIZONTAL_LEFT, "horizontal/utility-tile.fxml");
        utilityTileFXML.put(Orientation.HORIZONTAL_RIGHT, "horizontal/utility-tile.fxml");
        TILE_FXML_MAP.put(TileType.UTILITY, utilityTileFXML);

        Map<Orientation, String> startTileFXML = new EnumMap<>(Orientation.class);
        startTileFXML.put(Orientation.CORNER_1, "corner/start-tile.fxml");
        startTileFXML.put(Orientation.CORNER_2, "corner/start-tile.fxml");
        startTileFXML.put(Orientation.CORNER_3, "corner/start-tile.fxml");
        startTileFXML.put(Orientation.CORNER_4, "corner/start-tile.fxml");
        TILE_FXML_MAP.put(TileType.START, startTileFXML);

        Map<Orientation, String> defaultCornerTileFXML = new EnumMap<>(Orientation.class);
        defaultCornerTileFXML.put(Orientation.CORNER_1, "corner/corner-tile.fxml");
        defaultCornerTileFXML.put(Orientation.CORNER_2, "corner/corner-tile.fxml");
        defaultCornerTileFXML.put(Orientation.CORNER_3, "corner/corner-tile.fxml");
        defaultCornerTileFXML.put(Orientation.CORNER_4, "corner/corner-tile.fxml");
        TILE_FXML_MAP.put(TileType.JAIL, defaultCornerTileFXML);
        TILE_FXML_MAP.put(TileType.GO_TO_JAIL, defaultCornerTileFXML);
        TILE_FXML_MAP.put(TileType.FREE_PARKING, defaultCornerTileFXML);

        // TODO add mappings for other tiles

    }

    public static String getFXMLFileName(TileType tileType, Orientation orientation) {
        Map<Orientation, String> orientationMap = TILE_FXML_MAP.get(tileType);
        if (orientationMap != null) {
            return orientationMap.getOrDefault(orientation, "street-tile.fxml");
        }
        return null;
    }
}
