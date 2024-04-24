package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.UtilityTile;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class UtilityTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public UtilityTileInfoPaneFactory(Game game) {
        super(game);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        UtilityTile utilityTile = (UtilityTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        addTitleLabelWithImage(tileInfoPane, utilityTile.getName(), "utility-title", 20.0, getTileImageView("assets/" + utilityTile.getId() + ".png"));
        addOwnerInfo(tileInfoPane, utilityTile, onVisit);

        return tileInfoPane;
    }

    @Override
    protected ImageView getTileImageView(String imagePath) {
        ImageView utilityTileImage = createImageView(new ImageView(ResourceLoader.loadImage(imagePath)), 20.0);
        utilityTileImage.setPreserveRatio(true);
        utilityTileImage.setScaleX(1.5);
        utilityTileImage.setScaleY(1.5);

        return utilityTileImage;
    }
}