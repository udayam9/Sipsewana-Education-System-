package dto;

public class RegistrationDTO {
    private String RId;
    private String PId;
    private String PName;
    private String date;
    private String time;
    private double payment;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String rId, String pId, String pName, String date, String time, double payment) {
        this.setRId(rId);
        this.setPId(pId);
        this.setPName(pName);
        this.setDate(date);
        this.setTime(time);
        this.setPayment(payment);
    }

    public String getRId() {
        return RId;
    }

    public void setRId(String RId) {
        this.RId = RId;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
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
}
