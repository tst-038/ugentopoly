package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class StreetTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        StreetTile streetTile = (StreetTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        AnchorPane infoColor = createInfoColor(streetTile);
        Label infoTitle = createTitleLabel(streetTile);
        infoColor.getChildren().add(infoTitle);

        Label rentLabel = createLabel("Rent :", FontWeight.BOLD, 12, Color.WHITE, 5.0, null, 80.0, null);
        rentLabel.setAlignment(Pos.CENTER_LEFT);
        GridPane rents = createRentGrid(streetTile);
        AnchorPane.setLeftAnchor(rents, 50.0);
        AnchorPane.setTopAnchor(rents, 80.0);
        AnchorPane.setRightAnchor(rents, 2.0);

        Label priceLabel = createLabel("Price :", FontWeight.BOLD, 12, Color.WHITE, 5.0, null, 60., null);
        priceLabel.setAlignment(Pos.CENTER_LEFT);
        Label price = createPriceLabel(streetTile);
        Label ownerLabel = createOwnerLabel();
        Label owner = createOwnerLabel(streetTile);

        tileInfoPane.getChildren().addAll(infoColor, rentLabel, priceLabel, price, ownerLabel, owner);
        tileInfoPane.getChildren().addAll(rents);

        return tileInfoPane;
    }

    private AnchorPane createInfoColor(StreetTile tile) {
        AnchorPane infoColor = new AnchorPane();
        infoColor.setStyle("-fx-background-color: " + tile.getArea().getColor() + "; -fx-border-width: 0 0 3 0; -fx-border-color: black;");
        infoColor.setPrefHeight(46.0);
        infoColor.setPrefWidth(146.0);
        return createAnchorPane(infoColor, 0.0, 0.0, 3.0, 3.0, -3.0, null);
    }

    private Label createTitleLabel(StreetTile tile) {
        Label infoTitle = createLabel(tile.getName(), FontWeight.BOLD, 20, getTextColor(tile), 0.0, 0.0, 5.0, 5.0);
        infoTitle.setAlignment(Pos.CENTER);
        return infoTitle;
    }

    private GridPane createRentGrid(StreetTile tile) {
        GridPane rentGrid = new GridPane();
        rentGrid.setHgap(5);
        rentGrid.setVgap(5);
        rentGrid.setPrefSize(100,100);
        rentGrid.setAlignment(Pos.TOP_RIGHT);

        // Assuming getAllRents returns an array of your rent prices
        int[] rentPrices = tile.getAllRents();

        for (int i = 0; i < rentPrices.length; i++) {
            Label rentLabel = new Label(Settings.getMoneyUnit()+rentPrices[i]);
            rentLabel.setFont(Font.font("System", FontWeight.NORMAL, 12));
            rentLabel.setTextFill(Color.WHITE);
            rentLabel.setTextAlignment(TextAlignment.RIGHT);
            rentLabel.setMaxWidth(Double.MAX_VALUE);
            rentLabel.setAlignment(Pos.CENTER_RIGHT);

            rentGrid.add(rentLabel, i % 2, i / 2);
        }

        return rentGrid;
    }

    private Label createPriceLabel(StreetTile tile) {
        Label price = createLabel(Settings.getMoneyUnit() + tile.getPrice(), FontWeight.NORMAL, 12, Color.WHITE, null, 2.0, 60.0, null);
        price.setAlignment(Pos.CENTER_RIGHT);
        return price;
    }

    private Label createOwnerLabel() {
        return createLabel("Current Owner :", FontWeight.BOLD, 12, Color.WHITE, 0.0, 0.0, null, 40.0);
    }

    private Label createOwnerLabel(StreetTile tile) {
        Label owner = createLabel(tile.getOwner() != null ? tile.getOwner().getName() : "<None>", FontWeight.NORMAL, 12, Color.WHITE, 0.0, 0.0, null, 25.0);
        owner.setAlignment(Pos.CENTER);
        return owner;
    }

    private Color getTextColor(StreetTile tile) {
        Color areaColor = Color.web(tile.getArea().getColor());
        double brightness = (areaColor.getRed() * 299 + areaColor.getGreen() * 587 + areaColor.getBlue() * 114) / 1000;
        return brightness > 0.7 ? Color.BLACK : Color.WHITE;
    }
}