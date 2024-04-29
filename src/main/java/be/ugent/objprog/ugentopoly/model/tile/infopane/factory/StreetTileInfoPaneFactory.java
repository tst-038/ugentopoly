package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.tile.StreetTile;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class StreetTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    public StreetTileInfoPaneFactory(GameManager gameManager) {
        super(gameManager);
    }
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        StreetTile streetTile = (StreetTile) tile;
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        addStreetArea(tileInfoPane, streetTile);
        addTitleLabel(tileInfoPane, streetTile);
        addRentInfo(tileInfoPane, streetTile, 80);
        addPriceInfo(tileInfoPane, streetTile, 60);
        addOwnerInfo(tileInfoPane, streetTile, onVisit);

        return tileInfoPane;
    }

    private void addStreetArea(AnchorPane tileInfoPane, StreetTile streetTile) {
        AnchorPane infoColor = new AnchorPane();
        AnchorPane.setTopAnchor(infoColor, 3.0);
        AnchorPane.setLeftAnchor(infoColor, 3.0);
        AnchorPane.setRightAnchor(infoColor, 3.0);
        infoColor.getStyleClass().add("street-tile-area");
        infoColor.setStyle("-fx-background-color: " + streetTile.getArea().getHexColorString() + "; -fx-border-width: 0 0 3 0; -fx-border-color: black;");
        infoColor.setPrefHeight(50.0);
        infoColor.setPrefWidth(PANE_WIDTH);
        tileInfoPane.getChildren().add(infoColor);
    }

    private void addTitleLabel(AnchorPane tileInfoPane, StreetTile streetTile) {
        Label titleLabel = createLabel(streetTile.getName(), "street-tile-title", 0.0, 0.0, 15., null);
        titleLabel.getStyleClass().add(getTextColorClass(streetTile.getArea().getColor()));
        tileInfoPane.getChildren().add(titleLabel);
    }
}