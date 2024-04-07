package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.model.Player;

public interface Ownable {
    Player getOwner();

    void setOwner(Player owner);

    default boolean isOwned() {
        return getOwner() != null;
    }

    String getName();
    void setName(String name);
}
