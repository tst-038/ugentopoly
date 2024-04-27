package be.ugent.objprog.ugentopoly.logic.handler;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.logic.PlayerPositionUpdater;
import be.ugent.objprog.ugentopoly.logic.TurnManager;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.player.Player;

import java.util.List;

public class DiceRolledHandler {

    private final Settings settings;
    private final TurnManager turnManager;
    private final PlayerPositionUpdater playerPositionUpdater;

    public DiceRolledHandler(GameManager gameManager, TurnManager turnManager) {
        this.settings = gameManager.getSettings();
        this.turnManager = turnManager;
        this.playerPositionUpdater = gameManager.getPlayerPositionUpdater();
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

        playerPositionUpdater.update(player, diceResult);
    }
}
