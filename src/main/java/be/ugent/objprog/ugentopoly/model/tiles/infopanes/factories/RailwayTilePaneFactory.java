package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.tiles.RailwayTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class RailwayTilePaneFactory extends TileInfoPaneFactoryBase {
    public RailwayTilePaneFactory(Game game) {
        super(game);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        RailwayTile railwayTile = (RailwayTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        addTitleLabelWithImage(tileInfoPane, railwayTile.getName(), "railway-title", 10.0, getTileImageView("assets/railway.png"));
        addOwnerInfo(tileInfoPane, railwayTile, onVisit);

        return tileInfoPane;
    }
}