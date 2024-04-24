package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.cards.Card;
import be.ugent.objprog.ugentopoly.model.cards.Deck;
import be.ugent.objprog.ugentopoly.model.tiles.ChestTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class ChestTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public ChestTileInfoPaneFactory(Game game) {
        super(game);
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        ChestTile chestTile = (ChestTile) tile;

        addTitleLabelWithImage(tileInfoPane, chestTile.getName(), "chest-title", 10.0, getTileImageView("assets/chest.png"));
        addDescriptionLabel(tileInfoPane, getChestDescription(chestTile, onVisit, game), "chest-info", 30.0);
        addButton(tileInfoPane, game.getPropertyreader().get("button.close"), "chest-tile-close-button", "close-button", onVisit);

        return tileInfoPane;
    }

    private String getChestDescription(ChestTile chestTile, boolean onVisit, Game game) {
        if (onVisit) {
            Player currentPlayer = game.getTurnHandler().getCurrentPlayer();
            Card card = game.getDeckManager().getCommunityDeck().drawCard(currentPlayer);
            currentPlayer.getInventory().addCard(card);
            return card.getDescription();
        } else {
            return game.getPropertyreader().getTileDescription(chestTile.getId());
        }
    }
}