package dto;

public class StudentDTO {
    private String sId;
    private String sName;
    private String address;
    private String birthday;
    private String gender;
    private String contact;
    private String education;

    public StudentDTO(String sId, String sName, String address, String birthday, String gender, String contact, String education) {
        this.setsId(sId);
        this.setsName(sName);
        this.setAddress(address);
        this.setBirthday(birthday);
        this.setGender(gender);
        this.setContact(contact);
        this.setEducation(education);
    }

    public StudentDTO() {
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
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
}
