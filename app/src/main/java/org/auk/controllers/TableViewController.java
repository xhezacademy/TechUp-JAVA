package org.auk.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.auk.data.StudentRepository;
import org.auk.models.Student;

import java.net.URL;
import java.util.ResourceBundle;

public class TableViewController {

    @FXML public ListView<Student> listView;

    private StudentRepository repository;
    final private ObservableList<Student> studentList = FXCollections.observableArrayList();

    public TableViewController() {
        repository = new StudentRepository(5);
    }

    // Add event handlers
    @FXML
    private void addAction(ActionEvent action){
//        studentList.add(txtAddItem.getText());
//        System.out.println("Add Button Pushed");
    }

    @FXML
    private void deleteAction(ActionEvent action){
//        int selectedItem = listBoxMain.getSelectionModel().getSelectedIndex();
//        studentList.remove(selectedItem);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentList.setAll(repository.getAll());

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
