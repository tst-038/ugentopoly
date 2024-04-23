package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.GoToJailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class GoToJailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit, Game game) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit, game);
        GoToJailTile goToJailTile = (GoToJailTile) tile;

        addTitleLabelWithImage(tileInfoPane, goToJailTile.getName(), "go-to-jail-title", 10.0, getTileImageView("assets/go_to_jail.png"));
        addDescriptionLabel(tileInfoPane, PropertyReader.getInstance().getTileDescription(goToJailTile.getId()), "go-to-jail-info", 70.0);
        addButton(tileInfoPane, PropertyReader.getInstance().get("button.close"), "close-button", "close-button", onVisit);

        return tileInfoPane;
    }
}