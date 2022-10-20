package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public void StudentRegOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("StudentForm");
        closeWindow(actionEvent);
    }

    public void ProgramRegOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ProgramForm");
        closeWindow(actionEvent);
    }

    public void paymentOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("PaymentForm");
        closeWindow(actionEvent);
    }

    public void StudentViewAllOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("loadAllStudentForm");
        closeWindow(actionEvent);
    }

    public void programViewAllOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("loadAllPayment");
        closeWindow(actionEvent);
    }

    public void paymentViewAllOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("loadAllRegistrationForm");
        closeWindow(actionEvent);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("LogInForm");
        closeWindow(actionEvent);
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
