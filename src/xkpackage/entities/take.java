package xkpackage.entities;

public class take {
    private Integer takeID;
    private Integer stuID;
    private Integer cID;
    private Double grade;

    public Integer getTakeID() {
        return takeID;
    }

    public Integer getStuID() {
        return stuID;
    }

    public Integer getcID() {
        return cID;
    }

    public Double getGrade() {
        return grade;
    }

    public void setTakeID(Integer takeID) {
        this.takeID = takeID;
    }

    public void setStuID(Integer stuID) {
        this.stuID = stuID;
    }

    public void setcID(Integer cID) {
        this.cID = cID;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
