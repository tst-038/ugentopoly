package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.tiles.JailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class JailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        JailTile jailTile = (JailTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView jailImage = createJailImage();
        Label title = createTitleLabel(jailTile);
        Label info = createInfoLabel();

        tileInfoPane.getChildren().addAll(jailImage, title, info);

        return tileInfoPane;
    }

    private ImageView createJailImage() {
        ImageView startImage = new ImageView(ResourceLoader.loadImage("assets/jail.png"));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        return createImageView(startImage, 10.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(JailTile tile) {
        return createLabel(tile.getName(), "jail-title", 5.0, 5.0, 50.0, 35.0);
    }

    private Label createInfoLabel() {
        return createLabel("You are in jail, you can't do anything until you roll a double or pay X$", "jail-info", 5.0, 5.0, 110.0, 5.0);
    }
}
