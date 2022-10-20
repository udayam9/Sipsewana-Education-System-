package bo.custom.impl;

import bo.custom.ProgramBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dao.custom.StudentDAO;
import dto.ProgramDTO;
import dto.StudentDTO;
import entity.Program;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public ProgramDTO find(String Id) throws SQLException, ClassNotFoundException {
        Program temp= programDAO.search(Id);
        return new ProgramDTO(
                temp.getPId(),
                temp.getPName(),
                temp.getDuration(),
                temp.getFee()
        );
    }

    @Override
    public boolean add(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        return programDAO.add(new Program(
                programDTO.getpId(),
                programDTO.getpName(),
                programDTO.getDuration(),
                programDTO.getFee()
        ));
    }

    @Override
    public boolean update(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
      Program program=  new Program(
                programDTO.getpId(),
                programDTO.getpName(),
                programDTO.getDuration(),
                programDTO.getFee()
        );
      return programDAO.update(program);
    }

    @Override
    public boolean updateWithStudent(ProgramDTO programDTO, StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return programDAO.updateWithStudent(
                new Program(
                        programDTO.getpId(),
                        programDTO.getpName(),
                        programDTO.getDuration(),
                        programDTO.getFee()
                ),new Student(
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
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return programDAO.delete(id);
    }

    @Override
    public ArrayList<ProgramDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Program> pList= programDAO.getAll();
        ArrayList<ProgramDTO> list= new ArrayList<>();

        for (Program temp:pList
             ) {
            list.add(new ProgramDTO(
               temp.getPId(),
               temp.getPName(),
               temp.getDuration(),
               temp.getFee()
            ));
        }
        return list;
    }

    @Override
    public List<String> getIdList() {
        return programDAO.getIdList();
    }

    @Override
    public List<Student> findProgramForSList(String id) throws SQLException, ClassNotFoundException {
        Program temp= programDAO.search(id);
        return temp.getStudentList();
    }

    @Override
    public String setId() {
        String id=
                programDAO.setId();
        if (id==null){
            return "PID-001";

        }else{

            int tempId = Integer.parseInt(programDAO.setId().split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "PID-00"+tempId;
            }else if(tempId<=99){
                return "PID-0"+tempId;
            }else{
                return "PID-"+tempId;
            }

        }

    }
}
