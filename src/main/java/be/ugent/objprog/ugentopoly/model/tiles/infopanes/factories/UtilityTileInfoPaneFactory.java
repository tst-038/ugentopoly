package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.UtilityTile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class UtilityTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        UtilityTile utilityTile = (UtilityTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        ImageView utilityTileImage = createImageView(new ImageView(ResourceLoader.loadImage("assets/" + utilityTile.getId() + ".png")), 20.0, 37.5, 37.5, null);
        utilityTileImage.setPreserveRatio(true);
        utilityTileImage.setScaleX(1.5);
        utilityTileImage.setScaleY(1.5);
        utilityTileImage.setFitHeight(75.0);
        utilityTileImage.setFitWidth(75.0);

        Label titleLabel = createLabel(utilityTile.getName(), "utility-title", 5.0, 5.0, 0.0, 35.0);

        tileInfoPane.getChildren().addAll(utilityTileImage, titleLabel);

        Label ownerLabel;
        if (utilityTile.getOwner() == null) {
            Label forSaleLabel = createLabel(PropertyReader.getInstance().get("label.for_sale"), "utility-tile-owner-title", 0.0, 0.0, 115., null);
            tileInfoPane.getChildren().add(forSaleLabel);
            if (onVisit) {
                Button buyButton = createButton(PropertyReader.getInstance().get("button.buy"), "utility-tile-buy-button", "buy-button", 10., null, 160., null);
                Button cancelButton = createButton(PropertyReader.getInstance().get("button.close"), "utility-tile-close-button", "close-button", null, 10., 160., null);
                tileInfoPane.getChildren().addAll(buyButton, cancelButton);
            }
            ownerLabel = createLabel(Settings.getMoneyUnit() + utilityTile.getPrice(), "utility-tile-owner", 0.0, 0.0, 130., null);
            ownerLabel.setTextFill(Color.WHITE);
        } else {
            Label ownerTitleLabel = createLabel(PropertyReader.getInstance().get("label.owner"), "utility-tile-owner-title", 0.0, 0.0, 115., null);
            tileInfoPane.getChildren().add(ownerTitleLabel);
            if (onVisit && utilityTile.getOwner() != TurnHandler.getInstance().getCurrentPlayer()) {
                Button payRentButton = createButton(PropertyReader.getInstance().get("button.pay_rent"), "utility-pay-rent-button", "pay-rent-button", 20., 20., 160., null);
                tileInfoPane.getChildren().add(payRentButton);
            }
            ownerLabel = createLabel(utilityTile.getOwner().getName(), "utility-tile-owner", 0.0, 0.0, 130., null);
            ownerLabel.setTextFill(utilityTile.getOwner().getColor());
        }

        tileInfoPane.getChildren().add(ownerLabel);

        return tileInfoPane;
    }
}