package bo;


import bo.custom.impl.ProgramBOImpl;
import bo.custom.impl.RegistrationBOImpl;
import bo.custom.impl.StudentBOImpl;

public class BoFactory {
    // public static BoFactory getBOFactory;
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case REGISTRATION:
                return new RegistrationBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        STUDENT , PROGRAM, REGISTRATION
    }
}
