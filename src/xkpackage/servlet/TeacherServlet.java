package xkpackage.servlet;

import xkpackage.dao.DaoFactory;
import xkpackage.domain.Page;
import xkpackage.entities.*;
import xkpackage.entities.teacher;
import xkpackage.utils.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("list".equals(method)) {
            List<teacher> list;
            Page<teacher> tPage = DaoFactory.getInstance().gettPage();
            if(tPage.limit == null)
            {
                tPage.limit = new teacher();
            }
            try {
                list = DaoFactory.getInstance().getTeacherDao().list(tPage.limit);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String type = req.getParameter("type");
            if("init".equals(type))
            {
                tPage.init(list,10);
            }
            else if("change".equals(type))
            {
                tPage.setList(list);
            }
            req.setAttribute("list", tPage.page);
            req.setAttribute("curPage",tPage.curPage+1);
            req.setAttribute("pageSize",tPage.pageSize);
            req.setAttribute("totPage",tPage.totPage);
            req.setAttribute("totRecord",tPage.totRecord);
            try {
                req.getRequestDispatcher("page/teacher/list.jsp").forward(req,resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if("add".equals(method))
        {
            String tNum = req.getParameter("tNum");
            String tName = req.getParameter("tName");
            String tPwd = MD5.encrypt(req.getParameter("tPwd"));
            teacher t = new teacher();
            t.settNum(tNum);
            t.settName(tName);
            t.settPwd(tPwd);
            try {
                DaoFactory.getInstance().getTeacherDao().add(t);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect("teacher?method=list&type=change");
        }
        else if("edit".equals(method))
        {
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            try {
                teacher t = DaoFactory.getInstance().getTeacherDao().findByID(tID);
                req.setAttribute("teacher",t);
                req.getRequestDispatcher("page/teacher/update.jsp").forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if("editsubmit".equals(method))
        {
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            String tNum = req.getParameter("tNum");
            String tName = req.getParameter("tName");
            teacher t = new teacher();
            t.settNum(tNum);
            t.settName(tName);
            t.settID(tID);
            try {
                DaoFactory.getInstance().getTeacherDao().updateByAdmin(t);
                resp.sendRedirect("teacher?method=list&type=change");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if("delete".equals(method))
        {
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            try {
                DaoFactory.getInstance().getTeacherDao().delete(tID);
                resp.sendRedirect("teacher?method=list&type=change");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if("nextpage".equals(method))
        {
            Page<teacher> tPage = DaoFactory.getInstance().gettPage();
            tPage.setNextPage();
            req.setAttribute("list", tPage.page);
            req.setAttribute("curPage",tPage.curPage+1);
            req.setAttribute("pageSize",tPage.pageSize);
            req.setAttribute("totPage",tPage.totPage);
            req.setAttribute("totRecord",tPage.totRecord);
            req.getRequestDispatcher("page/teacher/list.jsp").forward(req,resp);
        }
        else if("lastpage".equals(method))
        {
            Page<teacher> tPage = DaoFactory.getInstance().gettPage();
            tPage.setLastPage();
            req.setAttribute("list", tPage.page);
            req.setAttribute("curPage",tPage.curPage+1);
            req.setAttribute("pageSize",tPage.pageSize);
            req.setAttribute("totPage",tPage.totPage);
            req.setAttribute("totRecord",tPage.totRecord);
            req.getRequestDispatcher("page/teacher/list.jsp").forward(req,resp);
        }
        else if("firstpage".equals(method))
        {
            Page<teacher> tPage = DaoFactory.getInstance().gettPage();
            tPage.setFirstPage();
            req.setAttribute("list", tPage.page);
            req.setAttribute("curPage",tPage.curPage+1);
            req.setAttribute("pageSize",tPage.pageSize);
            req.setAttribute("totPage",tPage.totPage);
            req.setAttribute("totRecord",tPage.totRecord);
            req.getRequestDispatcher("page/teacher/list.jsp").forward(req,resp);
        }
        else if ("search".equals(method))
        {
            Integer tID;
            if(!Objects.equals(req.getParameter("tID"), ""))
            {
                tID = Integer.parseInt(req.getParameter("tID"));
            }
            else {
                tID = null;
            }
            String tNum = req.getParameter("tNum");
            String tName = req.getParameter("tName");
            Page<teacher> tPage = DaoFactory.getInstance().gettPage();
            tPage.limit.settID(tID);
            tPage.limit.settNum(tNum);
            tPage.limit.settName(tName);
            resp.sendRedirect("teacher?method=list&type=change");
        } 
        else if ("grade".equals(method)) {
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            grade(req,resp,tID);
        } else if ("gradeedit".equals(method)) {
            Integer takeID = Integer.parseInt(req.getParameter("takeID"));
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            try {
                take temp = DaoFactory.getInstance().getTakeDao().findByID(takeID);
                req.setAttribute("take",temp);
                req.setAttribute("tID",tID);
                req.getRequestDispatcher("page/take/gradeUpdate.jsp").forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if("gradesubmit".equals(method))
        {
            Integer takeID = Integer.parseInt(req.getParameter("takeID"));
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            double grade = Double.parseDouble(req.getParameter("grade"));
            take temp = new take();
            temp.setTakeID(takeID);
            temp.setGrade(grade);
            try {
                DaoFactory.getInstance().getTakeDao().update(temp);
                grade(req,resp,tID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if("info".equals(method))
        {
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            info(req,resp,tID);
        } else if ("passwordedit".equals(method)) {
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            try {
                teacher temp = DaoFactory.getInstance().getTeacherDao().findByID(tID);
                req.setAttribute("teacher",temp);
                req.getRequestDispatcher("page/teacher/infoUpdate.jsp").forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("passwordsubmit".equals(method)) {
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            String tPwd = MD5.encrypt(req.getParameter("tPwd"));
            try {
                DaoFactory.getInstance().getTeacherDao().updateBySelf(tID,tPwd);
                info(req,resp,tID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void info(HttpServletRequest req,HttpServletResponse resp,Integer tID) throws ServletException, IOException {
        try {
            teacher temp = DaoFactory.getInstance().getTeacherDao().findByID(tID);
            req.setAttribute("tName", temp.gettName());
            req.setAttribute("tNum", temp.gettNum());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("tID", tID);
        req.getRequestDispatcher("page/teacher/teacherInfo.jsp").forward(req, resp);
    }
    public void grade(HttpServletRequest req, HttpServletResponse resp,Integer tID) throws ServletException, IOException {
        try {
            List<sc> list = DaoFactory.getInstance().getTeacherDao().grade(tID);
            req.setAttribute("list", list);
            req.getRequestDispatcher("page/teacher/grade.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

