package xkpackage.entities;

public class admin {
    private Integer adminID;
    private String adminName;
    private String adminAcc;
    private String adminPwd;

    public Integer getAdminID() {
        return adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminAcc() {
        return adminAcc;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminAcc(String adminAcc) {
        this.adminAcc = adminAcc;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }
}
