package be.ugent.objprog.ugentopoly.ui.listener;

import javafx.scene.Node;
import javafx.scene.control.Label;

public interface ILabelChangeListener {
    default void updateLabel(Node tileNode) {
        Label label = (Label) tileNode.lookup("Label");
        if (label != null) {
            label.setText(getName());
        }
    }

    String getName();
}
