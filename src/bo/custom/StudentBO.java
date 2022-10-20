package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {
    StudentDTO find(String sId) throws SQLException, ClassNotFoundException;

    boolean add(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean update(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;
    boolean updateWithRegPro(StudentDTO studentDTO, RegistrationDTO registrationDTO, ProgramDTO programDTO) throws SQLException, ClassNotFoundException;

    boolean delete(String sid) throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> getAll() throws SQLException, ClassNotFoundException;

    List<String> getIdList();

    String setId ();
    public List<RegistrationDTO> getRegistrationList(String id);
}
