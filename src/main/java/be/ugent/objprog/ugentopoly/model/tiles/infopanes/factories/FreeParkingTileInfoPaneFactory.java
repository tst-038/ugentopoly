package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.PropertyReader;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.tiles.FreeParkingTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FreeParkingTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView freeParkingImage = createFreeParkingImage();
        Label title = createTitleLabel((FreeParkingTile) tile);
        Label info = createInfoLabel(tile);

        tileInfoPane.getChildren().addAll(freeParkingImage, title, info);

        return tileInfoPane;
    }

    private ImageView createFreeParkingImage() {
        ImageView startImage = new ImageView(ResourceLoader.loadImage("assets/free_parking.png"));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        return createImageView(startImage, 10.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(FreeParkingTile tile) {
        return createLabel(tile.getName(), "free-parking-title", 5.0, 5.0, 50.0, 35.0);
    }

    private Label createInfoLabel(Tile tile) {
        String desc = PropertyReader.getInstance().getTileDescription(tile.getId());
        return createLabel(desc, "free-parking-info", 5.0, 5.0, 110.0, 5.0);
    }
}