package controller;

import bo.BoFactory;
import bo.custom.ProgramBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import dto.ProgramDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import model.programTm;
import validation.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class ProgramFormController {
    private final ProgramBO programBO= (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);

    public TextField txtName;
    public TextField txtDuration;
    public TextField txtFee;
    public TableView tblProgram;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colDuration;
    public TableColumn colFee;
    public TableColumn colUpdate;
    public TableColumn colDelete;
    public TextField txtFind;
    public Label lblTime;
    public Label lblDate;
    public Button update;
    public Button delete;
    static ProgramDTO updatingProgram;
    public TextField txtPId;
    public JFXButton btnAdd;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern namePattern = Pattern.compile("^[A-z ]{2,}$");
    Pattern durationPattern = Pattern.compile("^[0-9 A-z]{2,}$");
    Pattern feePattern = Pattern.compile("[1-9][0-9]*([.][0-9]{2})?$");

    public void initialize(){
        storeValidation();


        colId.setCellValueFactory(new PropertyValueFactory<>("PId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("PName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        try {
            loadDateAndTime();
            loadAllPrograms();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private void storeValidation() {
        map.put(txtName, namePattern);
        map.put(txtDuration, durationPattern);
        map.put(txtFee,feePattern);
    }

    private void loadAllPrograms() throws SQLException, ClassNotFoundException {
        ArrayList<ProgramDTO> pList=programBO.getAll();
        ObservableList<programTm> obList= FXCollections.observableArrayList();

        for (ProgramDTO temp:pList
             ) {
            update=new Button("Update");
            update.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        updatingProgram =temp;
                        loadUi("miniProgramUpdateForm");
                        loadAllPrograms();
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
                        programBO.delete(temp.getpId());
                        loadAllPrograms();

                        clearField();
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                } else {

                }

            }  );
            obList.add(new programTm(
               temp.getpId(),
               temp.getpName(),
               temp.getDuration(),
               temp.getFee(),
               update,
               delete
            ));

        }
        tblProgram.setItems(obList);
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

    public void addOnAction(ActionEvent actionEvent) {
        ProgramDTO programDTO=new ProgramDTO(
                txtPId.getText(),
                txtName.getText(),
                txtDuration.getText(),
                Double.valueOf(txtFee.getText())
        );

        try {
            programBO.add(programDTO);

            loadAllPrograms();
            clearField();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        new Alert(Alert.AlertType.CONFIRMATION,"Success...").show();
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
    public void clearField(){
        txtPId.clear();
        txtFee.clear();
        txtDuration.clear();
        txtName.clear();
    }

    public void program_keyRelesed(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            }
        }
    }
}
