package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.RailwayTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.layout.AnchorPane;

public class RailwayTilePaneFactory extends TileInfoPaneFactoryBase {
    public RailwayTilePaneFactory(GameManager gameManager) {
        super(gameManager);
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