package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.GoToJailTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.layout.AnchorPane;

public class GoToJailTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public GoToJailTileInfoPaneFactory(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        GoToJailTile goToJailTile = (GoToJailTile) tile;

        addTitleLabelWithImage(tileInfoPane, goToJailTile.getName(), "go-to-jail-title", 10.0, getTileImageView("assets/go_to_jail.png"));
        addDescriptionLabel(tileInfoPane, gameManager.getPropertyreader().getTileDescription(goToJailTile.getId()), "go-to-jail-info", 70.0);
        addButton(tileInfoPane, gameManager.getPropertyreader().get("button.close"), "close-button", "close-button", onVisit);

        return tileInfoPane;
    }
}