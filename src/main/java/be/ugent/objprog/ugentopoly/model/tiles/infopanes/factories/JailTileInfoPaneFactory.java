package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.tiles.JailTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class JailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit, Game game) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit, game);
        JailTile jailTile = (JailTile) tile;

        addTitleLabelWithImage(tileInfoPane, jailTile.getName(), "jail-title", 10.0, getTileImageView("assets/jail.png"));
        addDescriptionLabel(tileInfoPane, PropertyReader.getInstance().getTileDescription(jailTile.getId()), "jail-info", 70.0);

        return tileInfoPane;
    }
}