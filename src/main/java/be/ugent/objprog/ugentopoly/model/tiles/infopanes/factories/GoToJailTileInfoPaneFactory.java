package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.GoToJailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GoToJailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        ImageView goToJailImage = createGoToJailImage();
        Label title = createTitleLabel((GoToJailTile) tile);
        Label info = createInfoLabel(tile);

        tileInfoPane.getChildren().addAll(goToJailImage, title, info);

        return tileInfoPane;
    }

    private ImageView createGoToJailImage() {
        ImageView startImage = new ImageView(ResourceLoader.loadImage("assets/go_to_jail.png"));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        return createImageView(startImage, 10.0, 37.5, 37.5, null);
    }

    private Label createTitleLabel(GoToJailTile tile) {
        return createLabel(tile.getName(), "go-to-jail-title", 5.0, 5.0, 50.0, 35.0);
    }

    private Label createInfoLabel(Tile tile) {
        String desc = PropertyReader.getInstance().getTileDescription(tile.getId());
        return createLabel(desc, "go-to-jail-info", 5.0, 5.0, 110.0, 5.0);
    }
}