package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class StreetTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        StreetTile streetTile = (StreetTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        AnchorPane infoColor = createInfoColor(streetTile);
        Label infoTitle = createTitleLabel(streetTile);
        infoColor.getChildren().add(infoTitle);

        Label rentLabel = createLabel("Rent :", "street-tile-rent-title", 5.0, null, 80.0, null);
        GridPane rents = createRentGrid(streetTile);
        AnchorPane.setLeftAnchor(rents, 50.0);
        AnchorPane.setTopAnchor(rents, 80.0);
        AnchorPane.setRightAnchor(rents, 5.0);

        Label priceLabel = createLabel("Price :", "street-tile-price-title", 5.0, null, 60.0, null);
        Label price = createPriceLabel(streetTile);
        Label ownerLabel = createOwnerLabel();
        Label owner = createOwnerLabel(streetTile);

        tileInfoPane.getChildren().addAll(infoColor, rentLabel, priceLabel, price, ownerLabel, owner);
        tileInfoPane.getChildren().add(rents);

        return tileInfoPane;
    }

    private AnchorPane createInfoColor(StreetTile tile) {
        AnchorPane infoColor = new AnchorPane();
        infoColor.setStyle("-fx-background-color: " + tile.getArea().getColor() + "; -fx-border-width: 0 0 3 0; -fx-border-color: black;");
        infoColor.setPrefHeight(50.0);
        infoColor.setPrefWidth(150.0);
        return createAnchorPane(infoColor, 0.0, 0.0, 3.0, 3.0, 3.0, null);
    }

    private Label createTitleLabel(StreetTile tile) {
        Label infoTitle = createLabel(tile.getName(), "street-tile-title", 0.0, 0.0, 5.0, 5.0);
        infoTitle.getStyleClass().add(getTextColorClass(tile));
        return infoTitle;
    }

    private GridPane createRentGrid(StreetTile tile) {
        GridPane rentGrid = new GridPane();
        rentGrid.getStyleClass().add("street-tile-rent-grid");

        int[] rentPrices = tile.getAllRents();

        for (int i = 0; i < rentPrices.length; i++) {
            Label rentLabel = createLabel(Settings.getMoneyUnit() + rentPrices[i], "street-tile-rent", null, null, null, null);
            rentGrid.add(rentLabel, i % 2, i / 2);
        }

        return rentGrid;
    }

    private Label createPriceLabel(StreetTile tile) {
        return createLabel(Settings.getMoneyUnit() + tile.getPrice(), "street-tile-price", null, 5.0, 60.0, null);
    }

    private Label createOwnerLabel() {
        return createLabel("Current Owner :", "street-tile-owner-title", 0.0, 0.0, null, 30.0);
    }

    private Label createOwnerLabel(StreetTile tile) {
        return createLabel(tile.getOwner() != null ? tile.getOwner().getName() : "<None>", "street-tile-owner", 0.0, 0.0, null, 15.0);
    }

    private String getTextColorClass(StreetTile tile) {
        Color areaColor = Color.web(tile.getArea().getColor());
        double brightness = (areaColor.getRed() * 299 + areaColor.getGreen() * 587 + areaColor.getBlue() * 114) / 1000;
        return brightness > 0.7 ? "street-tile-title-black" : "street-tile-title-white";
    }
}