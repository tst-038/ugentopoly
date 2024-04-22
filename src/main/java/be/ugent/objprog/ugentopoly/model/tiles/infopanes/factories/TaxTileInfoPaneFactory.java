package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.TaxTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class TaxTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        TaxTile taxTile = (TaxTile) tile;

        addTitleLabelWithImage(tileInfoPane, taxTile.getName(), "tax-title", 10.0, getTileImageView("assets/tax.png"));
        addDescriptionLabel(tileInfoPane, getTaxDescription(taxTile), "tax-info", 70.0);
        addButton(tileInfoPane, PropertyReader.getInstance().get("button.pay_tax"), "tax-pay-button", "tax-pay-button", onVisit);

        return tileInfoPane;
    }

    private String getTaxDescription(TaxTile taxTile) {
        return PropertyReader.getInstance().getTileDescription(taxTile.getId().replaceAll("\\d", "")).formatted(Settings.getMoneyUnit() + taxTile.getAmount());
    }
}