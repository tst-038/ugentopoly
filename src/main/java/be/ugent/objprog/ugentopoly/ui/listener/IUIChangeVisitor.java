package be.ugent.objprog.ugentopoly.ui.listener;

import be.ugent.objprog.ugentopoly.model.tile.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public interface IUIChangeVisitor {
    void visit(StreetTile tile, Node tileNode, Pane rootPane);

    void visit(TaxTile tile, Node tileNode, Pane rootPane);

    void visit(RailwayTile tile, Node tileNode, Pane rootPane);

    void visit(ChestTile tile, Node tileNode, Pane rootPane);

    void visit(ChanceTile tile, Node tileNode, Pane rootPane);

    void visit(UtilityTile tile, Node tileNode, Pane rootPane);

    void visit(GoToJailTile tile, Node tileNode, Pane rootPane);

    void visit(JailTile tile, Node tileNode, Pane rootPane);

    void visit(FreeParkingTile tile, Node tileNode, Pane rootPane);

    void visit(StartTile tile, Node tileNode, Pane rootPane);
}
