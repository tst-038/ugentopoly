package be.ugent.objprog.ugentopoly.model.tile.visitor;

import be.ugent.objprog.ugentopoly.model.tile.*;

public interface TileVisitor {
    void visit(StreetTile tile, boolean onVisit);

    void visit(TaxTile tile, boolean onVisit);

    void visit(RailwayTile tile, boolean onVisit);

    void visit(ChestTile tile, boolean onVisit);

    void visit(ChanceTile tile, boolean onVisit);

    void visit(UtilityTile tile, boolean onVisit);

    void visit(GoToJailTile tile, boolean onVisit);

    void visit(JailTile tile, boolean onVisit);

    void visit(FreeParkingTile tile, boolean onVisit);

    void visit(StartTile tile, boolean onVisit);
}