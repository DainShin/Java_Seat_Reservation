package com.example.assignment3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Utilities {

    static void openWindow(String viewFileName, String pageTitle) throws Exception{

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(viewFileName + "-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(pageTitle);
        stage.setScene(scene);
        stage.show();

    }
    static Alert createErrorAlert(String title, String content){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        return alert;
    }

}
