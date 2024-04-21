package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;

public class MoveRelCard extends Card {
    private int relative;

    public MoveRelCard(String id, int relative, Deck deck) {
        super(id, String.format(PropertyReader.getInstance().get("card.move_rel_card"), relative), CardType.MOVEREL, deck);
        this.relative = relative;
    }

    @Override
    public void execute(Player player) {
        player.updatePositionDuringGame(player.getPosition() + relative);
        player.removeCard(this);
        returnToDeck();
    }
}
