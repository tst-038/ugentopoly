package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;

public class MoveRelCard extends Card {
    private int relative;

    public MoveRelCard(String id, int relative) {
        super(id, String.format(PropertyReader.getInstance().get("card.move_rel_card"), relative), CardType.MOVEREL);
        this.relative = relative;
    }

    @Override
    public void execute(Player player) {
        player.setPosition(player.getPosition() + relative);
        player.removeCard(this);
    }
}
