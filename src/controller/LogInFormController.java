package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LogInFormController {
    public TextField txtPassword;
    public TextField txtUserName;

    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        if ("admin".equals(txtUserName.getText())   && "1234".equals(txtPassword.getText())) {
            loadUi("DashBoardForm");
            closeWindow(actionEvent);
        }else {

            new Alert(Alert.AlertType.WARNING,"Wrong UserName Or Password...").show();
        }


    }
    void loadUi(String filename) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+filename+".fxml"))));
        stage.setTitle("Sipsewana");
        stage.show();
    }
    public void closeWindow(ActionEvent actionEvent){
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
