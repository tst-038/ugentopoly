package be.ugent.objprog.ugentopoly.model.tile;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.listener.IImageChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.ILabelChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.IUIChangeVisitor;
import be.ugent.objprog.ugentopoly.ui.manager.TileInfoPaneManager;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;
import java.util.Optional;

public class GoToJailTile extends Tile implements UIUpdatable, ILabelChangeListener, IImageChangeListener {
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
    public void acceptUIUpdate(IUIChangeVisitor visitor, Node tileNode, Pane rootPane) {
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
