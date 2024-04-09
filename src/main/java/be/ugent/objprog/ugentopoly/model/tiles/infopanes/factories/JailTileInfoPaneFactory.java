package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.tiles.JailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class JailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        JailTile jailTile = (JailTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView jailImage = createJailImage();
        jailImage.setFitHeight(75);
        jailImage.setFitWidth(75);
        jailImage.setPreserveRatio(true);
        Label title = createTitleLabel(jailTile);
        title.setGraphic(jailImage);
        title.setContentDisplay(ContentDisplay.TOP);
        Label info = createInfoLabel(tile);

        tileInfoPane.getChildren().addAll(title, info);

        return tileInfoPane;
    }

    private ImageView createJailImage() {
        ImageView startImage = new ImageView(ResourceLoader.loadImage("assets/jail.png"));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        return createImageView(startImage, null, 37.5, 37.5, null);
    }

    private Label createTitleLabel(JailTile tile) {
        return createLabel(tile.getName(), "jail-title", 5.0, 5.0, 10.0, null);
    }

    private Label createInfoLabel(Tile tile) {
        String desc = PropertyReader.getInstance().getTileDescription(tile.getId());
        return createLabel(desc,"jail-info", 5.0, 5.0, 110.0, 5.0);
    }
}
