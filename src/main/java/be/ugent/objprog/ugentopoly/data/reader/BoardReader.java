package be.ugent.objprog.ugentopoly.data.reader;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.exception.data.AreaReadException;
import be.ugent.objprog.ugentopoly.model.board.Area;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import be.ugent.objprog.ugentopoly.model.tile.TileType;
import be.ugent.objprog.ugentopoly.model.tile.factory.*;
import javafx.scene.paint.Color;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BoardReader implements XmlReader {

    public List<Tile> readTiles(List<Area> areas, GameManager gameManager) {
        try (InputStream xmlInputStream = ResourceLoader.loadResource("ugentopoly.xml")) {
            Document document = parseXml(xmlInputStream);
            Element root = getRootElement(document);
            Element tilesElement = getChildElement(root, "tiles");
            Map<TileType, TileFactory> tileFactories = createTileFactories(areas);

            return parseTiles(tilesElement, tileFactories, gameManager);
        } catch (IOException | JDOMException e) {
            throw new AreaReadException("Failed to read areas information from XML file", e);
        }
    }

    private List<Tile> parseTiles(Element tilesElement, Map<TileType, TileFactory> tileFactories, GameManager gameManager) {
        List<Tile> tiles = new ArrayList<>();

        for (Element tileElement : tilesElement.getChildren("tile")) {
            TileType type = TileType.valueOf(tileElement.getAttributeValue("type"));
            TileFactory tileFactory = tileFactories.get(type);

            if (tileFactory == null) {
                throw new IllegalArgumentException("Unknown tile type: " + type);
            }

            Tile tile = tileFactory.createTile(tileElement, gameManager);
            tiles.add(tile);
        }

        return tiles;
    }

    public List<Area> readAreas() {
        try (InputStream xmlInputStream = ResourceLoader.loadResource("ugentopoly.xml")) {
            Document document = parseXml(xmlInputStream);
            Element root = getRootElement(document);
            Element areasElement = getChildElement(root, "areas");

            return parseAreas(areasElement);
        } catch (IOException | JDOMException e) {
            throw new AreaReadException("Failed to read areas information from XML file", e);
        }
    }

    private List<Area> parseAreas(Element areasElement) {
        List<Area> areas = new ArrayList<>();

        for (Element areaElement : areasElement.getChildren("area")) {
            String id = areaElement.getAttributeValue("id");
            Color color = Color.web(areaElement.getAttributeValue("color"));
            areas.add(new Area(id, color));
        }

        return areas;
    }

    private Map<TileType, TileFactory> createTileFactories(List<Area> areas) {
        Map<TileType, TileFactory> tileFactories = new EnumMap<>(TileType.class);
        tileFactories.put(TileType.START, new StartTileFactory());
        tileFactories.put(TileType.JAIL, new JailTileFactory());
        tileFactories.put(TileType.FREE_PARKING, new FreeParkingTileFactory());
        tileFactories.put(TileType.GO_TO_JAIL, new GoToJailTileFactory());
        tileFactories.put(TileType.CHANCE, new ChanceTileFactory());
        tileFactories.put(TileType.CHEST, new ChestTileFactory());
        tileFactories.put(TileType.TAX, new TaxTileFactory());
        tileFactories.put(TileType.UTILITY, new UtilityTileFactory());
        tileFactories.put(TileType.RAILWAY, new RailwayTileFactory());
        tileFactories.put(TileType.STREET, new StreetTileFactory(areas));
        return tileFactories;
    }
}