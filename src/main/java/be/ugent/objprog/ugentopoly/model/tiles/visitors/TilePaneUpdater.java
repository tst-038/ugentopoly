package be.ugent.objprog.ugentopoly.model.tiles.visitors;

import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.TaxTile;

public class TilePaneUpdater implements TileVisitor {
    private final TileInfoPaneUpdater tileInfoPaneUpdater;

    public TilePaneUpdater(TileInfoPaneUpdater tileInfoPaneUpdater) {
        this.tileInfoPaneUpdater = tileInfoPaneUpdater;
    }

    private void updateStreetTilePane(StreetTile tile) {
        tileInfoPaneUpdater.updateStreetTileInfo(tile);
    }

    @Override
    public void visit(StreetTile tile) {
        updateStreetTilePane(tile);
    }

    @Override
    public void visit(TaxTile tile) {

    }
}