package be.ugent.objprog.ugentopoly.logic.listener;

import be.ugent.objprog.ugentopoly.model.player.Player;

import java.util.List;

public interface DiceRolledListener {
    void onDiceRolled(Player player, List<Integer> rolls);
}
