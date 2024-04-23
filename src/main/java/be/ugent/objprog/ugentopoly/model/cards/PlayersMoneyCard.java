package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.controller.PlayerManager;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.TransactionPriority;

import java.util.List;

public class PlayersMoneyCard extends Card {
    private final int amount;

    public PlayersMoneyCard(String id, int amount, Deck deck) {
        super(id, String.format(PropertyReader.getInstance().get("card.players_money_card"), amount), CardType.PLAYERS_MONEY, deck);
        this.amount = amount;
    }

    @Override
    public void execute(Player player, Game game) {
        List<Player> players = game.getPlayers();
        players.forEach(p -> {
            if (!p.equals(player)) {
                try{
                    if(amount > 0) {
                        game.getBank().transfer(p, player, amount, TransactionPriority.HIGH);
                    } else {
                        game.getBank().transfer(player, p, amount, TransactionPriority.HIGH);
                    }
                } catch (InsufficientFundsException ignored) {}
            }
        });
        player.getInventory().removeCard(this);
        returnToDeck();
    }
}
