package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class StreetTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        StreetTile streetTile = (StreetTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        AnchorPane infoColor = new AnchorPane();
        AnchorPane.setTopAnchor(infoColor, 3.0);
        AnchorPane.setLeftAnchor(infoColor, 3.0);
        AnchorPane.setRightAnchor(infoColor, 3.0);
        infoColor.getStyleClass().add("street-tile-area");
        infoColor.setStyle("-fx-background-color: " + streetTile.getArea().color() + "; -fx-border-width: 0 0 3 0; -fx-border-color: black;");
        infoColor.setPrefHeight(50.0);
        infoColor.setPrefWidth(150.0);

        Label titleLabel = createLabel(streetTile.getName(), "street-tile-title", 0.0, 0.0, 5.0, 5.0);
        titleLabel.getStyleClass().add(getTextColorClass(streetTile));
        infoColor.getChildren().add(titleLabel);

        Label rentTitleLabel = createLabel(PropertyReader.getInstance().get("label.rent") + ": ", "street-tile-rent-title", 5.0, null, 80.0, null);
        Label rentLabel = createLabel(Settings.getMoneyUnit() + streetTile.getRent(), "street-tile-rent", null, 5.0, 80.0, null);

        Label priceTitleLabel = createLabel(PropertyReader.getInstance().get("label.price") + ": ",  "street-tile-price-title", 5.0, null, 60.0, null);
        Label priceLabel = createLabel(Settings.getMoneyUnit() + streetTile.getPrice(), "street-tile-price", null, 5.0, 60.0, null);

        Label ownerLabel;
        if (streetTile.getOwner() == null) {
            Label forSaleLabel = createLabel(PropertyReader.getInstance().get("label.for_sale"), "street-tile-owner-title", 0.0, 0.0, 115., null);
            tileInfoPane.getChildren().add(forSaleLabel);
            if(onVisit) {
                Button buyButton = createButton(PropertyReader.getInstance().get("button.buy"), "street-tile-buy-button", "buy-button", 10., null, 160., null);
                Button cancelButton = createButton(PropertyReader.getInstance().get("button.close"), "street-tile-close-button", "close-button", null, 10., 160., null);
                tileInfoPane.getChildren().addAll(buyButton, cancelButton);
            }
            ownerLabel = createLabel(Settings.getMoneyUnit() + streetTile.getPrice(), "street-tile-owner", 0.0, 0.0, 130., null);
            ownerLabel.setTextFill(Color.WHITE);
        } else {
            Label ownerTitleLabel = createLabel(PropertyReader.getInstance().get("label.owner"), "street-tile-owner-title", 0.0, 0.0, 115., null);
            tileInfoPane.getChildren().add(ownerTitleLabel);
            if(onVisit && streetTile.getOwner() != TurnHandler.getInstance().getCurrentPlayer()) {
                Button payRentButton = createButton(PropertyReader.getInstance().get("button.pay_rent"), "street-pay-rent-button", "pay-rent-button", 20., 20., 160., null);
                tileInfoPane.getChildren().add(payRentButton);
            }
            ownerLabel = createLabel(streetTile.getOwner().getName(), "street-tile-owner", 0.0, 0.0, 130., null);
            ownerLabel.setTextFill(streetTile.getOwner().getColor());
        }

        tileInfoPane.getChildren().addAll(infoColor, rentTitleLabel, priceTitleLabel, priceLabel, ownerLabel, rentLabel);

        return tileInfoPane;
    }

    private String getTextColorClass(StreetTile tile) {
        Color areaColor = Color.web(tile.getArea().color());
        return areaColor.getBrightness() > 0.7 ? "street-tile-title-black" : "street-tile-title-white";
    }
}