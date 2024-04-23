package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class StartTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit, Game game) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit, game);

        addTitleLabelWithImage(tileInfoPane, tile.getName(), "start-title", 10.0, getTileImageView("assets/start.png"));
        addDescriptionLabel(tileInfoPane, getStartDescription(tile), "start-info", 70.0);
        addButton(tileInfoPane, PropertyReader.getInstance().get("button.close"), "claim-button", "close-button", onVisit);

        return tileInfoPane;
    }

    private String getStartDescription(Tile tile) {
        return PropertyReader.getInstance().getTileDescription(tile.getId()).formatted(Settings.getMoneyUnit() + Settings.getInstance().getStartBonus());
    }
}