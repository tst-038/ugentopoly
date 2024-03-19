package be.ugent.objprog.ugentopoly.model.tiles.visitors;

import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.*;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.factories.*;
import javafx.scene.layout.AnchorPane;

public class TileInfoPaneUpdater implements TileVisitor {
    private final AnchorPane tileInfoPane;
    private final StreetTileInfoPaneFactory streetTileInfoPaneFactory;
    private final TaxTileInfoPaneFactory taxTileInfoPaneFactory;
    private final StartTileInfoPaneFactory startTileInfoPaneFactory;
    private final FreeParkingTileInfoPaneFactory freeParkingTileInfoPaneFactory;
    private final GoToJailTileInfoPaneFactory goToJailTileInfoPaneFactory;
    private final JailTileInfoPaneFactory jailTileInfoPaneFactory;
    private final RailwayTilePaneFactory railwayTileInfoPaneFactory;
    private final ChestTileInfoPaneFactory chestTileInfoPaneFactory;

    public TileInfoPaneUpdater(AnchorPane tileInfoPane, Settings settings) {
        this.tileInfoPane = tileInfoPane;
        this.streetTileInfoPaneFactory = new StreetTileInfoPaneFactory();
        this.taxTileInfoPaneFactory = new TaxTileInfoPaneFactory();
        this.startTileInfoPaneFactory = new StartTileInfoPaneFactory(settings);
        this.freeParkingTileInfoPaneFactory = new FreeParkingTileInfoPaneFactory();
        this.goToJailTileInfoPaneFactory = new GoToJailTileInfoPaneFactory();
        this.jailTileInfoPaneFactory = new JailTileInfoPaneFactory();
        this.railwayTileInfoPaneFactory = new RailwayTilePaneFactory();
        this.chestTileInfoPaneFactory = new ChestTileInfoPaneFactory();
    }

    private void updateTileInfoPane(Tile tile, TileInfoPaneFactory factory) {
        if (tileInfoPane.isVisible()) {
            tileInfoPane.setVisible(false);
        } else {
            tileInfoPane.setVisible(true);
            tileInfoPane.getChildren().clear();
            AnchorPane tileInfoPane = factory.createTileInfoPane(tile);
            this.tileInfoPane.getChildren().add(tileInfoPane);
        }
    }

    @Override
    public void visit(StreetTile tile) {
        updateTileInfoPane(tile, streetTileInfoPaneFactory);
    }

    @Override
    public void visit(TaxTile tile) {
        updateTileInfoPane(tile, taxTileInfoPaneFactory);
    }

    @Override
    public void visit(RailwayTile tile) {
        updateTileInfoPane(tile, railwayTileInfoPaneFactory);
    }

    @Override
    public void visit(ChestTile tile) {
        updateTileInfoPane(tile, chestTileInfoPaneFactory);
    }

    @Override
    public void visit(ChanceTile tile) {

    }

    @Override
    public void visit(UtilityTile tile) {
    }

    @Override
    public void visit(GoToJailTile tile) {
        updateTileInfoPane(tile, goToJailTileInfoPaneFactory);
    }

    @Override
    public void visit(JailTile tile) {
        updateTileInfoPane(tile, jailTileInfoPaneFactory);
    }

    @Override
    public void visit(FreeParkingTile tile) {
        updateTileInfoPane(tile, freeParkingTileInfoPaneFactory);
    }

    @Override
    public void visit(StartTile tile){
        updateTileInfoPane(tile, startTileInfoPaneFactory);
    }
}