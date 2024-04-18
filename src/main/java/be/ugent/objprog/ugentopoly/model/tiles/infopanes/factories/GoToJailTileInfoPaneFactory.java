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
        GoToJailTile goToJailTile = (GoToJailTile) tile;

        ImageView goToJailImage = createImageView(new ImageView(ResourceLoader.loadImage("assets/go_to_jail.png")), 10.0, 37.5, 37.5, null);
        goToJailImage.setFitHeight(75.0);
        goToJailImage.setFitWidth(75.0);

        Label titleLabel = createLabel(goToJailTile.getName(), "go-to-jail-title", 5.0, 5.0, 50.0, 35.0);
        String desc = PropertyReader.getInstance().getTileDescription(goToJailTile.getId());
        Label infoLabel = createLabel(desc, "go-to-jail-info", 5.0, 5.0, 110.0, 5.0);

        tileInfoPane.getChildren().addAll(goToJailImage, titleLabel, infoLabel);

        return tileInfoPane;
    }
}