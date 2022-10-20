package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Registration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RegistrationBO extends SuperBO {
    boolean add(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException;
    boolean addWithStudent(RegistrationDTO registrationDTO, StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    ArrayList<RegistrationDTO> getAll() throws SQLException, ClassNotFoundException;
    List<Registration> getAllAsEntity() throws SQLException, ClassNotFoundException;

    String setId ();
    public List<RegistrationDTO> getRegistrationList(String id);
}
