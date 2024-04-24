package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.tiles.GoToJailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class GoToJailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public GoToJailTileInfoPaneFactory(Game game) {
        super(game);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        GoToJailTile goToJailTile = (GoToJailTile) tile;

        addTitleLabelWithImage(tileInfoPane, goToJailTile.getName(), "go-to-jail-title", 10.0, getTileImageView("assets/go_to_jail.png"));
        addDescriptionLabel(tileInfoPane, game.getPropertyreader().getTileDescription(goToJailTile.getId()), "go-to-jail-info", 70.0);
        addButton(tileInfoPane, game.getPropertyreader().get("button.close"), "close-button", "close-button", onVisit);

        return tileInfoPane;
    }
}