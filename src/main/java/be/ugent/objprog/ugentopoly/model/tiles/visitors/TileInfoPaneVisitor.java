package be.ugent.objprog.ugentopoly.model.tiles.visitors;

import be.ugent.objprog.ugentopoly.model.tiles.*;
import be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories.*;
import javafx.scene.layout.AnchorPane;

public class TileInfoPaneVisitor implements TileVisitor {
    private final AnchorPane tileInfoPane;

    public TileInfoPaneVisitor(AnchorPane tileInfoPane) {
        this.tileInfoPane = tileInfoPane;
    }

    private void updateTileInfoPane(Tile tile, TileInfoPaneFactory factory, boolean onVisit) {
        tileInfoPane.setVisible(false);
        tileInfoPane.getChildren().clear();
        AnchorPane infoPane = factory.createTileInfoPane(tile, onVisit);
        this.tileInfoPane.getChildren().add(infoPane);
    }

    @Override
    public void visit(StreetTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new StreetTileInfoPaneFactory(), onVisit);
    }

    @Override
    public void visit(TaxTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new TaxTileInfoPaneFactory(), onVisit);
    }

    @Override
    public void visit(RailwayTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new RailwayTilePaneFactory(), onVisit);
    }

    @Override
    public void visit(ChestTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new ChestTileInfoPaneFactory(), onVisit);
    }

    @Override
    public void visit(ChanceTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new ChanceTileInfoPaneFactory(),  onVisit);
    }

    @Override
    public void visit(UtilityTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new UtilityTileInfoPaneFactory(), onVisit);
    }

    @Override
    public void visit(GoToJailTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new GoToJailTileInfoPaneFactory(), onVisit);
    }

    @Override
    public void visit(JailTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new JailTileInfoPaneFactory(), onVisit);
    }

    @Override
    public void visit(FreeParkingTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new FreeParkingTileInfoPaneFactory(), onVisit);
    }

    @Override
    public void visit(StartTile tile, boolean onVisit){
        updateTileInfoPane(tile, new StartTileInfoPaneFactory(), onVisit);
    }
}