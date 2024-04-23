package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.model.Player;

public interface Visitable {
    String getName();

    void setName(String name);

    int getPosition();

    void onVisit(Player player);
}
