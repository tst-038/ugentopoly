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

    // Export the model package
    exports be.ugent.objprog.ugentopoly.model;

    // Export the exceptions packages
    exports be.ugent.objprog.ugentopoly.exceptions.bank;
    exports be.ugent.objprog.ugentopoly.exceptions.data;
    exports be.ugent.objprog.ugentopoly.exceptions.model;
    exports be.ugent.objprog.ugentopoly.exceptions.ui;
}