package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.cards.Card;
import be.ugent.objprog.ugentopoly.model.cards.Deck;
import be.ugent.objprog.ugentopoly.model.tiles.ChestTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class ChestTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        ChestTile chestTile = (ChestTile) tile;

        addTitleLabelWithImage(tileInfoPane, chestTile.getName(), "chest-title", 10.0, getTileImageView("assets/chest.png"));
        addDescriptionLabel(tileInfoPane, getChestDescription(chestTile, onVisit), "chest-info", 30.0);
        addButton(tileInfoPane, PropertyReader.getInstance().get("button.close"), "chest-tile-close-button", "close-button", onVisit);

        return tileInfoPane;
    }

    private String getChestDescription(ChestTile chestTile, boolean onVisit) {
        if (onVisit) {
            Player currentPlayer = TurnHandler.getInstance().getCurrentPlayer();
            Card card = Deck.getCommunityChestDeck().drawCard(currentPlayer);
            currentPlayer.addCard(card);
            return card.getDescription();
        } else {
            return PropertyReader.getInstance().getTileDescription(chestTile.getId());
        }
    }
}