package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;

public class MoneyCard extends Card {
    private int amount;

    public MoneyCard(String id, int amount, Deck deck) {
        super(id, String.format(PropertyReader.getInstance().get("card.money_card"), amount), CardType.MONEY, deck);
        this.amount = amount;
    }

    @Override
    public void execute(Player player) {
        if (amount > 0) {
            Bank.getInstance().deposit(player, amount);
        } else {
            Bank.getInstance().transferToJackpot(player, -amount);
        }

        player.removeCard(this);
        returnToDeck();
    }
}
