module be.ugent.objprog.ugentopoly {
    requires javafx.controls;
    requires javafx.fxml;
    requires be.ugent.objprog.dice;
    requires org.jdom2;
    requires java.desktop;

    opens be.ugent.objprog.ugentopoly to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.controller to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.model to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.log to javafx.base;

    // Export the main package
    exports be.ugent.objprog.ugentopoly;
}