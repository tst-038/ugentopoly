module be.ugent.objprog.ugentopoly {
    requires javafx.controls;
    requires javafx.fxml;
    requires be.ugent.objprog.dice;
    requires org.jdom2;
    requires java.desktop;
    requires javafx.media;

    opens be.ugent.objprog.ugentopoly to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.controller to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.model to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.log to javafx.base;

    // Export the main package
    exports be.ugent.objprog.ugentopoly;
    opens be.ugent.objprog.ugentopoly.log.event to javafx.base;
    opens be.ugent.objprog.ugentopoly.model.board to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.model.player to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.ui.element to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.logic to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.ui.manager to javafx.fxml;
}