package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.ChanceTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ChanceTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        ChanceTile chanceTile = (ChanceTile) tile;

        ImageView chanceTileImage = createImageView(new ImageView(ResourceLoader.loadImage("assets/chance.png")), 10.0, 55., 55., null);
        chanceTileImage.setPreserveRatio(true);
        chanceTileImage.setFitHeight(75.0);
        chanceTileImage.setFitWidth(75.0);
        chanceTileImage.setScaleX(1.1);
        chanceTileImage.setScaleY(1.1);

        Label titleLabel = createLabel(chanceTile.getName(), "chance-title", 5.0, 5.0, 50.0, 35.0);
        Label infoLabel;
        if (onVisit) {
            //TODO random chance card here
            infoLabel = createLabel("random chance card here", "chance-info", 5.0, 5.0, 110.0, 5.0);
        } else {
            String desc = PropertyReader.getInstance().getTileDescription(chanceTile.getId());
            infoLabel = createLabel(desc, "chance-info", 5.0, 5.0, 110.0, 5.0);
        }

        tileInfoPane.getChildren().addAll(chanceTileImage, titleLabel, infoLabel);

        return tileInfoPane;
    }
}