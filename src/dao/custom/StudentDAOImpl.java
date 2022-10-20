package dao.custom;

import entity.Program;
import entity.Registration;
import entity.Student;
import entity.SuperEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{
    @Override
    public boolean add(Student t) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Student temp=search(id);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(temp);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student t) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student temp= session.get(Student.class,id);
        transaction.commit();
        session.close();

       return temp;
    }

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query students = session.createQuery("from Student");

        List <Student> studentList=students.list();
        transaction.commit();
        session.close();

        return studentList;
    }

    @Override
    public String setId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query q = session.createQuery("SELECT sId from Student ORDER BY sId DESC");

        List<String> idlist=q.list();
        transaction.commit();
        session.close();
        if (idlist.size() == 0) {
            return null;
        }else {
            return idlist.get(0);

        }

    }

    @Override
    public boolean updateWithRegPro(Student student, Registration registration, Program program) {
        student.getRegistrationList().add(registration);
        student.getProgramList().add(program);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<String> getIdList() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query students = session.createQuery("SELECT sId from Student");

        List <String> studentList=students.list();
        transaction.commit();
        session.close();

        return studentList;
    }

    @Override
    public Student getProgramList(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student temp= session.get(Student.class,id);
        transaction.commit();
        session.close();

        return temp;
    }
}
