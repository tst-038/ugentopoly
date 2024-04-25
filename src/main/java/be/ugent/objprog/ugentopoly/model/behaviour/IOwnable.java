package be.ugent.objprog.ugentopoly.model.behaviour;

import be.ugent.objprog.ugentopoly.model.player.Player;

public interface IOwnable {
    Player getOwner();

    void setOwner(Player owner);

    String getName();

    int getPosition();
}
