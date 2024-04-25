package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.JailTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.layout.AnchorPane;

public class JailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public JailTileInfoPaneFactory(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        JailTile jailTile = (JailTile) tile;

        addTitleLabelWithImage(tileInfoPane, jailTile.getName(), "jail-title", 10.0, getTileImageView("assets/jail.png"));
        addDescriptionLabel(tileInfoPane, gameManager.getPropertyreader().getTileDescription(jailTile.getId()), "jail-info", 70.0);

        return tileInfoPane;
    }
}