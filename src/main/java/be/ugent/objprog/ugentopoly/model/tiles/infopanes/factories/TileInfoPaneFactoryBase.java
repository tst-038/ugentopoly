package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class TileInfoPaneFactoryBase implements TileInfoPaneFactory {
    protected static final double PANE_WIDTH = 150.0;
    protected static final double PANE_HEIGHT = 200.0;

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = new AnchorPane();
        tileInfoPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        return tileInfoPane;
    }

    protected Label createLabel(String text, String styleClass, Double leftAnchor, Double rightAnchor, Double topAnchor, Double bottomAnchor) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        label.setPrefWidth(PANE_WIDTH - 10);
        AnchorPane.setLeftAnchor(label, leftAnchor);
        AnchorPane.setRightAnchor(label, rightAnchor);
        AnchorPane.setTopAnchor(label, topAnchor);
        AnchorPane.setBottomAnchor(label, bottomAnchor);
        return label;
    }

    protected ImageView createImageView(ImageView imageView, Double topAnchor, Double leftAnchor, Double rightAnchor, Double bottomAnchor) {
        AnchorPane.setTopAnchor(imageView, topAnchor);
        AnchorPane.setLeftAnchor(imageView, leftAnchor);
        AnchorPane.setRightAnchor(imageView, rightAnchor);
        AnchorPane.setBottomAnchor(imageView, bottomAnchor);
        return imageView;
    }

    protected Button createButton(String text, String styleClass, String id, Double left, Double right, Double top, Double bottom) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        button.setId(id);
        AnchorPane.setTopAnchor(button, top);
        AnchorPane.setLeftAnchor(button, left);
        AnchorPane.setRightAnchor(button, right);
        AnchorPane.setBottomAnchor(button, bottom);
        return button;
    }
}