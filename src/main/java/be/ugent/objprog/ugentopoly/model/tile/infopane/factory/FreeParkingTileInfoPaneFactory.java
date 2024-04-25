package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.FreeParkingTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.layout.AnchorPane;

public class FreeParkingTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public FreeParkingTileInfoPaneFactory(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        FreeParkingTile freeParkingTile = (FreeParkingTile) tile;

        addTitleLabelWithImage(tileInfoPane, freeParkingTile.getName(), "free-parking-title", 10.0, getTileImageView("assets/free_parking.png"));
        addDescriptionLabel(tileInfoPane, gameManager.getPropertyreader().getTileDescription(freeParkingTile.getId()), "free-parking-info", 70.0);
        addButton(tileInfoPane, gameManager.getPropertyreader().get("button.claim"), "claim-button", "claim-button", onVisit);

        return tileInfoPane;
    }
}