package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;

public class MoveCard extends Card {
    private int position;
    private boolean collect;

    public MoveCard(String id, int position, boolean collect, Deck deck) {
        super(id, String.format(PropertyReader.getInstance().get("card.move_card"), position, collect), CardType.MOVE, deck);
        this.position = position;
        this.collect = collect;
    }

    @Override
    public void execute(Player player) {
        player.setPosition(position);
        if (collect && position < player.getPosition()) {
            Bank.getInstance().deposit(player, Settings.getInstance().getStartBonus());
        }
        player.removeCard(this);
        returnToDeck();
    }
}