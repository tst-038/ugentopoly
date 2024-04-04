package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.ChestTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.UtilityTile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class UtilityTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView utilityTileImage  = createUtilityImage(tile);
        Label title = createTitleLabel((UtilityTile) tile);
        Label info = createInfoLabel((UtilityTile) tile);

        tileInfoPane.getChildren().addAll(utilityTileImage, title, info);

        return tileInfoPane;
    }

    private ImageView createUtilityImage(Tile tile) {
        ImageView image = new ImageView(ResourceLoader.loadImage("assets/"+tile.getId()+".png"));
        image.setPreserveRatio(true);
        image.setScaleX(1.5);
        image.setScaleY(1.5);
        image.setFitHeight(75.0);
        image.setFitWidth(75.0);
        return createImageView(image, 20.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(UtilityTile tile) {
        return createLabel(tile.getName(), "utility-title", 5.0, 5.0, 0.0, 35.0);
    }

    private Label createInfoLabel(UtilityTile tile) {
        return createLabel("Kostprijs: "+tile.getCost()+ Settings.getMoneyUnit(), "utility-info", 5.0, 5.0, 100.0, 5.0);
    }
}