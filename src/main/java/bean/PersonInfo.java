package bean;

//要存取的通讯录里面的人
public class PersonInfo {
    private Integer personId;//个人编号，主键id
    private String personName;//姓名
    private String personSex;//性别
    private String personPhone;//个人电话 11位
    private String personAddress;//地址
    private String personEmail;//个人E-mail
    private Integer homePhone;//家庭电话 9位
    private Integer postCode;//邮政编码 6位
    private Integer adminId;//外键，属于哪个通讯录拥有者

    public PersonInfo() {
    }

    public PersonInfo(Integer personId, String personName, String personSex, String personPhone, String personAddress, String personEmail, Integer homePhone, Integer postCode, Integer adminId) {
        this.personId = personId;
        this.personName = personName;
        this.personSex = personSex;
        this.personPhone = personPhone;
        this.personAddress = personAddress;
        this.personEmail = personEmail;
        this.homePhone = homePhone;
        this.postCode = postCode;
        this.adminId = adminId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonSex(String personSex) {
        this.personSex = personSex;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public void setHomePhone(Integer homePhone) {
        this.homePhone = homePhone;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonSex() {
        return personSex;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public Integer getHomePhone() {
        return homePhone;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public Integer getAdminId() {
        return adminId;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "personId=" + personId +
                ", personName='" + personName + '\'' +
                ", personSex='" + personSex + '\'' +
                ", personPhone='" + personPhone + '\'' +
                ", personAddress='" + personAddress + '\'' +
                ", personEmail='" + personEmail + '\'' +
                ", homePhone=" + homePhone +
                ", postCode=" + postCode +
                ", adminId=" + adminId +
                '}';
    }
}
