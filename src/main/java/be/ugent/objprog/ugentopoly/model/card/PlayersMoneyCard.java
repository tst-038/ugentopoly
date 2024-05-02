package be.ugent.objprog.ugentopoly.model.card;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.TransactionPriority;
import be.ugent.objprog.ugentopoly.model.player.Player;

import java.util.List;

public class PlayersMoneyCard extends Card {
    private final int amount;

    public PlayersMoneyCard(String id, int amount, Deck deck) {
        super(id, String.format(
                deck.getGameManager().getPropertyreader().get(id),
                deck.getGameManager().getSettings().getMoneyUnit()+Math.abs(amount)
        ), CardType.PLAYERS_MONEY, deck);
        this.amount = amount;
    }

    @Override
    public void execute(Player player, GameManager gameManager) {
        List<Player> players = gameManager.getPlayers();
        players.forEach(p -> {
            if (!p.equals(player)) {
                if (amount > 0) {
                    gameManager.getBank().transfer(p, player, amount, TransactionPriority.MANDATORY);
                } else {
                    gameManager.getBank().transfer(player, p, amount, TransactionPriority.MANDATORY);
                }
            }
        });
        player.getInventory().removeCard(this);
        returnToDeck();
    }
}
