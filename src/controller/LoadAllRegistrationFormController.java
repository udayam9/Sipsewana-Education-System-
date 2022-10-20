package controller;

import bo.BoFactory;
import bo.custom.RegistrationBO;
import entity.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.registrationViewTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoadAllRegistrationFormController {
    private final RegistrationBO registrationBO= (RegistrationBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.REGISTRATION);
    public TableView tblRegistration;
    public TableColumn colRegId;
    public TableColumn colSId;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colPayment;
    public TableColumn colPName;

    public void initialize(){
        colRegId.setCellValueFactory(new PropertyValueFactory<>("RId"));
        colSId.setCellValueFactory(new PropertyValueFactory<>("SId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colPName.setCellValueFactory(new PropertyValueFactory<>("PName"));
        try {
            loadAllRegistration();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadAllRegistration() throws SQLException, ClassNotFoundException {
        List<Registration> rList= registrationBO.getAllAsEntity();
        ObservableList<registrationViewTm> obList= FXCollections.observableArrayList();

        for (Registration temp:rList
        ) {
            obList.add(new registrationViewTm(
                    temp.getrId(),
                    temp.getStudent().getSId(),
                    temp.getDate(),
                    temp.getTime(),
                    String.valueOf(temp.getPayment()),
                    temp.getpName()
            ));
        }
        tblRegistration.setItems(obList);

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
