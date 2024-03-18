package be.ugent.objprog.ugentopoly.model.tiles.visitors;

import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.TaxTile;

public interface TileVisitor {
    void visit(StreetTile tile);
    void visit(TaxTile tile);
}
