package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.GameState;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.interfaces.ImageUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.LabelUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;
import java.util.Optional;

public class GoToJailTile extends Tile implements UIUpdatable, LabelUpdatable, ImageUpdatable {
    public GoToJailTile(String id, int position) {
        super(id, position, TileType.GO_TO_JAIL);
    }
    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/go_to_jail.png")));
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
        TileInfoPaneManager.getInstance().showTileInfo(this, true);
        AnchorPane pane = TileInfoPaneManager.getInstance().getTileInfoPane();

        pane.lookup("#close-button").setOnMouseClicked(event -> {
            Optional<Tile> jail = GameState.getInstance().getBoard().getTiles().stream().filter(tile -> tile.getType()== TileType.JAIL).findFirst();
            if(jail.isPresent()){
                player.setPosition(jail.get().getPosition());
                JailTile jailTile = (JailTile) jail.get();
                jailTile.addPrisoner(player);
            }
            TileInfoPaneManager.getInstance().setPaneClosableAndHide();
        });
    }
}
