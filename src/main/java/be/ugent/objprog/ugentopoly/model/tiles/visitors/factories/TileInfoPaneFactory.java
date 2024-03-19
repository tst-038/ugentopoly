package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public interface TileInfoPaneFactory {
    AnchorPane createTileInfoPane(Tile tile);

    default Label createLabel(String text, int fontSize, Color color, double leftAnchor, double topAnchor) {
        Label label = new Label(text);
        label.setFont(Font.font("System", FontWeight.BOLD, fontSize));
        label.setTextFill(color);
        AnchorPane.setLeftAnchor(label, leftAnchor);
        AnchorPane.setTopAnchor(label, topAnchor);
        return label;
    }
}
