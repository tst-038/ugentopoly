package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.cards.Card;
import be.ugent.objprog.ugentopoly.model.cards.CardType;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.interfaces.ImageUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.LabelUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class ChanceTile extends Tile implements UIUpdatable, LabelUpdatable, ImageUpdatable, Visitable {
    public ChanceTile(String id, int position) {
        super(id, position, TileType.CHANCE);
    }
    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("tile.", "") + ".png")));
    }
    @Override
    public void accept(TileVisitor visitor, boolean onVisit){
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
        TileInfoPaneManager.getInstance().showTileInfo(this, true);
        AnchorPane pane = TileInfoPaneManager.getInstance().getTileInfoPane();

        pane.lookup("#close-button").setOnMouseClicked(event -> { // Makes sure that the previous tile info pane is closed and hidden
            TileInfoPaneManager.getInstance().setPaneClosableAndHide();
            IntStream.range(0, player.getCards().size()).forEachOrdered(i -> player.getCards().get(i).execute(player));
        });
    }
}
