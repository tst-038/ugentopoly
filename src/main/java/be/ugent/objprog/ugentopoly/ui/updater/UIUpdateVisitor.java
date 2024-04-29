package be.ugent.objprog.ugentopoly.ui.updater;

import be.ugent.objprog.ugentopoly.model.tile.*;
import be.ugent.objprog.ugentopoly.ui.listener.UIChangeVisitor;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class UIUpdateVisitor implements UIChangeVisitor {
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