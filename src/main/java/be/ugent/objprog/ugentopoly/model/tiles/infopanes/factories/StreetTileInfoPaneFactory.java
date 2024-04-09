package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class StreetTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        StreetTile streetTile = (StreetTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        AnchorPane infoColor = createInfoColor(streetTile);
        Label infoTitle = createTitleLabel(streetTile);
        infoColor.getChildren().add(infoTitle);

        Label rentLabel = createLabel(PropertyReader.getInstance().get("label.rent") +": ", "street-tile-rent-title", 5.0, null, 80.0, null);
        Label rent = createLabel(Settings.getMoneyUnit() + streetTile.getRent(), "street-tile-rent", null, 5.0, 80.0, null);

        Label priceLabel = createLabel(PropertyReader.getInstance().get("label.price")+": ",  "street-tile-price-title", 5.0, null, 60.0, null);
        Label price = createPriceLabel(streetTile);

        if (streetTile.getOwner() == null) {
            Label forSale = createForSaleLabel();
            tileInfoPane.getChildren().add(forSale);
            Button buyButton = createButton("buy", "street-tile-buy-button", "buy-button", 20., null, 150., null);
            Button cancelButton = createButton("close", "street-tile-close-button", "close-button", null, 20., 150., null);
            tileInfoPane.getChildren().addAll(buyButton, cancelButton);
        }else {
            Label ownerLabel = createOwnerLabel();
            Label owner = createOwnerLabel(streetTile);
            tileInfoPane.getChildren().addAll(ownerLabel, owner);
        }

        tileInfoPane.getChildren().addAll(infoColor, rentLabel, priceLabel, price);
        tileInfoPane.getChildren().add(rent);

        return tileInfoPane;
    }

    private AnchorPane createInfoColor(StreetTile tile) {
        AnchorPane infoColor = new AnchorPane();
        infoColor.getStyleClass().add("street-tile-area");
        infoColor.setStyle("-fx-background-color: " + tile.getArea().color() + "; -fx-border-width: 0 0 3 0; -fx-border-color: black;");
        infoColor.setPrefHeight(50.0);
        infoColor.setPrefWidth(150.0);
        return createAnchorPane(infoColor, 0.0, 0.0, 3.0, 3.0, 3.0, null);
    }

    private Label createTitleLabel(StreetTile tile) {
        Label infoTitle = createLabel(tile.getName(), "street-tile-title", 0.0, 0.0, 5.0, 5.0);
        infoTitle.getStyleClass().add(getTextColorClass(tile));
        return infoTitle;
    }

    private Label createPriceLabel(StreetTile tile) {
        return createLabel(Settings.getMoneyUnit() + tile.getPrice(), "street-tile-price", null, 5.0, 60.0, null);
    }

    private Label createOwnerLabel() {
        return createLabel("Eigenaar :", "street-tile-owner-title", 0.0, 0.0, 125., null);
    }

    private Label createForSaleLabel(){
        return createLabel("Te koop!", "street-tile-owner-title", 0.0, 0.0, 125., null);
    }

    private Label createOwnerLabel(StreetTile tile) {
        return createLabel(tile.getOwner() != null ? tile.getOwner().getName() : "<None>", "street-tile-owner", 0.0, 0.0, 140., null);
    }

    private String getTextColorClass(StreetTile tile) {
        Color areaColor = Color.web(tile.getArea().color());
        double brightness = (areaColor.getRed() * 299 + areaColor.getGreen() * 587 + areaColor.getBlue() * 114) / 1000;
        return brightness > 0.7 ? "street-tile-title-black" : "street-tile-title-white";
    }

    private Button createButton(String text, String styleClass, String id, Double left, Double right, Double top, Double bottom) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        button.setId(id);
        AnchorPane.setTopAnchor(button, top);
        AnchorPane.setLeftAnchor(button, left);
        AnchorPane.setRightAnchor(button, right);
        AnchorPane.setBottomAnchor(button, bottom);
        return button;
    }
}