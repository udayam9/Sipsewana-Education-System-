package dao.custom;

import dao.SuperDAO;
import entity.Program;
import entity.Student;

import java.util.List;

public interface ProgramDAO extends SuperDAO<Program,String> {
    String setId ();
    List<String> getIdList();
    public boolean updateWithStudent(Program program, Student student);
}
