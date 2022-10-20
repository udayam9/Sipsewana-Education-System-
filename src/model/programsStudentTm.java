package model;

public class programsStudentTm {
    private String SId;
    private String SName;
    private String address;
    private String birthday;
    private String contact;
    private String gender;

    public programsStudentTm(String SId, String SName, String address, String birthday, String contact, String gender) {
        this.setSId(SId);
        this.setSName(SName);
        this.setAddress(address);
        this.setBirthday(birthday);
        this.setContact(contact);
        this.setGender(gender);
    }

    public programsStudentTm() {
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
