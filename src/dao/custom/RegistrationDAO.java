package dao.custom;

import dao.SuperDAO;
import entity.Registration;
import entity.Student;

import java.util.List;

public interface RegistrationDAO extends SuperDAO<Registration,String> {
    String setId ();
    List<String> getIdList();
    public Boolean addWithStudent(Registration reg, Student s);
    public List<Registration> getRegistrationList(String id);
}
