package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.interfaces.ImageUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class UtilityTile extends Tile implements UIUpdatable, ImageUpdatable {

    private final int cost;

    public UtilityTile(String id, int position, int cost) {
        super(id, position, TileType.UTILITY);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("tile.", "") + ".png")));
    }

    @Override
    public void accept(TileVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void acceptUIUpdate(UIUpdateVisitor visitor, Node tileNode, Pane rootPane) {
        visitor.visit(this, tileNode, rootPane);
    }

    @Override
    public void updateImage(Node tileNode) {
        ImageView imageView = (ImageView) tileNode.lookup("ImageView");
        imageView.setImage(getImage());
    }

    @Override
    public void updateUI(Node tileNode, Pane rootPane) {
        updateImage(tileNode);
    }

    @Override
    public void onVisit(Player player) {

    }
}
