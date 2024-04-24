package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.log.Log;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LogBookController {

    @FXML
    private Group logbookRoot;

    @FXML
    private TableView<Log> logTableView;

    @FXML
    private TableColumn<Log, String> messageColumn;

    @FXML
    private TableColumn<Log, String> timestampColumn;

    @FXML
    private void initialize() {
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        logTableView.getChildrenUnmodifiable().forEach(node -> {
            if (node instanceof ScrollPane scrollPane) {
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            }
        });
    }

    @FXML
    private void hideLogbook() {
        logbookRoot.setVisible(false);
    }
}
