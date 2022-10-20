package controller;

import bo.BoFactory;
import bo.custom.ProgramBO;
import dto.ProgramDTO;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

import static controller.ProgramFormController.updatingProgram;

public class MiniProgramUpdateFormController {
    private final ProgramBO programBO= (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);

    public TextField txtName;
    public TextField txtDuration;
    public TextField txtFee;
    public TextField txtpId;
    ProgramDTO temp=updatingProgram;

    public void initialize(){
        txtpId.setText(temp.getpId());
        txtName.setText(temp.getpName());
        txtDuration.setText(temp.getDuration());
        txtFee.setText(String.valueOf(temp.getFee()));
    }

    public void addOnAction(ActionEvent actionEvent) {
        ProgramDTO programDTO=new ProgramDTO(
                txtpId.getText(),
                txtName.getText(),
                txtDuration.getText(),
                Double.valueOf(txtFee.getText())
        );
        try {
            programBO.update(programDTO);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
