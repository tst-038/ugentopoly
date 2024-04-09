package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Player;

import java.util.List;

public interface DiceRolledListener {
    void onDiceRolled(Player player, List<Integer> rolls);
}
