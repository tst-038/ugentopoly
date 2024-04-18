package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.cards.Card;
import be.ugent.objprog.ugentopoly.model.cards.Deck;
import be.ugent.objprog.ugentopoly.model.tiles.ChestTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ChestTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);
        ChestTile chestTile = (ChestTile) tile;

        ImageView chestTileImage = createImageView(new ImageView(ResourceLoader.loadImage("assets/chest.png")), 10.0, 37.5, 37.5, null);
        chestTileImage.setFitHeight(75.0);
        chestTileImage.setFitWidth(75.0);

        Label infoLabel;

        if (onVisit) {
            Card card = Deck.getCommunityChestDeck().drawCard();
            TurnHandler.getInstance().getCurrentPlayer().addCard(card);
            infoLabel = createLabel(card.getDescription(), "chance-info", 5.0, 5.0, 120.0, null);
            Button cancelButton = createButton(PropertyReader.getInstance().get("button.close"), "chance-tile-close-button", "close-button", 20., 20., 160., null);
            tileInfoPane.getChildren().add(cancelButton);
        }else {
            String desc = PropertyReader.getInstance().getTileDescription(chestTile.getId());
            infoLabel = createLabel(desc, "chance-info", 5.0, 5.0, 110.0, 5.0);
        }

        Label titleLabel = createLabel(chestTile.getName(), "chest-title", 5.0, 5.0, 50.0, 35.0);

        tileInfoPane.getChildren().addAll(chestTileImage, titleLabel, infoLabel);

        return tileInfoPane;
    }
}