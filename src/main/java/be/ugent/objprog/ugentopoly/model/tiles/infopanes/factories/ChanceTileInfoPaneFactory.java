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

        ImageView chanceTileImage  = createChanceImage();
        Label title = createTitleLabel((ChanceTile) tile);

        //TODO maybe chance this to be more efficient
        Label info = createInfoLabel(tile);

        if (onVisit){
            info = createChanceLabel();
        }

        tileInfoPane.getChildren().addAll(chanceTileImage, title, info);

        return tileInfoPane;
    }

    private ImageView createChanceImage() {
        ImageView chanceImage = new ImageView(ResourceLoader.loadImage("assets/chance.png"));
        chanceImage.setPreserveRatio(true);
        chanceImage.setFitHeight(75.0);
        chanceImage.setFitWidth(75.0);
        chanceImage.setScaleX(1.1);
        chanceImage.setScaleY(1.1);
        return createImageView(chanceImage, 10.0, 55., 55., null);
    }

    private Label createTitleLabel(ChanceTile tile) {
        return createLabel(tile.getName(), "chance-title", 5.0, 5.0, 50.0, 35.0);
    }

    private Label createInfoLabel(Tile tile) {
        String desc = PropertyReader.getInstance().getTileDescription(tile.getId());
        return createLabel(desc, "chance-info", 5.0, 5.0, 110.0, 5.0);
    }

    private Label createChanceLabel() {
        //TODO Fix random chance card
        return createLabel("random chance card here", "chance-info", 5.0, 5.0, 110.0, 5.0);
    }
}