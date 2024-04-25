package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.TaxTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.layout.AnchorPane;

public class TaxTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public TaxTileInfoPaneFactory(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        TaxTile taxTile = (TaxTile) tile;

        addTitleLabelWithImage(tileInfoPane, taxTile.getName(), "tax-title", 10.0, getTileImageView("assets/tax.png"));
        addDescriptionLabel(tileInfoPane, getTaxDescription(taxTile, gameManager), "tax-info", 70.0);
        addButton(tileInfoPane, gameManager.getPropertyreader().get("button.pay_tax"), "tax-pay-button", "tax-pay-button", onVisit);

        return tileInfoPane;
    }

    private String getTaxDescription(TaxTile taxTile, GameManager gameManager) {
        return gameManager.getPropertyreader() .getTileDescription(taxTile.getId().replaceAll("\\d", "")).formatted(gameManager.getSettings().getMoneyUnit() + taxTile.getAmount());
    }
}