package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.card.Card;
import be.ugent.objprog.ugentopoly.model.tile.ChanceTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.layout.AnchorPane;

public class ChanceTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public ChanceTileInfoPaneFactory(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        ChanceTile chanceTile = (ChanceTile) tile;

        addTitleLabelWithImage(tileInfoPane, chanceTile.getName(), "chance-title", 10.0, getTileImageView("assets/chance.png"));
        addDescriptionLabel(tileInfoPane, getChanceDescription(chanceTile, onVisit, gameManager), "chance-info", 50.0);
        addButton(tileInfoPane, gameManager.getPropertyreader().get("button.close"), "chance-tile-close-button", "close-button", onVisit);

        return tileInfoPane;
    }

    private String getChanceDescription(ChanceTile chanceTile, boolean onVisit, GameManager gameManager) {
        if (onVisit) {
            Player currentPlayer = gameManager.getTurnManager().getCurrentPlayer();
            Card card = gameManager.getDeckManager().getChanceDeck().drawCard(currentPlayer);
            currentPlayer.getInventory().addCard(card);
            card.returnToDeck();
            return card.getDescription();
        } else {
            return gameManager.getPropertyreader().getTileDescription(chanceTile.getId());
        }
    }
}