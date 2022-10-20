package dao.custom;

import entity.Program;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public boolean add(Program program) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(program);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Program temp=search(id);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(temp);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program t) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Program search(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Program temp= session.get(Program.class,s);
        transaction.commit();
        session.close();

        return temp;
    }

    @Override
    public List<Program> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();



        Query programs = session.createQuery("from Program ");
        List<Program> list = programs.list();

        transaction.commit();

        session.close();
        return list;
    }

    @Override
    public String setId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query q = session.createQuery("SELECT pId from Program ORDER BY pId DESC");

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
    public List<String> getIdList() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query qry = session.createQuery("SELECT pId from Program ");

        List <String> plist=qry.list();
        transaction.commit();
        session.close();

        return plist;
    }

    @Override
    public boolean updateWithStudent(Program program, Student student) {
        program.getStudentList().add(student);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(program);
        transaction.commit();
        session.close();
        return true;
    }
}
