package be.ugent.objprog.ugentopoly.model.card;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;

public class MoneyCard extends Card {
    private final int amount;

    public MoneyCard(String id, int amount, Deck deck) {
        super(id, String.format(
                amount > 0 ?
                deck.getGameManager().getPropertyreader().get("card.money_card_receive") : deck.getGameManager().getPropertyreader().get("card.money_card_pay"),
                deck.getGameManager().getSettings().getMoneyUnit()+Math.abs(amount)), CardType.MONEY, deck);
        this.amount = amount;
    }

    @Override
    public void execute(Player player, GameManager gameManager) {
        if (amount > 0) {
            gameManager.getBank().deposit(player, amount, true);
        } else {
            gameManager.getBank().transferToJackpot(player, -amount);
        }

        player.getInventory().removeCard(this);
    }
}
