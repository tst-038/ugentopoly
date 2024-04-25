package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.controller.PlayerManager;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;

import java.util.List;

public class DiceRolledEventHandler {

    private final GameLogBook gameLogBook;
    private final Bank bank;
    private final Settings settings;
    private final PlayerManager playerManager;
    private final TurnHandler turnHandler;

    public DiceRolledEventHandler(Game game, TurnHandler turnHandler) {
        this.gameLogBook = game.getLogBook();
        this.bank = game.getBank();
        this.settings = game.getSettings();
        this.playerManager = game.getPlayerManager();
        this.turnHandler = turnHandler;
    }

    public void onDiceRolled(Player player, List<Integer> rolls) {
        int diceResult = rolls.stream().mapToInt(Integer::intValue).sum();
        boolean hasRolledDouble = rolls.get(0).equals(rolls.get(1));

        DoubleRollHandler doubleRollHandler = new DoubleRollHandler(settings);
        boolean shouldGoToJail = doubleRollHandler.handleDoubleRoll(player, hasRolledDouble);
        if (shouldGoToJail) {
            turnHandler.nextPlayer();
            return;
        }

        JailHandler jailHandler = new JailHandler();
        boolean canContinueTurn = jailHandler.handle(player, hasRolledDouble);
        if (!canContinueTurn) {
            turnHandler.nextPlayer();
            return;
        }

        GoToJailHandler goToJailHandler = new GoToJailHandler();
        boolean landedOnGoToJail = goToJailHandler.handle(player);
        System.out.println("before setoninfopanecloselistener");

        player.getGame().getTileInfoPaneManager().setOnInfoPaneClosedListener(() -> {
            if (!hasRolledDouble || landedOnGoToJail) {
                turnHandler.nextPlayer();
            } else {
                turnHandler.continueTurn();
            }
        });

        PlayerPositionUpdater playerPositionUpdater = new PlayerPositionUpdater(gameLogBook, bank, settings);
        playerPositionUpdater.update(player, diceResult);
    }
}
