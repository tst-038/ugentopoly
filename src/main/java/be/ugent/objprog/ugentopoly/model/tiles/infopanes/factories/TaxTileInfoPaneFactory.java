package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.tiles.TaxTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class TaxTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public TaxTileInfoPaneFactory(Game game) {
        super(game);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        TaxTile taxTile = (TaxTile) tile;

        addTitleLabelWithImage(tileInfoPane, taxTile.getName(), "tax-title", 10.0, getTileImageView("assets/tax.png"));
        addDescriptionLabel(tileInfoPane, getTaxDescription(taxTile, game), "tax-info", 70.0);
        addButton(tileInfoPane, game.getPropertyreader().get("button.pay_tax"), "tax-pay-button", "tax-pay-button", onVisit);

        return tileInfoPane;
    }

    private String getTaxDescription(TaxTile taxTile, Game game) {
        return game.getPropertyreader() .getTileDescription(taxTile.getId().replaceAll("\\d", "")).formatted(game.getSettings().getMoneyUnit() + taxTile.getAmount());
    }
}