package xkpackage.dao;

import xkpackage.domain.Page;
import xkpackage.domain.User;
import xkpackage.entities.course;
import xkpackage.entities.student;
import xkpackage.entities.teacher;
import xkpackage.entities.tc;

public class DaoFactory {
    private static DaoFactory factory = new DaoFactory();
    private static adminDao admDao = new adminDao();
    private static teacherDao tDao = new teacherDao();
    private static stuDao sDao = new stuDao();
    private static courseDao cDao = new courseDao();
    private static takeDao takeDao = new takeDao();
    private static Page<student> stuPage = new Page<>();
    private static Page<teacher> tPage = new Page<>();
    private static Page<course> cPage = new Page<>();
    private static Page<tc> tcPage = new Page<>();
    private static User<student> studentUser = new User<>();
    private DaoFactory() {}
    public static DaoFactory getInstance() {
        return factory;
    }
    public adminDao getAdminDao() {
        return admDao;
    }
    public teacherDao getTeacherDao() {
        return tDao;
    }
    public stuDao getStuDao() {
        return sDao;
    }
    public courseDao getCourseDao() {return cDao;}
    public takeDao getTakeDao() {return takeDao;}
    public Page<student> getStuPage()
    {
        return stuPage;
    }
    public Page<teacher> gettPage() {
        return tPage;
    }
    public Page<course> getcPage() {
        return cPage;
    }
    public Page<tc> getTcPage() {return tcPage;}
    public User<student> getStudentUser() {return studentUser;}
}
