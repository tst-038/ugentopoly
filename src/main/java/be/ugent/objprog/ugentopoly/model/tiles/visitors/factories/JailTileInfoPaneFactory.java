package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.tiles.JailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class JailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        JailTile jailTile = (JailTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView jailImage = createStartImage();
        Label title = createTitleLabel(jailTile);
        Label info = createInfoLabel();

        tileInfoPane.getChildren().addAll(jailImage, title, info);

        return tileInfoPane;
    }

    private ImageView createStartImage() {
        ImageView startImage = new ImageView(ResourceLoader.loadImage("assets/start.png"));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        return createImageView(startImage, 5.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(JailTile tile) {
        return createLabel(tile.getName(), "jail-title", 5.0, 5.0, 60.0, 35.0);
    }

    private Label createInfoLabel() {
        return createLabel("You are in jail, you can't do anything until you roll a double or pay X$", "jail-info", 5.0, 5.0, 120.0, 5.0);
    }
}
