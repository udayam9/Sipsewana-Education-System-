package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.ProgramDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Program;
import entity.Registration;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public StudentDTO find(String sId) throws SQLException, ClassNotFoundException {
        Student temp= studentDAO.search(sId);
       return new StudentDTO(
                temp.getSId(),
                temp.getSName(),
                temp.getAddress(),
                temp.getBirthday(),
                temp.getGender(),
                temp.getContact(),
                temp.getEducation()
        );
    }

    @Override
    public boolean add(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.add(new Student(
                studentDTO.getsId(),
                studentDTO.getsName(),
                studentDTO.getAddress(),
                studentDTO.getBirthday(),
                studentDTO.getGender(),
                studentDTO.getContact(),
                studentDTO.getEducation()));
    }

    @Override
    public boolean update(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {

        Student student= new Student(
                studentDTO.getsId(),
                studentDTO.getsName(),
                studentDTO.getAddress(),
                studentDTO.getBirthday(),
                studentDTO.getGender(),
                studentDTO.getContact(),
                studentDTO.getEducation()
        );
        return studentDAO.update(student);
    }

    @Override
    public boolean updateWithRegPro(StudentDTO studentDTO, RegistrationDTO registrationDTO, ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.updateWithRegPro(new Student(
                studentDTO.getsId(),
                studentDTO.getsName(),
                studentDTO.getAddress(),
                studentDTO.getBirthday(),
                studentDTO.getGender(),
                studentDTO.getContact(),
                studentDTO.getEducation()),
                new Registration(
                        registrationDTO.getRId(),
                        registrationDTO.getPId(),
                        registrationDTO.getPName(),
                        registrationDTO.getDate(),
                        registrationDTO.getTime(),
                        registrationDTO.getPayment()),
                new Program(
                        programDTO.getpId(),
                        programDTO.getpName(),
                        programDTO.getDuration(),
                        programDTO.getFee()
                )
                );
    }

    @Override
    public boolean delete(String sid) throws SQLException, ClassNotFoundException {
      return studentDAO.delete(sid);
    }

    @Override
    public ArrayList<StudentDTO> getAll() throws SQLException, ClassNotFoundException {
       List<Student> studentList= studentDAO.getAll();
       ArrayList<StudentDTO> DTOList=new ArrayList<>();
        for ( Student temp:studentList) {
            DTOList.add(new StudentDTO(
                    temp.getSId(),
                    temp.getSName(),
                    temp.getAddress(),
                    temp.getBirthday(),
                    temp.getGender(),
                    temp.getContact(),
                    temp.getEducation()
                    ));
        }
        return DTOList;
    }

    @Override
    public List<String> getIdList() {

        return studentDAO.getIdList();
    }

    @Override
    public String setId() {

        String id=
        studentDAO.setId();
        if (id==null){
            return "SID-001";

        }else{

            int tempId = Integer.parseInt(studentDAO.setId().split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "SID-00"+tempId;
            }else if(tempId<=99){
                return "SID-0"+tempId;
            }else{
                return "SID-"+tempId;
            }

        }

    }

    @Override
    public List<RegistrationDTO> getRegistrationList(String id) {
        Student student1=studentDAO.getProgramList(id);
        List<Registration> rList=student1.getRegistrationList();
        List<RegistrationDTO> reg=new ArrayList<>() ;
        for (Registration temp:rList
             ) {
            reg.add(new RegistrationDTO(
                    temp.getrId(),
                    temp.getpId(),
                    temp.getpName(),
                    temp.getDate(),
                    temp.getTime(),
                    temp.getPayment()
            ));
        }

      return reg;

    }
}
