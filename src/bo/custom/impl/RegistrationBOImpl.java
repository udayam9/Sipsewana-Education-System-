package bo.custom.impl;

import bo.custom.RegistrationBO;
import dao.DAOFactory;
import dao.custom.RegistrationDAO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Registration;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {
    private final RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);


    @Override
    public boolean add(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException {
        return registrationDAO.add(new Registration(
                registrationDTO.getRId(),
                registrationDTO.getPId(),
                registrationDTO.getPName(),
                registrationDTO.getDate(),
                registrationDTO.getTime(),
                registrationDTO.getPayment()
        ));
    }

    @Override
    public boolean addWithStudent(RegistrationDTO registrationDTO, StudentDTO studentDTO) throws SQLException, ClassNotFoundException {


        return registrationDAO.addWithStudent(new Registration(
                        registrationDTO.getRId(),
                        registrationDTO.getPId(),
                        registrationDTO.getPName(),
                        registrationDTO.getDate(),
                        registrationDTO.getTime(),
                        registrationDTO.getPayment()
        ),
                new Student(
                        studentDTO.getsId(),
                        studentDTO.getsName(),
                        studentDTO.getAddress(),
                        studentDTO.getBirthday(),
                        studentDTO.getGender(),
                        studentDTO.getContact(),
                        studentDTO.getEducation()
                )
                );
    }

    @Override
    public ArrayList<RegistrationDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Registration> rList= registrationDAO.getAll();
        ArrayList<RegistrationDTO> list= new ArrayList<>();

        for (Registration temp:rList
        ) {
            list.add(new RegistrationDTO(
                    temp.getrId(),
                    temp.getpId(),
                    temp.getpName(),
                    temp.getDate(),
                    temp.getTime(),
                    temp.getPayment()
            ));
        }
        return list;
    }

    @Override
    public List<Registration> getAllAsEntity() throws SQLException, ClassNotFoundException {
        return registrationDAO.getAll();

    }

    @Override
    public String setId() {
        String id=
                registrationDAO.setId();
        if (id==null){
            return "RID-001";

        }else{

            int tempId = Integer.parseInt(registrationDAO.setId().split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "RID-00"+tempId;
            }else if(tempId<=99){
                return "RID-0"+tempId;
            }else{
                return "RID-"+tempId;
            }

        }
    }

    @Override
    public List<RegistrationDTO> getRegistrationList(String id) {


        return null;
    }
}
