package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Program implements SuperEntity {
    @Id
    private String pId;
    private String pName;
    private String duration;
    private double fee;

    @ManyToMany(mappedBy = "programList",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Student> studentList = new ArrayList<>();

    public Program() {
    }

    public Program(String programId, String programName, String duration, double fee) {
        this.pId = programId;
        this.pName = programName;
        this.duration = duration;
        this.fee = fee;
    }

    public Program(String programId, String programName, String duration, double fee, List<Student> studentList) {
        this.setPId(programId);
        this.setPName(programName);
        this.setDuration(duration);
        this.setFee(fee);
        this.setStudentList(studentList);
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String programId) {
        this.pId = programId;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String programName) {
        this.pName = programName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId='" + pId + '\'' +
                ", programName='" + pName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", studentList=" + studentList +
                '}';
    }
}
