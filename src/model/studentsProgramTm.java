package model;

public class studentsProgramTm {
        private String regId;
        private String pId;
        private String pName;
        private String duration;
        private String date;
        private double fee;

    public studentsProgramTm(String regId, String pId, String pName, String duration, String date, double fee) {
        this.setRegId(regId);
        this.setpId(pId);
        this.setpName(pName);
        this.setDuration(duration);
        this.setDate(date);
        this.setFee(fee);
    }

    public studentsProgramTm() {
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
