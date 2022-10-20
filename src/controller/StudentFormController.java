package controller;

import bo.BoFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import dto.StudentDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.studentTm;
import validation.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class StudentFormController {
    private final StudentBO studentBO= (StudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT);
    public JFXButton btnAdd;
    ObservableList<String> obGender= FXCollections.observableArrayList("Male","Female");
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEducation;
    public Label lblStudentId;
    public JFXDatePicker txtBirthday;
    public ComboBox cmbGender;
    public TableView tblStudents;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colBirthday;
    public TableColumn colGender;
    public TableColumn colEducation;
    public TableColumn colUpdate;
    public TableColumn colDelete;
    public TextField txtFind;
    public Label lblTime;
    public Label lblDate;
    String gender;
    private Button update;
    private Button delete;
    static StudentDTO updatingStudent;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern namePattern = Pattern.compile("^[A-z ]{2,}$");
    Pattern addressPattern = Pattern.compile("^[A-z ]{3,30}([0-9]{1,2})?$");
    Pattern agePattern = Pattern.compile("[1-9][0-9]*$");
    Pattern phoneNumberPattern = Pattern.compile("^[0-9][-]?[0-9]*$");
    Pattern educationPattern = Pattern.compile("[A-z ]{3,30}([0-9]{1,2})?$");


    public void initialize(){
        storeValidation();


        colId.setCellValueFactory(new PropertyValueFactory<>("SId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("SName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEducation.setCellValueFactory(new PropertyValueFactory<>("education"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        try {
            loadAllStudents();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        cmbGender.getItems().addAll(obGender);
        setNewSId();
        loadDateAndTime();

        txtFind.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                find(newValue);
            }
        });
        cmbGender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            gender=((String) newValue);
        });
    }

    private void storeValidation() {
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtContact, phoneNumberPattern);
        map.put(txtEducation, educationPattern);
    }

    private void loadAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> studentList= studentBO.getAll();
        ObservableList<studentTm> obStudent=FXCollections.observableArrayList();
        for (StudentDTO temp:studentList) {
            update=new Button("Update");
            update.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        System.out.println(temp.getsId());
                        updatingStudent =temp;
                        loadUi("miniStudentUpdateForm");


                        setNewSId();
                        loadAllStudents();
                    } catch (SQLException | ClassNotFoundException | IOException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            delete=new Button("Delete");
            delete.setOnAction((l)-> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure to delete?", yes, no);
                alert.setTitle("Confirmation alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == yes) {

                    try {
                        studentBO.delete(temp.getsId());
                        loadAllStudents();
                        setNewSId();
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                } else {

                }

            }  );
           studentTm s1=new studentTm(
                   temp.getsId(),
                   temp.getsName(),
                   temp.getAddress(),
                   temp.getBirthday(),
                   temp.getGender(),
                   temp.getContact(),
                   temp.getEducation(),
                   update,
                   delete
           );
            System.out.println("======================="+s1.getSId());
            obStudent.add(new studentTm(
                temp.getsId(),
                temp.getsName(),
                temp.getAddress(),
                temp.getBirthday(),
                temp.getGender(),
                temp.getContact(),
                temp.getEducation(),
                update,
                delete
            ));
        }
        tblStudents.setItems(obStudent);
    }

    private void setNewSId() {
       lblStudentId.setText( studentBO.setId());
    }

    public void find(String sId){

    }

    public void registerOnAction(ActionEvent actionEvent) {
        String bDay = txtBirthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        StudentDTO student= new StudentDTO(
                lblStudentId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                bDay,
                gender,
                txtContact.getText(),
                txtEducation.getText()
        );
        try {
            studentBO.add(student);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            setNewSId();
            loadAllStudents();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } new Alert(Alert.AlertType.CONFIRMATION,"Success...").show();
        clearFields();
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
        txtContact.clear();
        txtAddress.clear();
        txtEducation.clear();
        txtName.clear();

    }

    public void Student_KeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            }
        }
    }

}
