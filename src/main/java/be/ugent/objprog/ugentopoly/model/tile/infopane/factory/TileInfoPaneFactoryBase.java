package be.ugent.objprog.ugentopoly.model.tile.infopane.factory;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.reader.PropertyReader;
import be.ugent.objprog.ugentopoly.model.behaviour.IBuyable;
import be.ugent.objprog.ugentopoly.model.behaviour.IOwnable;
import be.ugent.objprog.ugentopoly.model.tile.Tile;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public abstract class TileInfoPaneFactoryBase implements ITileInfoPaneFactory {
    protected static final double PANE_WIDTH = 150.0;
    protected static final double PANE_HEIGHT = 200.0;
    protected static final double LABEL_MARGIN = 5.0;
    protected static final double IMAGE_SIZE = 75.0;
    protected static final double BUTTON_MARGIN = 10.0;
    protected GameManager gameManager;
    protected PropertyReader propertyReader;

    protected TileInfoPaneFactoryBase(GameManager gameManager) {
        this.gameManager = gameManager;
        this.propertyReader = gameManager.getPropertyreader();
    }

    @Override
    public AnchorPane createTileInfoPane(Tile tile, boolean onVisit) {
        AnchorPane tileInfoPane = new AnchorPane();
        tileInfoPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        return tileInfoPane;
    }

    protected Label createLabel(String text, String styleClass, Double leftAnchor, Double rightAnchor, Double topAnchor, Double bottomAnchor) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        label.setPrefWidth(PANE_WIDTH - 2 * LABEL_MARGIN);
        AnchorPane.setLeftAnchor(label, leftAnchor);
        AnchorPane.setRightAnchor(label, rightAnchor);
        AnchorPane.setTopAnchor(label, topAnchor);
        AnchorPane.setBottomAnchor(label, bottomAnchor);
        return label;
    }

    protected ImageView createImageView(ImageView imageView, Double topAnchor) {
        AnchorPane.setTopAnchor(imageView, topAnchor);
        AnchorPane.setLeftAnchor(imageView, 37.5);
        AnchorPane.setRightAnchor(imageView, 37.5);
        imageView.setFitHeight(IMAGE_SIZE);
        imageView.setFitWidth(IMAGE_SIZE);
        return imageView;
    }

    protected Button createButton(String text, String styleClass, String id, Double leftAnchor, Double rightAnchor) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        button.setId(id);
        AnchorPane.setLeftAnchor(button, leftAnchor);
        AnchorPane.setRightAnchor(button, rightAnchor);
        AnchorPane.setTopAnchor(button, 160.0);
        AnchorPane.setBottomAnchor(button, null);
        return button;
    }

    protected void addOwnerInfo(AnchorPane tileInfoPane, IBuyable tile, boolean onVisit) {
        if (tile.getOwner() == null) {
            addForSaleInfo(tileInfoPane, tile, onVisit);
        } else {
            addOwnerLabel(tileInfoPane, tile);
            addPayRentButton(tileInfoPane, tile, onVisit, gameManager);
        }
    }

    private void addForSaleInfo(AnchorPane tileInfoPane, IBuyable tile, boolean onVisit) {
        Label forSaleLabel = createLabel(propertyReader.get("label.for_sale"), "tile-owner-title", 0.0, 0.0, 115.0, null);
        tileInfoPane.getChildren().add(forSaleLabel);

        if (onVisit) {
            Button buyButton = createButton(propertyReader.get("button.buy"), "tile-buy-button", "buy-button", BUTTON_MARGIN, null);
            Button cancelButton = createButton(propertyReader.get("button.close"), "tile-close-button", "close-button", null, BUTTON_MARGIN);
            tileInfoPane.getChildren().addAll(buyButton, cancelButton);
        }

        Label priceLabel = createLabel(gameManager.getSettings().getMoneyUnit() + tile.getPrice(), "tile-owner", 0.0, 0.0, 130.0, null);
        priceLabel.setTextFill(Color.WHITE);
        tileInfoPane.getChildren().add(priceLabel);
    }

    private void addOwnerLabel(AnchorPane tileInfoPane, IOwnable tile) {
        Label ownerTitleLabel = createLabel(propertyReader.get("label.owner"), "tile-owner-title", 0.0, 0.0, 115.0, null);
        tileInfoPane.getChildren().add(ownerTitleLabel);

        Label ownerLabel = createLabel(tile.getOwner().getName(), "tile-owner", 0.0, 0.0, 130.0, null);
        ownerLabel.setTextFill(tile.getOwner().getColor());
        tileInfoPane.getChildren().add(ownerLabel);
    }

    private void addPayRentButton(AnchorPane tileInfoPane, IBuyable tile, boolean onVisit, GameManager gameManager) {
        if (onVisit && tile.getOwner() != gameManager.getTurnManager().getCurrentPlayer()) {
            Button payRentButton = createButton(propertyReader.get("button.pay_rent"), "tile-pay-rent-button", "pay-rent-button", BUTTON_MARGIN, BUTTON_MARGIN);
            tileInfoPane.getChildren().add(payRentButton);
        }
    }

    protected String getTextColorClass(Color backgroundColor) {
        double luminance = 0.299 * backgroundColor.getRed() + 0.587 * backgroundColor.getGreen() + 0.114 * backgroundColor.getBlue();
        return luminance > 0.5 ? "tile-title-black" : "tile-title-white";
    }

    protected ImageView getTileImageView(String imagePath) {
        ImageView tileImage = createImageView(new ImageView(ResourceLoader.loadImage(imagePath)), 10.0);
        tileImage.setFitHeight(60.0);
        tileImage.setFitWidth(60.0);
        tileImage.setPreserveRatio(true);
        return tileImage;
    }

    protected void addTitleLabelWithImage(AnchorPane tileInfoPane, String title, String styleClass, double topAnchor, ImageView image) {
        Label titleLabel = createLabel(title, styleClass, 5.0, 5.0, topAnchor, null);
        titleLabel.setGraphic(image);
        titleLabel.setGraphicTextGap(10.0);
        titleLabel.setContentDisplay(ContentDisplay.TOP);
        tileInfoPane.getChildren().add(titleLabel);
    }

    protected void addDescriptionLabel(AnchorPane tileInfoPane, String description, String styleClass, double topAnchor) {
        Label descriptionLabel = createLabel(description, styleClass, 5.0, 5.0, topAnchor, 5.0);
        tileInfoPane.getChildren().add(descriptionLabel);
    }

    protected void addButton(AnchorPane tileInfoPane, String buttonText, String buttonStyleClass, String buttonId, boolean onVisit) {
        if (onVisit) {
            Button button = createButton(buttonText, buttonStyleClass, buttonId, 20.0, 20.0);
            tileInfoPane.getChildren().add(button);
        }
    }
}