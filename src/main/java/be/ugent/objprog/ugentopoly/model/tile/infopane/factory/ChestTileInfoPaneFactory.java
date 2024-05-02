package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.card.Card;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.ChestTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.layout.AnchorPane;

public class ChestTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public ChestTileInfoPaneFactory(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        ChestTile chestTile = (ChestTile) tile;

        addTitleLabelWithImage(tileInfoPane, chestTile.getName(), "chest-title", 10.0, getTileImageView("assets/chest.png"));
        addDescriptionLabel(tileInfoPane, getChestDescription(chestTile, onVisit, gameManager), "chest-info", 30.0);
        addButton(tileInfoPane, gameManager.getPropertyreader().get("button.close"), "chest-tile-close-button", "close-button", onVisit);

        return tileInfoPane;
    }

    private String getChestDescription(ChestTile chestTile, boolean onVisit, GameManager gameManager) {
        if (onVisit) {
            Player currentPlayer = gameManager.getTurnManager().getCurrentPlayer();
            Card card = gameManager.getDeckManager().getCommunityDeck().drawCard(currentPlayer);
            currentPlayer.getInventory().addCard(card);
            card.returnToDeck();
            return card.getDescription();
        } else {
            return gameManager.getPropertyreader().getTileDescription(chestTile.getId());
        }
    }
}