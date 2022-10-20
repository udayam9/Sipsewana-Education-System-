package controller;

import bo.BoFactory;
import bo.custom.RegistrationBO;
import bo.custom.StudentBO;
import dto.ProgramDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoadAllStudentFormController {
    private final StudentBO studentBO= (StudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT);
    private final RegistrationBO registrationBO= (RegistrationBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.REGISTRATION);

    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEducation;
    public TableView tblProgram;
    public TableColumn colRegId;
    public TableColumn colPid;
    public TableColumn colProgram;
    public TableColumn colDuration;
    public TableColumn colDate;
    public TableColumn colPayment;
    public ComboBox cmbSId;
    public TextField txtBDay;
    public TextField txtGender;

    public void initialize(){

        colRegId.setCellValueFactory(new PropertyValueFactory<>("RId"));
        colPid.setCellValueFactory(new PropertyValueFactory<>("PId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("PName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));



        loadAllIds();
        cmbSId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setStudantData(newValue);
                loadStudentProgram(newValue);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });}

    private void loadStudentProgram(Object newValue) {
        String id= (String) newValue;
        List<RegistrationDTO> pList= studentBO.getRegistrationList(id);
        ObservableList<RegistrationDTO> obList= FXCollections.observableArrayList();
        for (RegistrationDTO temp:pList
             ) {
            obList.add(temp);
        }

        tblProgram.setItems(obList);
    }

    private void setStudantData(Object newValue) throws SQLException, ClassNotFoundException {
        String id= (String) newValue;
         StudentDTO temp=       studentBO.find(id);
        txtName.setText(temp.getsName());
        txtAddress.setText(temp.getAddress());
        txtBDay.setText(temp.getBirthday());
        txtGender.setText(temp.getGender());
        txtContact.setText(temp.getContact());
        txtEducation.setText(temp.getEducation());
    }

    private void loadAllIds() {
        List<String> listIds= studentBO.getIdList();
        cmbSId.getItems().addAll(listIds);
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
}
