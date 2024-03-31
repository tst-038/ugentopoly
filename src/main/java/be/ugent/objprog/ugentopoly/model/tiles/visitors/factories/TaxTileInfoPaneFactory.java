package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.TaxTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TaxTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView taxImage = createTileImage();
        Label titleLabel = createTitleLabel(tile);
        Label infoLabel = createInfoLabel(tile);

        tileInfoPane.getChildren().addAll(taxImage, titleLabel, infoLabel);

        return tileInfoPane;
    }

    private ImageView createTileImage() {
        ImageView taxImage = new ImageView(ResourceLoader.loadImage("assets/tax.png"));
        taxImage.setFitHeight(75.0);
        taxImage.setFitWidth(75.0);
        return createImageView(taxImage, 10.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(Tile tile) {
        TaxTile taxTile = (TaxTile) tile;
        return createLabel(taxTile.getName(), "tax-title", 5.0, 5.0, 50.0, 35.0);
    }

    private Label createInfoLabel(Tile tile) {
        TaxTile taxTile = (TaxTile) tile;
        String infoText = "Pay: "+Settings.getMoneyUnit() + taxTile.getAmount();
        return createLabel(infoText, "tax-info", 5.0, 5.0,110., 5.);
    }
}