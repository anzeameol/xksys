package xkpackage.entities;

public class teacher {
    private Integer tID;
    private String tName;
    private String tNum;
    private String tPwd;

    public Integer gettID() {
        return tID;
    }

    public String gettName() {
        return tName;
    }

    public String gettNum() {
        return tNum;
    }

    public String gettPwd() {
        return tPwd;
    }

    public void settID(Integer tID) {
        this.tID = tID;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public void settNum(String tNum) {
        this.tNum = tNum;
    }

    public void settPwd(String tPwd) {
        this.tPwd = tPwd;
    }
}
