package model;

public class registrationViewTm {
    private String RId;
    private String SId;
    private String date;
    private String time;
    private String payment;
    private String PName;

    public registrationViewTm() {
    }

    public registrationViewTm(String RId, String SId, String date, String time, String payment, String PName) {
        this.setRId(RId);
        this.setSId(SId);
        this.setDate(date);
        this.setTime(time);
        this.setPayment(payment);
        this.setPName(PName);
    }

    public String getRId() {
        return RId;
    }

    public void setRId(String RId) {
        this.RId = RId;
    }

    public String getSId() {
        return SId;
    }

    public void setSId(String SId) {
        this.SId = SId;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }
}
