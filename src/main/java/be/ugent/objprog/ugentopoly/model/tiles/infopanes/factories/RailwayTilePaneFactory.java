package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.PropertyReader;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.RailwayTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class RailwayTilePaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile);

        ImageView railwayImage = createTileImage();
        Label titleLabel = createTitleLabel(tile);
        Label infoLabel = createInfoLabel(tile);

        tileInfoPane.getChildren().addAll(railwayImage, titleLabel, infoLabel);

        return tileInfoPane;
    }

    private ImageView createTileImage() {
        ImageView railwayImage = new ImageView(ResourceLoader.loadImage("assets/railway.png"));
        railwayImage.setFitHeight(75.0);
        railwayImage.setFitWidth(75.0);
        return createImageView(railwayImage, 10.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(Tile tile) {
        return createLabel(tile.getName(), "railway-title", 5.0, 5.0, 50.0, 35.0);
    }

    private Label createInfoLabel(Tile tile) {
        RailwayTile railwayTile = (RailwayTile) tile;
        String infoText = PropertyReader.getInstance().get("label.price") +": " + Settings.getMoneyUnit() + railwayTile.getPrice();
        return createLabel(infoText, "railway-info", null, 2.0, 110.0, 5.);
    }
}