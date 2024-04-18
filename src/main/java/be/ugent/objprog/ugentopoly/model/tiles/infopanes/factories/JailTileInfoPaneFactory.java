package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.JailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class JailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        JailTile jailTile = (JailTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        ImageView jailImage = createImageView(new ImageView(ResourceLoader.loadImage("assets/jail.png")), null, 37.5, 37.5, null);
        jailImage.setFitHeight(75);
        jailImage.setFitWidth(75);
        jailImage.setPreserveRatio(true);

        Label titleLabel = createLabel(jailTile.getName(), "jail-title", 5.0, 5.0, 10.0, null);
        titleLabel.setGraphic(jailImage);
        titleLabel.setContentDisplay(ContentDisplay.TOP);

        String desc = PropertyReader.getInstance().getTileDescription(jailTile.getId());
        Label infoLabel = createLabel(desc, "jail-info", 5.0, 5.0, 110.0, 5.0);

        tileInfoPane.getChildren().addAll(titleLabel, infoLabel);

        return tileInfoPane;
    }
}