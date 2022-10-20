package model;

import javafx.scene.control.Button;

public class studentTm {
    private String SId;
    private String SName;
    private String address;
    private String birthday;
    private String gender;
    private String contact;
    private String education;
    private Button update;
    private Button delete;

    public studentTm(String sId, String sName, String address, String birthday, String gender, String contact, String education, Button update, Button delete) {
        this.setSId(sId);
        this.setSName(sName);
        this.setAddress(address);
        this.setBirthday(birthday);
        this.setGender(gender);
        this.setContact(contact);
        this.setEducation(education);
        this.setUpdate(update);
        this.setDelete(delete);
    }

    public studentTm() {
    }

    public String getSId() {
        return SId;
    }

    public void setSId(String SId) {
        this.SId = SId;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
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
