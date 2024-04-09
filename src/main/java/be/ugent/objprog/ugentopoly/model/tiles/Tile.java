package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.net.URL;

public abstract class Tile implements Visitable {

    private final String id;
    private final int position;
    private final TileType type;
    private String name;
    private final Orientation orientation;

    protected Tile(String id, int position, TileType type) {
        this.id = id.replace("tile.", "");
        this.position = position;
        this.type = type;
        this.orientation = Orientation.getOrientation(position);
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

    public abstract void accept(TileVisitor visitor);

    public abstract void acceptUIUpdate(UIUpdateVisitor visitor, Node tileNode, Pane rootPane);
}
