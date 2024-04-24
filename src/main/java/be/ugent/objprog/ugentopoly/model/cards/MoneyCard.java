package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.Player;

public class MoneyCard extends Card {
    private final int amount;

    public MoneyCard(String id, int amount, Deck deck) {
        super(id, String.format(deck.getGame().getPropertyreader().get("card.money_card"), amount), CardType.MONEY, deck);
        this.amount = amount;
    }

    @Override
    public void execute(Player player, Game game) {
        if (amount > 0) {
            game.getBank().deposit(player, amount);
        } else {
            game.getBank().transferToJackpot(player, -amount);
        }

        player.getInventory().removeCard(this);
        returnToDeck();
    }
}
