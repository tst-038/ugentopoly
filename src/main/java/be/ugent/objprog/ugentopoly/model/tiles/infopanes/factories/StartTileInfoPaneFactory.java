package be.ugent.objprog.ugentopoly.model.tiles.infopanes.factories;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class StartTileInfoPaneFactory extends TileInfoPaneFactoryBase {
    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit){
        AnchorPane tileInfoPane = super.createTileInfoPane(tile, onVisit);

        ImageView startImage = createImageView(new ImageView(ResourceLoader.loadImage("assets/start.png")), 10.0, 37.5, 37.5, null);
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);

        Label titleLabel = createLabel(tile.getName(), "start-title", 5.0, 5.0, 50.0, 35.0);
        String desc = PropertyReader.getInstance().getTileDescription(tile.getId()).formatted(Settings.getMoneyUnit() + Settings.getInstance().getStartBonus());
        Label infoLabel = createLabel(desc, "start-info", 5.0, 5.0, 80.0, 5.0);

        tileInfoPane.getChildren().addAll(startImage, titleLabel, infoLabel);

        if(onVisit) {
            Button claimButton = createButton(PropertyReader.getInstance().get("button.claim_start_bonus"), "claim-button", "claim-button", 20.0, 20.0, 160.0, null);
            tileInfoPane.getChildren().add(claimButton);
        }

        return tileInfoPane;
    }
}