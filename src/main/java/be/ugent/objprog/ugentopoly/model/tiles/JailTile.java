package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.interfaces.ImageUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.LabelUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JailTile extends Tile implements UIUpdatable, LabelUpdatable, ImageUpdatable {
    private Map<Player, Integer> prisoners;

    public JailTile(String id, int position) {
        super(id, position, TileType.JAIL);
        prisoners = new HashMap<>();
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("tile.", "") +".png")));
    }

    public void addPrisoner(Player player) {
        prisoners.put(player, 3);
    }

    public void removePrisoner(Player player) {
        prisoners.remove(player);
    }

    //TODO implement
    public void reducePrisonerTurn(Player player) {
        prisoners.put(player, prisoners.get(player) - 1);
        if (prisoners.get(player) == 0) {
            removePrisoner(player);
        }
    }

    @Override
    public void accept(TileVisitor visitor, boolean onVisit) {
        visitor.visit(this, onVisit);
    }

    @Override
    public void acceptUIUpdate(UIUpdateVisitor visitor, Node tileNode, Pane rootPane) {
        visitor.visit(this, tileNode, rootPane);
    }

    @Override
    public void updateUI(Node tileNode, Pane rootPane) {
        updateLabel(tileNode);
        updateImage(tileNode);
    }

    @Override
    public void onVisit(Player player) {
        //Do nothing
    }
}
