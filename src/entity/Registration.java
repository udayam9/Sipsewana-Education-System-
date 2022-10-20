package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Registration implements SuperEntity{
    @Id
    private String rId;
    private String pId;
    private String pName;
    private String date;
    private String time;
    private double payment;

    @ManyToOne
    private Student student;

    public Registration(String rId, String pId, String pName, String date, String time, double payment) {
        this.rId = rId;
        this.pId = pId;
        this.pName = pName;
        this.date = date;
        this.time = time;
        this.payment = payment;
    }

    public Registration(String rId, String pId, String pName, String date, String time, double payment, Student student) {
        this.setrId(rId);
        this.setpId(pId);
        this.setpName(pName);
        this.setDate(date);
        this.setTime(time);
        this.setPayment(payment);
        this.setStudent(student);
    }

    public Registration() {
    }



    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}