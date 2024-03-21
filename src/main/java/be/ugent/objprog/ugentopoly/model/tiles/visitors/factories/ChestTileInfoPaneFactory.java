package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.tiles.ChestTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class ChestTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView chestTileImage = createChestImage();
        Label title = createTitleLabel((ChestTile) tile);
        Label info = createInfoLabel();

        tileInfoPane.getChildren().addAll(chestTileImage, title, info);

        return tileInfoPane;
    }

    private ImageView createChestImage() {
        ImageView startImage = new ImageView(ResourceLoader.loadImage("assets/chest.png"));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        return createImageView(startImage, 5.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(ChestTile tile) {
        return createLabel(tile.getName(), FontWeight.BOLD, 20, Color.WHITE, 5.0, 5.0, 80.0, 35.0);
    }

    private Label createInfoLabel() {
        return createLabel("Draw a card from the chest", FontWeight.NORMAL, 12, Color.WHITE, 5.0, 5.0, 140.0, 5.0);
    }
}