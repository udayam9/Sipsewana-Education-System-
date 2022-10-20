package controller;

import bo.BoFactory;
import bo.custom.ProgramBO;
import bo.custom.RegistrationBO;
import bo.custom.StudentBO;
import dto.ProgramDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class RegistrationFormController {
    private final StudentBO studentBO= (StudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT);
    private final ProgramBO programBO= (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);
    private final RegistrationBO registrationBO= (RegistrationBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.REGISTRATION);


    public TextField txtSName;
    public TextField txtSAddress;
    public TextField txtSContact;
    public TextField txtSEducation;
    public ComboBox cmbStudent;
    public Label lblTime;
    public Label lblDate;
    public TextField txtSBirthday;
    public TextField txtSGender;
    public ComboBox cmbProgram;
    public TextField txtPNAme;
    public TextField txtPDuration;
    public TextField txtFee;
    public Label lblPrice;
    public Label lblRId;
    public StudentDTO tempStudent;
    public ProgramDTO tempProgram;



    public void initialize(){
        lblPrice.setText("000.00/=");
        setId();
        loadStudentIds();
        loadAllPrograms();

        cmbStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setStudantData(String.valueOf(newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setProgramData(String.valueOf(newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        loadDateAndTime();
    }
    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }
    private void setId() {
        lblRId.setText( registrationBO.setId());
    }

    private void setProgramData(String id) throws SQLException, ClassNotFoundException {
       tempProgram=programBO.find(id);
        txtPDuration.setText(tempProgram.getDuration());
        txtFee.setText(String.valueOf(tempProgram.getFee()));
        txtPNAme.setText(tempProgram.getpName());
        lblPrice.setText(String.valueOf( tempProgram.getFee()));
    }

    private void setStudantData(String id) throws SQLException, ClassNotFoundException {
        tempStudent=  studentBO.find(id);
        txtSName.setText(tempStudent.getsName());
        txtSAddress.setText(tempStudent.getAddress());
        txtSBirthday.setText(tempStudent.getBirthday());
        txtSGender.setText(tempStudent.getGender());
        txtSContact.setText(tempStudent.getContact());
        txtSEducation.setText(tempStudent.getEducation());
    }


    private void loadAllPrograms() {
        List<String> listIds= studentBO.getIdList();
        cmbStudent.getItems().addAll(listIds);
    }

    private void loadStudentIds() {
        List<String> listIds= programBO.getIdList();
        cmbProgram.getItems().addAll(listIds);
    }

    public void ConfirmPaymentOnAction(ActionEvent actionEvent) {
        RegistrationDTO reg=new RegistrationDTO(
                lblRId.getText()  ,
                tempProgram.getpId(),
                tempProgram.getpName(),
                lblDate.getText(),
                lblTime.getText(),
                Double.valueOf(  lblPrice.getText())

        );


        try {
            registrationBO.addWithStudent(reg,tempStudent);
            studentBO.updateWithRegPro(tempStudent,reg,tempProgram);
            programBO.updateWithStudent(tempProgram,tempStudent);
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION,"Success...").show();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void backToHomeOnAtion(ActionEvent actionEvent) throws IOException {
        loadUi("DashBoardForm");
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
    public void clearFields(){
        txtSName.clear();
        txtSAddress.clear();
        txtSBirthday.clear();
        txtSGender.clear();
        txtSContact.clear();
        txtSEducation.clear();
        txtPDuration.clear();
            txtFee.clear();
        txtPNAme.clear();
        lblPrice.setText("000.00/=");
    }

    public void manageStudentOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("StudentForm");

    }

    public void managePaymentOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ProgramForm");

    }
}
