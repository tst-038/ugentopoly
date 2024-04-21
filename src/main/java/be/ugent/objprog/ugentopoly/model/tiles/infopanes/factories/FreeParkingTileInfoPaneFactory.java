package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.FreeParkingTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FreeParkingTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        FreeParkingTile freeParkingTile = (FreeParkingTile) tile;

        ImageView freeParkingImage = createImageView(new ImageView(ResourceLoader.loadImage("assets/free_parking.png")), 10.0, 37.5, 37.5, null);
        freeParkingImage.setFitHeight(75.0);
        freeParkingImage.setFitWidth(75.0);

        Label titleLabel = createLabel(freeParkingTile.getName(), "free-parking-title", 5.0, 5.0, 50.0, 35.0);
        String desc = PropertyReader.getInstance().getTileDescription(freeParkingTile.getId());
        Label infoLabel = createLabel(desc, "free-parking-info", 5.0, 5.0, 115.0, null);

        if(onVisit){
            Button claim = createButton(PropertyReader.getInstance().get("button.claim"), "claim-button", "claim-button", 20., 20., 160., null);
            tileInfoPane.getChildren().add(claim);
        }

        tileInfoPane.getChildren().addAll(freeParkingImage, titleLabel, infoLabel);

        return tileInfoPane;
    }
}