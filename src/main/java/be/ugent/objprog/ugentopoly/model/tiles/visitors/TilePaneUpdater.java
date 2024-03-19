package be.ugent.objprog.ugentopoly.model.tiles.visitors;

import be.ugent.objprog.ugentopoly.model.tiles.*;

public class TilePaneUpdater implements TileVisitor {
    private final TileInfoPaneUpdater tileInfoPaneUpdater;

    public TilePaneUpdater(TileInfoPaneUpdater tileInfoPaneUpdater) {
        this.tileInfoPaneUpdater = tileInfoPaneUpdater;
    }


    @Override
    public void visit(StreetTile tile) {
        tileInfoPaneUpdater.visit(tile);
    }

    @Override
    public void visit(TaxTile tile) {
        tileInfoPaneUpdater.visit(tile);

    }

    @Override
    public void visit(GoToJailTile tile) {
        tileInfoPaneUpdater.visit(tile);
    }

    @Override
    public void visit(JailTile tile) {
        tileInfoPaneUpdater.visit(tile);
    }

    @Override
    public void visit(ChanceTile tile) {
        tileInfoPaneUpdater.visit(tile);
    }

    @Override
    public void visit(UtilityTile tile) {
        tileInfoPaneUpdater.visit(tile);
    }

    @Override
    public void visit(FreeParkingTile tile) {
        tileInfoPaneUpdater.visit(tile);
    }

    @Override
    public void visit(StartTile tile) {
        tileInfoPaneUpdater.visit(tile);
    }

    @Override
    public void visit(RailwayTile tile) {
        tileInfoPaneUpdater.visit(tile);
    }

    @Override
    public void visit(ChestTile tile) {
        tileInfoPaneUpdater.visit(tile);
    }
}