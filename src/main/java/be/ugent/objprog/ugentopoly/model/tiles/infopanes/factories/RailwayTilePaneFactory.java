package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import be.ugent.objprog.ugentopoly.model.tiles.RailwayTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class RailwayTilePaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit, Game game) {
        RailwayTile railwayTile = (RailwayTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit, game);

        addTitleLabelWithImage(tileInfoPane, railwayTile.getName(), "railway-title", 10.0, getTileImageView("assets/railway.png"));
        addOwnerInfo(tileInfoPane, railwayTile, onVisit, game);

        return tileInfoPane;
    }
}