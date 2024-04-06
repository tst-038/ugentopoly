package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.PropertyReader;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class StartTileInfoPaneFactory extends TileInfoPaneFactoryBase {

    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView startImage = createTileImage();
        Label titleLabel = createTitleLabel(tile);
        Label infoLabel = createInfoLabel(tile);

        tileInfoPane.getChildren().addAll(startImage, titleLabel, infoLabel);

        return tileInfoPane;
    }

    private ImageView createTileImage() {
        ImageView startImage = new ImageView(ResourceLoader.loadImage("assets/start.png"));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        return createImageView(startImage, 10.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(Tile tile) {
        return createLabel(tile.getName(), "start-title", 5.0, 5.0, 50.0, 35.0);
    }

    private Label createInfoLabel(Tile tile) {
        String desc = PropertyReader.getInstance().getTileDescription(tile.getId()).formatted(Settings.getMoneyUnit() + Settings.getInstance().getStartAmount());
        return createLabel(desc, "start-info", 5.0, 5.0, 110.0, 5.0);
    }
}