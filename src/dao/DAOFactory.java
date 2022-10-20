package dao;

import dao.custom.RegistrationDAOImpl;
import dao.custom.ProgramDAOImpl;
import dao.custom.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
               return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT,PROGRAM, REGISTRATION
    }
}
