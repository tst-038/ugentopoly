module be.ugent.objprog.ugentopoly {
    requires javafx.controls;
    requires javafx.fxml;
    requires be.ugent.objprog.dice;
    requires jdom;

    opens be.ugent.objprog.ugentopoly to javafx.fxml;
    opens be.ugent.objprog.ugentopoly.controller to javafx.fxml;
    exports be.ugent.objprog.ugentopoly;
    exports be.ugent.objprog.ugentopoly.model;
    opens be.ugent.objprog.ugentopoly.model to javafx.fxml;
}