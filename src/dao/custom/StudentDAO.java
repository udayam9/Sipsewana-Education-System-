package dao.custom;

import dao.SuperDAO;
import entity.Program;
import entity.Registration;
import entity.Student;

import java.util.List;


public interface StudentDAO extends SuperDAO<Student,String> {
    String setId ();
    public boolean updateWithRegPro(Student student, Registration registration, Program program);
        public     List<String> getIdList();
        public Student getProgramList(String id);
}
