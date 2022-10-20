package dao.custom;

import bo.BoFactory;
import entity.Registration;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean add(Registration t) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(t);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Registration t) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Registration search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public List getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query qry = session.createQuery("from Registration ");

        List <String> plist=qry.list();
        transaction.commit();
        session.close();

        return plist;
    }

    @Override
    public String setId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query q = session.createQuery("SELECT rId from Registration ORDER BY rId DESC");

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
        return null;
    }

    @Override
    public Boolean addWithStudent(Registration reg, Student s) {
        reg.setStudent(s);
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(reg);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Registration> getRegistrationList(String id) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query qry = session.createQuery(" from Registration WHERE rId= :ID");
        qry.setParameter("ID",id);
        List <Registration> rlist=qry.list();
        transaction.commit();
        session.close();


        return rlist;


    }
}
