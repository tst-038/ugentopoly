package be.ugent.objprog.ugentopoly.model.tile;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.listener.ImageChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.LabelChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.UIChangeVisitor;
import be.ugent.objprog.ugentopoly.ui.manager.TileInfoPaneManager;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;
import java.util.Optional;

public class GoToJailTile extends Tile implements UIUpdatable, LabelChangeListener, ImageChangeListener {
    public GoToJailTile(String id, int position, GameManager gameManager) {
        super(id, position, TileType.GO_TO_JAIL, gameManager);
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/go_to_jail.png")));
    }

    @Override
    public void accept(TileVisitor visitor, boolean onVisit) {
        visitor.visit(this, onVisit);
    }

    @Override
    public void acceptUIUpdate(UIChangeVisitor visitor, Node tileNode, Pane rootPane) {
        visitor.visit(this, tileNode, rootPane);
    }

    @Override
    public void updateUI(Node tileNode, Pane rootPane) {
        updateLabel(tileNode);
        updateImage(tileNode);
    }

    @Override
    public void onVisit(Player player) {
        TileInfoPaneManager tileInfoPaneManager = gameManager.getTileInfoPaneManager();
        tileInfoPaneManager.showTileInfo(this, true);
        AnchorPane pane = tileInfoPaneManager.getTileInfoPane();

        pane.lookup("#close-button").setOnMouseClicked(event -> {
            Optional<Tile> jail = gameManager.getBoardManager().getBoard().getTiles().stream().filter(tile -> tile.getType() == TileType.JAIL).findFirst();
            if (jail.isPresent()) {
                player.getPosition().updatePosition(jail.get().getPosition());
                player.setInJail(true);
            }
            tileInfoPaneManager.setPaneClosableAndHide();
        });
    }
}
