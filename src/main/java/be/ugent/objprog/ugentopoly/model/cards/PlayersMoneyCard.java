package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;

public class PlayersMoneyCard extends Card {
    private int amount;

    public PlayersMoneyCard(String id, int amount) {
        super(id, String.format(PropertyReader.getInstance().get("card.players_money_card"), amount), CardType.PLAYERS_MONEY);
        this.amount = amount;
    }

    @Override
    public void execute(Player player) {
        // Collect the specified amount from each player
        // ...
        player.removeCard(this);

    }
}
