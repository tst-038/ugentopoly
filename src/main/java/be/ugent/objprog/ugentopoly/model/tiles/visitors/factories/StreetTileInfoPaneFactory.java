package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StreetTileInfoPaneFactory implements TileInfoPaneFactory {



    private AnchorPane createInfoColor(StreetTile tile) {
        AnchorPane infoColor = new AnchorPane();
        infoColor.setStyle("-fx-background-color: " + tile.getArea().getColor() + "; -fx-border-width: 0 0 3 0; -fx-border-color: black;");
        infoColor.setPrefHeight(46.0);
        infoColor.setPrefWidth(146.0);
        infoColor.setLayoutY(0);
        infoColor.setLayoutX(0);
        AnchorPane.setTopAnchor(infoColor, 3.0);
        AnchorPane.setLeftAnchor(infoColor, 3.0);
        AnchorPane.setRightAnchor(infoColor, -3.0);
        return infoColor;
    }

    private Label createInfoTitle(StreetTile tile) {
        Label infoTitle = new Label(tile.getName());
        infoTitle.setAlignment(Pos.CENTER);
        infoTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        infoTitle.setTextFill(getTextColor(tile));
        AnchorPane.setLeftAnchor(infoTitle, 0.0);
        AnchorPane.setRightAnchor(infoTitle, 0.0);
        AnchorPane.setTopAnchor(infoTitle, 5.0);
        AnchorPane.setBottomAnchor(infoTitle, 5.0);
        return infoTitle;
    }

    private Label[] createRentLabels(StreetTile tile) {
        Label[] rents = new Label[5];
        for (int i = 0; i < 5; i++) {
            rents[i] = new Label(Settings.getMoneyUnit() + tile.getAllRents()[i]);
            rents[i].setAlignment(Pos.CENTER_RIGHT);
            rents[i].setFont(Font.font("System", FontWeight.BOLD, 12));
            rents[i].setTextFill(Color.WHITE);
            AnchorPane.setRightAnchor(rents[i], 2.0);
            AnchorPane.setTopAnchor(rents[i], 71.0 + (i * 18.0));
        }
        return rents;
    }

    private Label createPriceLabel(StreetTile tile) {
        Label price = new Label(Settings.getMoneyUnit() + tile.getPrice());
        price.setAlignment(Pos.CENTER_RIGHT);
        price.setFont(Font.font("System", FontWeight.BOLD, 12));
        price.setTextFill(Color.WHITE);
        AnchorPane.setRightAnchor(price, 2.0);
        AnchorPane.setTopAnchor(price, 161.0);
        return price;
    }

    private Label createOwnerLabel() {
        Label ownerLabel = new Label("Current Owner :");
        ownerLabel.setAlignment(Pos.CENTER);
        ownerLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        ownerLabel.setTextFill(Color.WHITE);
        AnchorPane.setBottomAnchor(ownerLabel, 40.0);
        AnchorPane.setLeftAnchor(ownerLabel, 0.0);
        AnchorPane.setRightAnchor(ownerLabel, 0.0);
        return ownerLabel;
    }

    private Label createOwnerLabel(StreetTile tile) {
        Label owner = new Label(tile.getOwner() != null ? tile.getOwner().getName() : "<None>");
        owner.setAlignment(Pos.CENTER);
        owner.setFont(Font.font("System", FontWeight.BOLD, 12));
        owner.setTextFill(Color.WHITE);
        AnchorPane.setBottomAnchor(owner, 25.0);
        AnchorPane.setLeftAnchor(owner, 0.0);
        AnchorPane.setRightAnchor(owner, 0.0);
        return owner;
    }

    private Color getTextColor(StreetTile tile) {
        Color areaColor = Color.web(tile.getArea().getColor());
        double brightness = (areaColor.getRed() * 299 + areaColor.getGreen() * 587 + areaColor.getBlue() * 114) / 1000;
        return brightness > 0.7 ? Color.BLACK : Color.WHITE;
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        if (tile.getType() != TileType.STREET) {
            throw new IllegalArgumentException("Tile must be of type "+TileType.STREET);
        }
        StreetTile streetTile = (StreetTile) tile;
        AnchorPane tileInfoPane = new AnchorPane();
        tileInfoPane.setPrefWidth(150.0);
        tileInfoPane.setPrefHeight(200.0);

        AnchorPane infoColor = createInfoColor(streetTile);
        Label infoTitle = createInfoTitle(streetTile);
        infoColor.getChildren().add(infoTitle);

        Label rentLabel = createLabel("Rent :", 12, Color.WHITE, 5.0, 71.0);
        Label[] rents = createRentLabels(streetTile);
        Label priceLabel = createLabel("Price :", 12, Color.WHITE, 5.0, 161.0);
        Label price = createPriceLabel(streetTile);
        Label ownerLabel = createOwnerLabel();
        Label owner = createOwnerLabel(streetTile);

        tileInfoPane.getChildren().addAll(infoColor, rentLabel, priceLabel, price, ownerLabel, owner);
        tileInfoPane.getChildren().addAll(rents);

        return tileInfoPane;
    }
}