package be.ugent.objprog.ugentopoly.model.tile;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.behaviour.Visitable;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.listener.UIChangeVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.net.URL;

public abstract class Tile implements Visitable {

    protected final GameManager gameManager;
    private final String id;
    private final int position;
    private final TileType type;
    private final Orientation orientation;
    private String name;

    protected Tile(String id, int position, TileType type, GameManager gameManager) {
        this.id = id.replace("tile.", "");
        this.position = position;
        this.type = type;
        this.orientation = Orientation.getOrientation(position);
        this.gameManager = gameManager;
    }

    public String getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public TileType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public URL getFxmlURL() {
        return Ugentopoly.class.getResource("view/tiles/" + orientation.getDirectoryName() + "/" + type.getFXMLFileName());
    }


    public abstract Image getImage();

    public abstract void accept(TileVisitor visitor, boolean onVisit);

    public abstract void acceptUIUpdate(UIChangeVisitor visitor, Node tileNode, Pane rootPane);
}
