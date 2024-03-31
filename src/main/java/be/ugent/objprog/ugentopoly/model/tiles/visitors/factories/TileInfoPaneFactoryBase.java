package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class TileInfoPaneFactoryBase implements TileInfoPaneFactory {
    protected static final double PANE_WIDTH = 150.0;
    protected static final double PANE_HEIGHT = 200.0;

    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        AnchorPane tileInfoPane = new AnchorPane();
        tileInfoPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);

        return tileInfoPane;
    }

    protected Label createLabel(String text, String styleClass,
                                Double leftAnchor, Double rightAnchor, Double topAnchor, Double bottomAnchor) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);

        label.setPrefWidth(PANE_WIDTH - 10);

        if (leftAnchor != null) AnchorPane.setLeftAnchor(label, leftAnchor);
        if (rightAnchor != null) AnchorPane.setRightAnchor(label, rightAnchor);
        if (topAnchor != null) AnchorPane.setTopAnchor(label, topAnchor);
        if (bottomAnchor != null) AnchorPane.setBottomAnchor(label, bottomAnchor);

        return label;
    }

    protected ImageView createImageView(ImageView imageView, Double topAnchor, Double leftAnchor, Double rightAnchor, Double bottomAnchor) {
        if (topAnchor != null) AnchorPane.setTopAnchor(imageView, topAnchor);
        if (leftAnchor != null) AnchorPane.setLeftAnchor(imageView, leftAnchor);
        if (rightAnchor != null) AnchorPane.setRightAnchor(imageView, rightAnchor);
        if (bottomAnchor != null) AnchorPane.setBottomAnchor(imageView, bottomAnchor);
        return imageView;
    }

    protected AnchorPane createAnchorPane(AnchorPane anchorPane, Double layoutX, Double layoutY,
                                          Double topAnchor, Double leftAnchor, Double rightAnchor, Double bottomAnchor) {
        if (layoutX != null) anchorPane.setLayoutX(layoutX);
        if (layoutY != null) anchorPane.setLayoutY(layoutY);
        if (topAnchor != null) AnchorPane.setTopAnchor(anchorPane, topAnchor);
        if (leftAnchor != null) AnchorPane.setLeftAnchor(anchorPane, leftAnchor);
        if (rightAnchor != null) AnchorPane.setRightAnchor(anchorPane, rightAnchor);
        if (bottomAnchor != null) AnchorPane.setBottomAnchor(anchorPane, bottomAnchor);
        return anchorPane;
    }
}