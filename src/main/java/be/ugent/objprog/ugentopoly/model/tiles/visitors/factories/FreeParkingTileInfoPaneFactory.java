package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.tiles.FreeParkingTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class FreeParkingTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView freeParkingImage = createFreeParkingImage();
        Label title = createTitleLabel((FreeParkingTile) tile);
        Label info = createInfoLabel();

        tileInfoPane.getChildren().addAll(freeParkingImage, title, info);

        return tileInfoPane;
    }

    private ImageView createFreeParkingImage() {
        ImageView startImage = new ImageView(ResourceLoader.loadImage("assets/free_parking.png"));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        return createImageView(startImage, 5.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(FreeParkingTile tile) {
        return createLabel(tile.getName(), FontWeight.BOLD, 20, Color.WHITE, 5.0, 5.0, 60.0, 35.0);
    }

    private Label createInfoLabel() {
        String infoText = "You are in the Free Parking tile. You can rest here and enjoy the view.";
        return createLabel(infoText, FontWeight.NORMAL, 12, Color.WHITE, 5.0, 5.0, 140.0, 5.0);
    }
}