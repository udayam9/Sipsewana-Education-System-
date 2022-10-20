package controller;

import bo.BoFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXDatePicker;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

import static controller.StudentFormController.updatingStudent;

public class MiniStudentUpdateFormController {
    private final StudentBO studentBO= (StudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT);

    ObservableList<String> obGender= FXCollections.observableArrayList("Male","Female");
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEducation;
    public Label lblStudentId;
    public TextField txtBirthday;
    public ComboBox cmbGender;
    public StudentDTO student;
    String gender;
    public void initialize(){
        student = updatingStudent;
        cmbGender.getItems().addAll(obGender);

        lblStudentId.setText(student.getsId());
        txtName.setText(student.getsName());
        txtAddress.setText(student.getAddress());
        txtContact.setText(student.getContact());
        txtEducation.setText(student.getEducation());
        txtBirthday.setText(student.getBirthday());
        cmbGender.setValue(student.getGender());

        cmbGender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            gender=((String) newValue);
        });
    }

    public void registerOnAction(ActionEvent actionEvent) {

        StudentDTO student= new StudentDTO(
                lblStudentId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtBirthday.getText(),
                gender,
                txtContact.getText(),
                txtEducation.getText()
        );

        try {
            studentBO.update(student);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        closeWindow(actionEvent);
    }
    public void closeWindow(ActionEvent actionEvent){
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
