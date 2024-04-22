package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.FreeParkingTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class FreeParkingTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        FreeParkingTile freeParkingTile = (FreeParkingTile) tile;

        addTitleLabelWithImage(tileInfoPane, freeParkingTile.getName(), "free-parking-title", 10.0, getTileImageView("assets/free_parking.png"));
        addDescriptionLabel(tileInfoPane, PropertyReader.getInstance().getTileDescription(freeParkingTile.getId()), "free-parking-info", 70.0);
        addButton(tileInfoPane, PropertyReader.getInstance().get("button.claim"), "claim-button", "claim-button", onVisit);

        return tileInfoPane;
    }
}