package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.interfaces.LabelUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class StartTile extends Tile implements UIUpdatable, LabelUpdatable {
    public StartTile(String id, int position, Game game) {
        super(id, position, TileType.START, game);
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("tile.", "") + ".png")));
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
    }

    @Override
    public void onVisit(Player player) {
        TileInfoPaneManager tileInfoPaneManager = game.getTileInfoPaneManager();
        tileInfoPaneManager.showTileInfo(this, true);

        AnchorPane pane = tileInfoPaneManager.getTileInfoPane();
        pane.lookup("#close-button").setOnMouseClicked(event -> {
            game.getBank().deposit(player, game.getSettings().getStartBonus());
            tileInfoPaneManager.setPaneClosableAndHide();
        });
    }
}
