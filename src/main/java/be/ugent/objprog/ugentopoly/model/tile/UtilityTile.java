package be.ugent.objprog.ugentopoly.model.tile;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.behaviour.IBuyable;
import be.ugent.objprog.ugentopoly.model.behaviour.IRentable;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.visitor.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.listener.IImageChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.IUIChangeListener;
import be.ugent.objprog.ugentopoly.ui.listener.IUIChangeVisitor;
import be.ugent.objprog.ugentopoly.ui.manager.TileInfoPaneManager;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class UtilityTile extends Tile implements IUIChangeListener, IImageChangeListener, IBuyable, IRentable {

    private final int price;
    private Player owner;

    public UtilityTile(String id, int position, int cost, GameManager gameManager) {
        super(id, position, TileType.UTILITY, gameManager);
        this.price = cost;
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("tile.", "") + ".png")));
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
        TileInfoPaneManager tileInfoPaneManager = gameManager.getTileInfoPaneManager();
        if (owner == player) {
            tileInfoPaneManager.notifyInfoPaneClosed();
            return;
        }

        tileInfoPaneManager.showTileInfo(this, true);
        AnchorPane pane = tileInfoPaneManager.getTileInfoPane();

        if (owner != null) {
            pane.lookup("#pay-rent-button").setOnMouseClicked(event -> {
                payRent(player, gameManager);
                tileInfoPaneManager.setPaneClosableAndHide();
            });
        } else {
            Button buy = (Button) pane.lookup("#buy-button");
            buy.setOnMouseClicked(event -> {
                buy(player, gameManager);
                player.getInventory().addOwnedUtility();
                tileInfoPaneManager.setPaneClosableAndHide();
            });
            buy.setDisable(gameManager.getBank().hasInsufficientBalance(player, getPrice()));
            pane.lookup("#close-button").setOnMouseClicked(event -> tileInfoPaneManager.setPaneClosableAndHide());
        }
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getRent() {
        return gameManager.getDiceHandler().getLastRoll().stream().mapToInt(Integer::intValue).sum() * (owner.getInventory().getOwnedUtilities() == 1 ? 4 : 10);
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
