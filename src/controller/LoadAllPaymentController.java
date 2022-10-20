package controller;

import bo.BoFactory;
import bo.custom.ProgramBO;
import dto.ProgramDTO;
import dto.RegistrationDTO;
import entity.Student;
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
import model.programsStudentTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoadAllPaymentController {
    public TextField txtPName;
    public TextField txtDuration;
    public TextField txtFee;
    public TableView tblStudent;
    public TableColumn colSId;
    public TableColumn colSName;
    public TableColumn colAddress;
    public TableColumn colBirthday;
    public TableColumn colContact;
    public TableColumn colGender;
    public ComboBox cmbProgramId;
    private final ProgramBO programBO= (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);


    public void initialize() {
        colSId.setCellValueFactory(new PropertyValueFactory<>("SId"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("SName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadAllIds();
        cmbProgramId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String id=String.valueOf(newValue);
            try {
                setProgramtData(id);
                loadProgramStudents(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });}

    private void loadProgramStudents(String id) throws SQLException, ClassNotFoundException {
        List<Student> sList=programBO.findProgramForSList(id);
        ObservableList<programsStudentTm> obList= FXCollections.observableArrayList();
        for (Student temp:sList
             ) {
            obList.add(new programsStudentTm(
               temp.getSId(),
               temp.getSName(),
                    temp.getAddress(),
                    temp.getBirthday(),
                    temp.getContact(),
                    temp.getGender()
            ));
        }
        tblStudent.setItems(obList);
    }

    private void setProgramtData(String id) throws SQLException, ClassNotFoundException {
        ProgramDTO program=programBO.find(id);
        txtDuration.setText(program.getDuration());
        txtFee.setText(String.valueOf(program.getFee()));
        txtPName.setText(program.getpName());
    }

    private void loadAllIds() {
        List<String> ids= programBO.getIdList();
        cmbProgramId.getItems().addAll(ids);
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
