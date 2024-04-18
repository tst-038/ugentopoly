package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import be.ugent.objprog.ugentopoly.model.interfaces.Ownable;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
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

        ImageView utilityTileImage  = createUtilityImage(tile);
        Label title = createTitleLabel((UtilityTile) tile);

        tileInfoPane.getChildren().addAll(utilityTileImage, title);
        if (utilityTile.getOwner() == null) {
            Label forSale = createForSaleLabel();
            tileInfoPane.getChildren().add(forSale);
            if(onVisit) {
                Button buyButton = createButton(PropertyReader.getInstance().get("button.buy"), "utility-tile-buy-button", "buy-button", 10., null, 160., null);
                Button cancelButton = createButton(PropertyReader.getInstance().get("button.cancel"), "utility-tile-close-button", "close-button", null, 10., 160., null);
                tileInfoPane.getChildren().addAll(buyButton, cancelButton);
            }
        }else {
            Label ownerLabel = createOwnerLabel();
            tileInfoPane.getChildren().add(ownerLabel);
            // If the player is not the owner of the street, show the pay rent button
            if(onVisit && utilityTile.getOwner() != TurnHandler.getInstance().getCurrentPlayer()){
                Button payrentButton = createButton(PropertyReader.getInstance().get("button.pay_rent"), "utility-pay-rent-button", "pay-rent-button", 20., 20., 160., null);
                tileInfoPane.getChildren().add(payrentButton);
            }
        }
        Label owner = createOwnerLabel(utilityTile);

        tileInfoPane.getChildren().add(owner);
        return tileInfoPane;
    }

    private Label createOwnerLabel() {
        return createLabel(PropertyReader.getInstance().get("label.owner"), "utility-tile-owner-title", 0.0, 0.0, 115., null);
    }

    private Label createForSaleLabel(){
        return createLabel(PropertyReader.getInstance().get("label.for_sale"), "utility-tile-owner-title", 0.0, 0.0, 115., null);
    }

    private Label createOwnerLabel(Buyable tile) {
        Label ownerLabel = createLabel(tile.getOwner() != null ? tile.getOwner().getName() : Settings.getMoneyUnit()+tile.getPrice(), "utility-tile-owner", 0.0, 0.0, 130., null);
        ownerLabel.setTextFill(tile.getOwner() != null ? tile.getOwner().getColor() : Color.WHITE);
        return ownerLabel;
    }

    private ImageView createUtilityImage(Tile tile) {
        ImageView image = new ImageView(ResourceLoader.loadImage("assets/"+tile.getId()+".png"));
        image.setPreserveRatio(true);
        image.setScaleX(1.5);
        image.setScaleY(1.5);
        image.setFitHeight(75.0);
        image.setFitWidth(75.0);
        return createImageView(image, 20.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(UtilityTile tile) {
        return createLabel(tile.getName(), "utility-title", 5.0, 5.0, 0.0, 35.0);
    }
}