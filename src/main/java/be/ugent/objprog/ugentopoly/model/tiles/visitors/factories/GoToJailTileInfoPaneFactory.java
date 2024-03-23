package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.tiles.GoToJailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class GoToJailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView goToJailImage = createGoToJailImage();
        Label title = createTitleLabel((GoToJailTile) tile);
        Label info = createInfoLabel();

        tileInfoPane.getChildren().addAll(goToJailImage, title, info);

        return tileInfoPane;
    }

    private ImageView createGoToJailImage() {
        ImageView startImage = new ImageView(ResourceLoader.loadImage("assets/go_to_jail.png"));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        return createImageView(startImage, 5.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(GoToJailTile tile) {
        return createLabel(tile.getName(), "go-to-jail-title", 5.0, 5.0, 80.0, 35.0);
    }

    private Label createInfoLabel() {
        return createLabel("When you land on this tile, you will be sent to jail.", "go-to-jail-info", 5.0, 5.0, 140.0, 5.0);
    }
}