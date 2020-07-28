package bean;

//通讯录拥有者
public class AdminInfo {
    private Integer adminId;//编号
    private String adminName;//姓名
    private String adminPassWord;//密码

    public AdminInfo() {
    }

    public AdminInfo(Integer adminId, String adminName, String adminPassWord) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPassWord = adminPassWord;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPassWord() {
        return adminPassWord;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminPassWord(String adminPassWord) {
        this.adminPassWord = adminPassWord;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminPassWord='" + adminPassWord + '\'' +
                '}';
    }
}
