package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.StartTile;
import be.ugent.objprog.ugentopoly.model.tiles.TaxTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class StartTileInfoPaneFactory implements TileInfoPaneFactory {
    private final Settings settings;

    public StartTileInfoPaneFactory(Settings settings) {
        this.settings = settings;
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        if(tile.getType() != TileType.START) {
            throw new IllegalArgumentException("Tile must be of type "+TileType.START);
        }

        StartTile startTile = (StartTile) tile;
        AnchorPane tileInfoPane = new AnchorPane();

        ImageView startImage = createStartImage();
        Label reason = createTitleLabel(startTile);
        Label info = createInfoLabel();

        tileInfoPane.getChildren().addAll(startImage, reason, info);

        return tileInfoPane;
    }

    private ImageView createStartImage() {
        ImageView startImage = new ImageView(new Image(Ugentopoly.class.getResourceAsStream("assets/start.png")));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        AnchorPane.setTopAnchor(startImage, 5.0);
        AnchorPane.setLeftAnchor(startImage, 37.5);
        AnchorPane.setRightAnchor(startImage, 37.5);
        return startImage;
    }

    private Label createTitleLabel(StartTile tile) {
        Label info = new Label(tile.getName());
        info.setFont(Font.font("System", FontWeight.BOLD, 20));
        info.setWrapText(true);
        info.setAlignment(Pos.CENTER);
        info.setTextAlignment(TextAlignment.CENTER);
        info.setTextFill(Color.WHITE);
        info.setPrefWidth(140.0);
        AnchorPane.setLeftAnchor(info, 5.0);
        AnchorPane.setRightAnchor(info, 5.0);
        AnchorPane.setTopAnchor(info, 60.0);
        AnchorPane.setBottomAnchor(info, 35.0);
        return info;
    }

    private Label createInfoLabel() {
        // TODO ask if we can add fields in the configuration files, if allowed remove hardcoded values
        Label info = new Label("This is the start position. You will receive " + Settings.getMoneyUnit()+ settings.getStartAmount() + " when you pass this tile.");
        info.setFont(Font.font("System", FontWeight.NORMAL, 12));
        info.setWrapText(true);
        info.setAlignment(Pos.CENTER);
        info.setTextAlignment(TextAlignment.CENTER);
        info.setTextFill(Color.WHITE);
        info.setPrefWidth(140.0);
        AnchorPane.setLeftAnchor(info, 5.0);
        AnchorPane.setRightAnchor(info, 5.0);
        AnchorPane.setTopAnchor(info, 120.);
        AnchorPane.setBottomAnchor(info, 5.0);
        return info;
    }


}
