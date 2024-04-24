package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class UIUpdater {
    private final AreaPaneUpdater areaPaneUpdater;
    private final TileUpdater tileUpdater;
    private final PlayerPanelUpdater playerPanelUpdater;
    private final DiceDialogInitializer diceDialogInitializer;
    private final TileOwnershipUpdater tileOwnershipUpdater;
    private final JackpotLabelBinder jackpotLabelBinder;

    public UIUpdater(AnchorPane rootPane, Game game) {
        PropertyReader propertyReader = game.getPropertyreader();
        this.areaPaneUpdater = new AreaPaneUpdater(rootPane);
        this.tileUpdater = new TileUpdater(rootPane);
        this.playerPanelUpdater = new PlayerPanelUpdater(rootPane, game, propertyReader);
        this.diceDialogInitializer = new DiceDialogInitializer(rootPane);
        this.tileOwnershipUpdater = new TileOwnershipUpdater(rootPane);
        this.jackpotLabelBinder = new JackpotLabelBinder(rootPane, game);
    }

    public void colorAreaPanes(List<Area> areas) {
        areaPaneUpdater.colorAreaPanes(areas);
    }

    public void updateTiles(List<Tile> tiles) {
        tileUpdater.updateTiles(tiles);
    }

    public void updatePlayers(List<Player> players) {
        playerPanelUpdater.updatePlayers(players);
    }

    public void initializeDices(Node diceDialog) {
        diceDialogInitializer.initializeDices(diceDialog);
    }

    public void playerBoughtTile(Player player, Buyable tile) {
        tileOwnershipUpdater.playerBoughtTile(player, tile);
    }

    public void bindJackpot() {
        jackpotLabelBinder.bindJackpot();
    }
}