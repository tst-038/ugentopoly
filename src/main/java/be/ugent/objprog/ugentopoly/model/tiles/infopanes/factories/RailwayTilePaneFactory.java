package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.RailwayTile;
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

        ImageView railwayImage = createImageView(new ImageView(ResourceLoader.loadImage("assets/railway.png")), 10.0, 37.5, 37.5, null);
        railwayImage.setFitHeight(75.0);
        railwayImage.setFitWidth(75.0);

        Label titleLabel = createLabel(railwayTile.getName(), "railway-title", 5.0, 5.0, 45.0, 35.0);
        Label ownerLabel = createLabel(railwayTile.getOwner() != null ? railwayTile.getOwner().getName() : Settings.getMoneyUnit()+railwayTile.getPrice(), "tax-tile-owner", 0.0, 0.0, 130., null);
        ownerLabel.setTextFill(railwayTile.getOwner() != null ? railwayTile.getOwner().getColor() : Color.WHITE);

        tileInfoPane.getChildren().addAll(railwayImage, titleLabel, ownerLabel);

        if (railwayTile.getOwner() == null) {
            Label forSaleLabel = createLabel(PropertyReader.getInstance().get("label.for_sale"), "tax-tile-owner-title", 0.0, 0.0, 115., null);
            tileInfoPane.getChildren().add(forSaleLabel);
            if(onVisit) {
                Button buyButton = createButton(PropertyReader.getInstance().get("button.buy"), "buy-button", "buy-button", 10., null, 160., null);
                Button cancelButton = createButton(PropertyReader.getInstance().get("button.close"), "close-button", "close-button", null, 10., 160., null);
                tileInfoPane.getChildren().addAll(buyButton, cancelButton);
            }
        } else {
            Label ownerTitleLabel = createLabel(PropertyReader.getInstance().get("label.owner"), "tax-tile-owner-title", 0.0, 0.0, 115., null);
            tileInfoPane.getChildren().add(ownerTitleLabel);
            if(onVisit && railwayTile.getOwner() != TurnHandler.getInstance().getCurrentPlayer()){
                Button payrentButton = createButton(PropertyReader.getInstance().get("button.pay_rent"), "pay-rent-button", "pay-rent-button", 20., 20., 160., null);
                tileInfoPane.getChildren().add(payrentButton);
            }
        }

        return tileInfoPane;
    }
}