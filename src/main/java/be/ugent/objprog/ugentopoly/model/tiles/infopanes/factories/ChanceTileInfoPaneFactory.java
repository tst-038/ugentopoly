package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

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
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        ChanceTile chanceTile = (ChanceTile) tile;

        addTitleLabelWithImage(tileInfoPane, chanceTile.getName(), "chance-title", 10.0, getTileImageView("assets/chance.png"));
        addDescriptionLabel(tileInfoPane, getChanceDescription(chanceTile, onVisit), "chance-info", 30.0);
        addButton(tileInfoPane, PropertyReader.getInstance().get("button.close"), "chance-tile-close-button", "close-button", onVisit);

        return tileInfoPane;
    }

    private String getChanceDescription(ChanceTile chanceTile, boolean onVisit) {
        if (onVisit) {
            Player currentPlayer = TurnHandler.getInstance().getCurrentPlayer();
            Card card = Deck.getChanceDeck().drawCard(currentPlayer);
            currentPlayer.addCard(card);
            return card.getDescription();
        } else {
            return PropertyReader.getInstance().getTileDescription(chanceTile.getId());
        }
    }
}