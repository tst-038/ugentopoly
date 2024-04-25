package be.ugent.objprog.ugentopoly.model.tile.visitor;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.*;
import be.ugent.objprog.ugentopoly.model.tile.infopane.factory.*;
import javafx.scene.layout.AnchorPane;

public class TileInfoPaneVisitor implements TileVisitor {
    private final AnchorPane tileInfoPane;
    private final GameManager gameManager;

    public TileInfoPaneVisitor(AnchorPane tileInfoPane, GameManager gameManager) {
        this.tileInfoPane = tileInfoPane;
        this.gameManager = gameManager;
    }

    private void updateTileInfoPane(Tile tile, ITileInfoPaneFactory factory, boolean onVisit) {
        tileInfoPane.setVisible(false);
        tileInfoPane.getChildren().clear();
        AnchorPane infoPane = factory.createTileInfoPane(tile, onVisit);
        this.tileInfoPane.getChildren().add(infoPane);
    }

    @Override
    public void visit(StreetTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new StreetTileInfoPaneFactory(gameManager), onVisit);
    }

    @Override
    public void visit(TaxTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new TaxTileInfoPaneFactory(gameManager), onVisit);
    }

    @Override
    public void visit(RailwayTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new RailwayTilePaneFactory(gameManager), onVisit);
    }

    @Override
    public void visit(ChestTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new ChestTileInfoPaneFactory(gameManager), onVisit);
    }

    @Override
    public void visit(ChanceTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new ChanceTileInfoPaneFactory(gameManager), onVisit);
    }

    @Override
    public void visit(UtilityTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new UtilityTileInfoPaneFactory(gameManager), onVisit);
    }

    @Override
    public void visit(GoToJailTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new GoToJailTileInfoPaneFactory(gameManager), onVisit);
    }

    @Override
    public void visit(JailTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new JailTileInfoPaneFactory(gameManager), onVisit);
    }

    @Override
    public void visit(FreeParkingTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new FreeParkingTileInfoPaneFactory(gameManager), onVisit);
    }

    @Override
    public void visit(StartTile tile, boolean onVisit) {
        updateTileInfoPane(tile, new StartTileInfoPaneFactory(gameManager), onVisit);
    }
}