package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileInfoPaneVisitor;
import javafx.scene.layout.AnchorPane;

public class TileInfoPaneManager {
    private static TileInfoPaneManager instance;
    private AnchorPane tileInfoPane;
    private TileInfoPaneVisitor tileInfoPaneUpdater;
    private boolean isClosable;

    private TileInfoPaneManager(AnchorPane tileInfoPane) {
        this.tileInfoPane = tileInfoPane;
        this.tileInfoPaneUpdater = new TileInfoPaneVisitor(tileInfoPane);
        this.isClosable = true;
    }

    public static TileInfoPaneManager getInstance(AnchorPane tileInfoPane) {
        if (instance == null) {
            instance = new TileInfoPaneManager(tileInfoPane);
        }
        return instance;
    }

    public static TileInfoPaneManager getInstance() {
        return instance;
    }

    public void showTileInfo(Tile tile, boolean onVisit) {
        if (tile != null && !isClosable){
            return;
        }
        tile.accept(tileInfoPaneUpdater, onVisit);
        isClosable = !onVisit;
        tileInfoPane.setVisible(true);
    }

    public AnchorPane getTileInfoPane() {
        return tileInfoPane;
    }

    public void hideTileInfoPane() {
        if (isClosable) {
            tileInfoPane.setVisible(false);
        }
    }

    public void setPaneClosableAndHide(){
        isClosable = true;
        hideTileInfoPane();
    }

    public boolean isClosable(){
        return isClosable;
    }
}