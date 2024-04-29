package be.ugent.objprog.ugentopoly.model.behaviour;

import be.ugent.objprog.ugentopoly.model.player.Player;

public interface Visitable {
    String getName();

    void setName(String name);

    int getPosition();

    void onVisit(Player player);
}
