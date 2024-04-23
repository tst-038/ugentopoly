package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.model.Player;

public interface Ownable {
    Player getOwner();

    void setOwner(Player owner);

    String getName();

    int getPosition();
}
