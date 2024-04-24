package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.model.Area;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

public class AreaPaneUpdater {
    private final AnchorPane rootPane;

    public AreaPaneUpdater(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public void colorAreaPanes(List<Area> areas) {
        for (Area area : areas) {
            String areaId = area.getId();
            String areaClass = getAreaClass(areaId);
            setAreaPaneColor(areaClass, area.getColor());
        }
    }

    private String getAreaClass(String areaId) {
        return "area-" + areaId.substring(4);
    }

    private void setAreaPaneColor(String areaClass, Color color) {
        rootPane.lookupAll("." + areaClass).forEach(node -> {
            Pane areaPane = (Pane) node;
            areaPane.setBackground(new Background(new BackgroundFill(color, null, null)));
        });
    }
}
