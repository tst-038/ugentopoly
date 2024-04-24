package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class StartTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public StartTileInfoPaneFactory(Game game) {
        super(game);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        addTitleLabelWithImage(tileInfoPane, tile.getName(), "start-title", 10.0, getTileImageView("assets/start.png"));
        addDescriptionLabel(tileInfoPane, getStartDescription(tile), "start-info", 70.0);
        addButton(tileInfoPane, game.getPropertyreader().get("button.close"), "claim-button", "close-button", onVisit);

        return tileInfoPane;
    }

    private String getStartDescription(Tile tile) {
        return game.getPropertyreader().getTileDescription(tile.getId()).formatted(game.getSettings().getMoneyUnit() + game.getSettings().getStartBonus());
    }
}