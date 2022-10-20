package model;

import javafx.scene.control.Button;

public class programTm {
    private String PId;
    private String PName;
    private String duration;
    private double fee;
    private Button update;
    private Button delete;

    public programTm(String pId, String pName, String duration, double fee, Button update, Button delete) {
        this.setPId(pId);
        this.setPName(pName);
        this.setDuration(duration);
        this.setFee(fee);
        this.setUpdate(update);
        this.setDelete(delete);
    }

    public programTm() {
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

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
