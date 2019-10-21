package org.auk.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.auk.data.StudentRepository;
import org.auk.models.Student;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MainController {

    @FXML public PasswordField passwordField;
    @FXML public TextField usernameField;
    @FXML private Text actionTarget;
    @FXML private Button clickBtn;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
//        actionTarget.setText("Sign in button pressed");

        var username = usernameField.getText();
        var password = passwordField.getText();

        if (username.equals("admin") && password.equals("root")) {
            System.out.println("clicked");
//            Logger.getLogger().info();
//            btnOpenNewWindow.setOnAction(new EventHandler<ActionEvent>() {
//                public void handle(ActionEvent event) {
                    Parent root;
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/fxml/layout_table.fxml"));
                        root = loader.load();

//                        root = FXMLLoader.load(getClass().getClassLoader().getResource("/fxml/layout_table.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("List of all Students");
                        stage.setScene(new Scene(root, 450, 450));
                        stage.show();
                        // Hide this current window (if this is what you want)
//                        ((Node)(event.getSource())).getScene().getWindow().hide();

//                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Demo.fxml"));
//                        Parent root1 = (Parent) fxmlLoader.load();
//                        Stage stage = new Stage();
//                        stage.initModality(Modality.APPLICATION_MODAL);
//                        stage.initStyle(StageStyle.UNDECORATED);
//                        stage.setTitle("ABC");
//                        stage.setScene(new Scene(root1));
//                        stage.show();
                    }
                    catch (IOException e) {
//                        e.printStackTrace();
                    }
//                }
//            }
        }
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
}
