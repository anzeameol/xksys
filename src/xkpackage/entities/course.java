package xkpackage.entities;

public class course {
    private Integer cID;
    private String cName;
    private Integer tID;

    public Integer getcID() {
        return cID;
    }

    public String getcName() {
        return cName;
    }

    public Integer gettID() {
        return tID;
    }

    public void setcID(Integer cID) {
        this.cID = cID;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void settID(Integer tID) {
        this.tID = tID;
    }
}
