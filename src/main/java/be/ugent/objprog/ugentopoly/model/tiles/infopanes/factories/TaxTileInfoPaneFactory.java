package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.TaxTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TaxTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        TaxTile taxTile = (TaxTile) tile;

        ImageView taxImage = createImageView(new ImageView(ResourceLoader.loadImage("assets/tax.png")), 10.0, 37.5, 37.5, null);
        taxImage.setFitHeight(75.0);
        taxImage.setFitWidth(75.0);

        Label titleLabel = createLabel(taxTile.getName(), "tax-title", 5.0, 5.0, 50.0, 35.0);
        String desc = PropertyReader.getInstance().getTileDescription(taxTile.getId().replaceAll("\\d","")).formatted(Settings.getMoneyUnit() + taxTile.getAmount());
        Label infoLabel = createLabel(desc, "tax-info", 5.0, 5.0,130., null);

        tileInfoPane.getChildren().addAll(taxImage, titleLabel, infoLabel);

        if(onVisit){
            Button payrentButton = createButton(PropertyReader.getInstance().get("button.pay_tax"), "tax-pay-button", "tax-pay-button", 20., 20., 160., null);
            tileInfoPane.getChildren().add(payrentButton);
        }

        return tileInfoPane;
    }
}