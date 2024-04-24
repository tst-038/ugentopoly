package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TileOwnershipUpdater {
    private final AnchorPane rootPane;

    public TileOwnershipUpdater(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public void playerBoughtTile(Player player, Buyable tile) {
        Pane tilePane = (Pane) rootPane.lookup("#_" + tile.getPosition());
        Label tileName = (Label) tilePane.lookup("Label");

        if (tileName == null) {
            setOwnerColorForTileWithoutLabel(tilePane, player.getColor());
        } else {
            setOwnerColorForTileWithLabel(tileName, player.getColor());
        }
    }

    private void setOwnerColorForTileWithoutLabel(Pane tilePane, Color ownerColor) {
        ((AnchorPane) tilePane.lookup("#ownerColor")).setBackground(new Background(new BackgroundFill(ownerColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setOwnerColorForTileWithLabel(Label tileName, Color ownerColor) {
        tileName.setTextFill(ownerColor);
        tileName.setFont(Font.font(tileName.getFont().getFamily(), FontWeight.EXTRA_BOLD, tileName.getFont().getSize() + 2));
    }
}