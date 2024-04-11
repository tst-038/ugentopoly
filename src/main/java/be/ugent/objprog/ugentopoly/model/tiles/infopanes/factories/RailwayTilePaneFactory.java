package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import be.ugent.objprog.ugentopoly.model.tiles.RailwayTile;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class RailwayTilePaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        RailwayTile railwayTile = (RailwayTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        ImageView railwayImage = createTileImage();
        Label titleLabel = createTitleLabel(tile);

        if (railwayTile.getOwner() == null) {
            Label forSale = createForSaleLabel();
            tileInfoPane.getChildren().add(forSale);
            if(onVisit) {
                Button buyButton = createButton("Buy", "buy-button", "buy-button", 10., null, 150., null);
                Button cancelButton = createButton("Close", "close-button", "close-button", null, 10., 150., null);
                tileInfoPane.getChildren().addAll(buyButton, cancelButton);
            }
        }else {
            Label ownerLabel = createOwnerLabel();
            tileInfoPane.getChildren().add(ownerLabel);
            // If the player is not the owner of the street, show the pay rent button
            if(onVisit && railwayTile.getOwner() != TurnHandler.getInstance().getCurrentPlayer()){
                Button payrentButton = createButton("Pay rent", "pay-rent-button", "pay-rent-button", 20., 20., 150., null);
                tileInfoPane.getChildren().add(payrentButton);
            }
        }
        Label owner = createOwnerLabel(railwayTile);

        tileInfoPane.getChildren().addAll(railwayImage, titleLabel, owner);

        return tileInfoPane;
    }

    private Label createOwnerLabel() {
        return createLabel("Eigenaar :", "tax-tile-owner-title", 0.0, 0.0, 115., null);
    }

    private Label createForSaleLabel(){
        return createLabel("Te koop!", "tax-tile-owner-title", 0.0, 0.0, 115., null);
    }

    private Label createOwnerLabel(RailwayTile tile) {
        Label ownerLabel = createLabel(tile.getOwner() != null ? tile.getOwner().getName() : Settings.getMoneyUnit()+tile.getPrice(), "tax-tile-owner", 0.0, 0.0, 130., null);
        ownerLabel.setTextFill(tile.getOwner() != null ? tile.getOwner().getColor() : Color.WHITE);
        return ownerLabel;
    }

    private ImageView createTileImage() {
        ImageView railwayImage = new ImageView(ResourceLoader.loadImage("assets/railway.png"));
        railwayImage.setFitHeight(75.0);
        railwayImage.setFitWidth(75.0);
        return createImageView(railwayImage, 10.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(Tile tile) {
        return createLabel(tile.getName(), "railway-title", 5.0, 5.0, 45.0, 35.0);
    }
}