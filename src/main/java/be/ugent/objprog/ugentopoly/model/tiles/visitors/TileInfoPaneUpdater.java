package be.ugent.objprog.ugentopoly.model.tiles.visitors;

import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.*;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.factories.*;
import javafx.scene.layout.AnchorPane;

public class TileInfoPaneUpdater implements TileVisitor {
    private final AnchorPane tileInfoPane;
    private final Settings settings;

    public TileInfoPaneUpdater(AnchorPane tileInfoPane, Settings settings) {
        this.tileInfoPane = tileInfoPane;
        this.settings = settings;
    }

    private void updateTileInfoPane(Tile tile, TileInfoPaneFactory factory) {
        if (tileInfoPane.isVisible()) {
            tileInfoPane.setVisible(false);
        } else {
            tileInfoPane.setVisible(true);
            tileInfoPane.getChildren().clear();
            AnchorPane infoPane = factory.createTileInfoPane(tile);
            this.tileInfoPane.getChildren().add(infoPane);
        }
    }

    @Override
    public void visit(StreetTile tile) {
        updateTileInfoPane(tile, new StreetTileInfoPaneFactory());
    }

    @Override
    public void visit(TaxTile tile) {
        updateTileInfoPane(tile, new TaxTileInfoPaneFactory());
    }

    @Override
    public void visit(RailwayTile tile) {
        updateTileInfoPane(tile, new RailwayTilePaneFactory());
    }

    @Override
    public void visit(ChestTile tile) {
        updateTileInfoPane(tile, new ChestTileInfoPaneFactory());
    }

    @Override
    public void visit(ChanceTile tile) {
        updateTileInfoPane(tile, new ChanceTileInfoPaneFactory());
    }

    @Override
    public void visit(UtilityTile tile) {
        updateTileInfoPane(tile, new UtilityTileInfoPaneFactory());
    }

    @Override
    public void visit(GoToJailTile tile) {
        updateTileInfoPane(tile, new GoToJailTileInfoPaneFactory());
    }

    @Override
    public void visit(JailTile tile) {
        updateTileInfoPane(tile, new JailTileInfoPaneFactory());
    }

    @Override
    public void visit(FreeParkingTile tile) {
        updateTileInfoPane(tile, new FreeParkingTileInfoPaneFactory());
    }

    @Override
    public void visit(StartTile tile){
        updateTileInfoPane(tile, new StartTileInfoPaneFactory(settings));
    }
}