package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student implements SuperEntity {
    @Id
    private String sId;
    private String sName;
    private String address;
    private String birthday;
    private String gender;
    private String contact;
    private String education;

    @ManyToMany
    private List<Program> programList = new ArrayList<>();

    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER)
    private List<Registration> registrationList= new ArrayList<>();

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + sId + '\'' +
                ", studentName='" + sName + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", contact='" + contact + '\'' +
                ", education='" + education + '\'' +
                ", programList=" + programList +
                ", registration=" + registrationList +
                '}';
    }

    public Student() {
    }

    public Student(String studentId, String studentName, String address, String birthday, String gender, String contact, String education) {
        this.sId = studentId;
        this.sName = studentName;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.contact = contact;
        this.education = education;
    }

    public Student(String studentId, String studentName, String address, String birthday, String gender, String contact, String education, List<Program> programList, List<Registration> registration) {
        this.setSId(studentId);
        this.setSName(studentName);
        this.setAddress(address);
        this.setBirthday(birthday);
        this.setGender(gender);
        this.setContact(contact);
        this.setEducation(education);
        this.setProgramList(programList);
        this.setRegistrationList(registration);
    }

    public String getSId() {
        return sId;
    }

    public void setSId(String studentId) {
        this.sId = studentId;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String studentName) {
        this.sName = studentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(List<Registration> registration) {
        this.registrationList = registration;
    }
}
