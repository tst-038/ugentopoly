package be.ugent.objprog.ugentopoly.model.tiles.visitors;

import be.ugent.objprog.ugentopoly.model.tiles.*;

public interface TileVisitor {
    void visit(StreetTile tile);

    void visit(TaxTile tile);
    void visit(RailwayTile tile);
    void visit(ChestTile tile);
    void visit(ChanceTile tile);
    void visit(UtilityTile tile);
    void visit(GoToJailTile tile);
    void visit(JailTile tile);
    void visit(FreeParkingTile tile);
    void visit(StartTile tile);
}
