package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.layout.AnchorPane;

public class StartTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public StartTileInfoPaneFactory(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        addTitleLabelWithImage(tileInfoPane, tile.getName(), "start-title", 10.0, getTileImageView("assets/start.png"));
        addDescriptionLabel(tileInfoPane, getStartDescription(tile), "start-info", 70.0);
        addButton(tileInfoPane, gameManager.getPropertyreader().get("button.close"), "claim-button", "close-button", onVisit);

        return tileInfoPane;
    }

    private String getStartDescription(Tile tile) {
        return gameManager.getPropertyreader().getTileDescription(tile.getId()).formatted(gameManager.getSettings().getMoneyUnit() + gameManager.getSettings().getStartBonus());
    }
}