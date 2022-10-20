package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import dto.StudentDTO;
import entity.Program;
import entity.Student;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProgramBO extends SuperBO {
    ProgramDTO find(String Id) throws SQLException, ClassNotFoundException;

    boolean add(ProgramDTO programDTO) throws SQLException, ClassNotFoundException;

    boolean update(ProgramDTO programDTO) throws SQLException, ClassNotFoundException;
    boolean updateWithStudent(ProgramDTO programDTO,StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    ArrayList<ProgramDTO> getAll() throws SQLException, ClassNotFoundException;

    List<String> getIdList();
    List<Student> findProgramForSList (String id) throws SQLException, ClassNotFoundException;

    String setId ();
}
