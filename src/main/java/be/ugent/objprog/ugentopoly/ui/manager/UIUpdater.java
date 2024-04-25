package be.ugent.objprog.ugentopoly.ui.manager;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.data.reader.PropertyReader;
import be.ugent.objprog.ugentopoly.model.board.Area;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.behaviour.IBuyable;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import be.ugent.objprog.ugentopoly.ui.updater.JackpotLabelBinder;
import be.ugent.objprog.ugentopoly.ui.updater.TileUpdater;
import be.ugent.objprog.ugentopoly.ui.dialog.DiceRollDialog;
import be.ugent.objprog.ugentopoly.ui.updater.AreaPaneUpdater;
import be.ugent.objprog.ugentopoly.ui.updater.PlayerPanelUpdater;
import be.ugent.objprog.ugentopoly.ui.updater.TileOwnershipUpdater;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class UIUpdater {
    private final AreaPaneUpdater areaPaneUpdater;
    private final TileUpdater tileUpdater;
    private final PlayerPanelUpdater playerPanelUpdater;
    private final DiceRollDialog diceRollDialog;
    private final TileOwnershipUpdater tileOwnershipUpdater;
    private final JackpotLabelBinder jackpotLabelBinder;

    public UIUpdater(AnchorPane rootPane, GameManager gameManager) {
        PropertyReader propertyReader = gameManager.getPropertyreader();
        this.areaPaneUpdater = new AreaPaneUpdater(rootPane);
        this.tileUpdater = new TileUpdater(rootPane);
        this.playerPanelUpdater = new PlayerPanelUpdater(rootPane, gameManager, propertyReader);
        this.diceRollDialog = new DiceRollDialog(rootPane);
        this.tileOwnershipUpdater = new TileOwnershipUpdater(rootPane);
        this.jackpotLabelBinder = new JackpotLabelBinder(rootPane, gameManager);
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
        diceRollDialog.initializeDices(diceDialog);
    }

    public void playerBoughtTile(Player player, IBuyable tile) {
        tileOwnershipUpdater.playerBoughtTile(player, tile);
    }

    public void bindJackpot() {
        jackpotLabelBinder.bindJackpot();
    }
}