package be.ugent.objprog.ugentopoly.logic.handler;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.ui.manager.PlayerManager;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.logic.PlayerPositionUpdater;
import be.ugent.objprog.ugentopoly.logic.TurnManager;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.Settings;

import java.util.List;

public class DiceRolledHandler {

    private final GameLogBook gameLogBook;
    private final Bank bank;
    private final Settings settings;
    private final PlayerManager playerManager;
    private final TurnManager turnManager;

    public DiceRolledHandler(GameManager gameManager, TurnManager turnManager) {
        this.gameLogBook = gameManager.getLogBook();
        this.bank = gameManager.getBank();
        this.settings = gameManager.getSettings();
        this.playerManager = gameManager.getPlayerManager();
        this.turnManager = turnManager;
    }

    public void onDiceRolled(Player player, List<Integer> rolls) {
        int diceResult = rolls.stream().mapToInt(Integer::intValue).sum();
        boolean hasRolledDouble = rolls.get(0).equals(rolls.get(1));

        DoubleRollHandler doubleRollHandler = new DoubleRollHandler(settings);
        boolean shouldGoToJail = doubleRollHandler.handleDoubleRoll(player, hasRolledDouble);
        if (shouldGoToJail) {
            turnManager.nextPlayer();
            return;
        }

        JailHandler jailHandler = new JailHandler();
        boolean canContinueTurn = jailHandler.handle(player, hasRolledDouble);
        if (!canContinueTurn) {
            turnManager.nextPlayer();
            return;
        }

        GoToJailHandler goToJailHandler = new GoToJailHandler();
        boolean landedOnGoToJail = goToJailHandler.handle(player);

        player.getGameManager().getTileInfoPaneManager().setOnInfoPaneClosedListener(() -> {
            if (!hasRolledDouble || landedOnGoToJail) {
                turnManager.nextPlayer();
            } else {
                turnManager.continueTurn();
            }
        });

        PlayerPositionUpdater playerPositionUpdater = new PlayerPositionUpdater(gameLogBook, bank, settings);
        playerPositionUpdater.update(player, diceResult);
    }
}
