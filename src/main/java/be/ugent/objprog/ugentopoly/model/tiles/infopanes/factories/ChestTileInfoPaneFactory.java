package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.ChestTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ChestTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        ChestTile chestTile = (ChestTile) tile;

        ImageView chestTileImage = createImageView(new ImageView(ResourceLoader.loadImage("assets/chest.png")), 10.0, 37.5, 37.5, null);
        chestTileImage.setFitHeight(75.0);
        chestTileImage.setFitWidth(75.0);

        Label titleLabel = createLabel(chestTile.getName(), "chest-title", 5.0, 5.0, 50.0, 35.0);
        String desc = PropertyReader.getInstance().getTileDescription(chestTile.getId());
        Label infoLabel = createLabel(desc, "chest-info", 5.0, 5.0, 110.0, 5.0);

        tileInfoPane.getChildren().addAll(chestTileImage, titleLabel, infoLabel);

        return tileInfoPane;
    }
}