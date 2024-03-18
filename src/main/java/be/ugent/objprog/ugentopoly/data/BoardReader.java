package be.ugent.objprog.ugentopoly.data;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.exceptions.AreaReadException;
import be.ugent.objprog.ugentopoly.exceptions.SettingReadException;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoardReader {

    public Settings readSettings() {
        try (InputStream xmlInputStream = Ugentopoly.class.getResourceAsStream("ugentopoly.deel1.xml")) {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(xmlInputStream);
            Element root = document.getRootElement();
            Element settingsElement = root.getChild("settings");

            int balance = Integer.parseInt(settingsElement.getAttributeValue("balance"));
            int start = Integer.parseInt(settingsElement.getAttributeValue("start"));
            int size = Integer.parseInt(settingsElement.getAttributeValue("size"));

            return new Settings(balance, start, size);
        }
        catch (IOException | JDOMException e) {
            throw new SettingReadException("Failed to read settings information from XML file", e);
        }
    }

    public List<Area> readAreas() {
        try (InputStream xmlInputStream = Ugentopoly.class.getResourceAsStream("ugentopoly.deel1.xml")) {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(xmlInputStream);
            Element root = document.getRootElement();
            Element areasElement = root.getChild("areas");

            List<Area> areas = new ArrayList<>();
            for (Element areaElement : areasElement.getChildren("area")) {
                String id = areaElement.getAttributeValue("id");
                String color = areaElement.getAttributeValue("color");
                int house = Integer.parseInt(areaElement.getAttributeValue("house"));

                areas.add(new Area(id, color, house));
            }

            return areas;
        } catch (IOException | JDOMException e) {
            throw new AreaReadException("Failed to read areas information from XML file", e);
        }
    }


    public static List<Tile> readTiles(List<Area> areas) {
        try (InputStream xmlInputStream = Ugentopoly.class.getResourceAsStream("ugentopoly.deel1.xml")) {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(xmlInputStream);
            Element root = document.getRootElement();
            Element tilesElement = root.getChild("tiles");

            List<Tile> tiles = new ArrayList<>();
            for (Element tileElement : tilesElement.getChildren("tile")) {
                String id = tileElement.getAttributeValue("id");
                int position = Integer.parseInt(tileElement.getAttributeValue("position"));
                TileType type = TileType.valueOf(tileElement.getAttributeValue("type"));

                Tile tile;
                switch (type) {
                    case START:
                        tile = new StreetTile("tile", 0, TileType.STREET, 0, null, new int[5] );
                        break;
                    case JAIL:
                        tile = new StreetTile("tile", 0, TileType.STREET, 0, null, new int[5]);
                        break;
                    case FREE_PARKING:
                        tile = new StreetTile("tile", 0, TileType.STREET, 0, null, new int[5]);
                        break;
                    case GO_TO_JAIL:
                        tile = new StreetTile("tile", 0, TileType.STREET, 0, null, new int[5]);
                        break;
                    case CHANCE:
                        tile = new StreetTile("tile", 0, TileType.STREET, 0, null, new int[5]);
                        break;
                    case CHEST:
                        tile = new StreetTile("tile", 0, TileType.STREET, 0, null, new int[5]);
                        break;
                    case TAX:
                        int amount = Integer.parseInt(tileElement.getAttributeValue("amount"));
                        tile = new TaxTile(id, position, type, amount);
                        break;
                    case UTILITY:
                        int utilityCost = Integer.parseInt(tileElement.getAttributeValue("cost"));
                        tile = new UtilityTile(id, position, type, utilityCost);
                        break;
                    case RAILWAY:
                        int railwayCost = Integer.parseInt(tileElement.getAttributeValue("cost"));
                        tile = new RailwayTile(id, position, type, railwayCost);
                        break;
                    case STREET:
                        int streetCost = Integer.parseInt(tileElement.getAttributeValue("cost"));
                        String area = tileElement.getAttributeValue("area");
                        int[] rents = IntStream.range(0, 6)
                                .map(i -> Integer.parseInt(tileElement.getAttributeValue("rent" + i))).toArray();
                        tile = new StreetTile(id, position, type, streetCost, areas.stream().filter(ar -> ar.getId().equals(area)).findFirst().orElse(null), rents);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown tile type: " + type);
                }
                tiles.add(tile);
            }
            return tiles;
        } catch (IOException | JDOMException e) {
            throw new AreaReadException("Failed to read areas information from XML file", e);
        }
    }
}
