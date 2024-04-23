package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.cards.Card;
import be.ugent.objprog.ugentopoly.model.cards.Deck;
import be.ugent.objprog.ugentopoly.model.tiles.ChanceTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.layout.AnchorPane;

public class ChanceTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit, Game game) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit, game);
        ChanceTile chanceTile = (ChanceTile) tile;

        addTitleLabelWithImage(tileInfoPane, chanceTile.getName(), "chance-title", 10.0, getTileImageView("assets/chance.png"));
        addDescriptionLabel(tileInfoPane, getChanceDescription(chanceTile, onVisit, game), "chance-info", 30.0);
        addButton(tileInfoPane, PropertyReader.getInstance().get("button.close"), "chance-tile-close-button", "close-button", onVisit);

        return tileInfoPane;
    }

    private String getChanceDescription(ChanceTile chanceTile, boolean onVisit, Game game) {
        if (onVisit) {
            Player currentPlayer = game.getTurnHandler().getCurrentPlayer();
            Card card = Deck.getChanceDeck().drawCard(currentPlayer);
            currentPlayer.getInventory().addCard(card);
            return card.getDescription();
        } else {
            return PropertyReader.getInstance().getTileDescription(chanceTile.getId());
        }
    }
}