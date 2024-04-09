package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.model.tiles.*;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class UIUpdateVisitorImpl implements UIUpdateVisitor {
    @Override
    public void visit(StreetTile tile, Node tileNode, Pane rootPane) {
        tile.updateUI(tileNode, rootPane);
    }

    @Override
    public void visit(TaxTile tile, Node tileNode, Pane rootPane) {
        tile.updateUI(tileNode, rootPane);
    }

    @Override
    public void visit(RailwayTile tile, Node tileNode, Pane rootPane) {
        tile.updateUI(tileNode, rootPane);
    }

    @Override
    public void visit(ChestTile tile, Node tileNode, Pane rootPane) {
        tile.updateUI(tileNode, rootPane);
    }

    @Override
    public void visit(ChanceTile tile, Node tileNode, Pane rootPane) {
        tile.updateUI(tileNode, rootPane);
    }

    @Override
    public void visit(UtilityTile tile, Node tileNode, Pane rootPane) {
        tile.updateUI(tileNode, rootPane);
    }

    @Override
    public void visit(GoToJailTile tile, Node tileNode, Pane rootPane) {
        tile.updateUI(tileNode, rootPane);
    }

    @Override
    public void visit(JailTile tile, Node tileNode, Pane rootPane) {
        tile.updateUI(tileNode, rootPane);
    }

    @Override
    public void visit(FreeParkingTile tile, Node tileNode, Pane rootPane) {
        tile.updateUI(tileNode, rootPane);
    }

    @Override
    public void visit(StartTile tile, Node tileNode, Pane rootPane) {
        tile.updateUI(tileNode, rootPane);
    }
}