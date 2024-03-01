package xkpackage.servlet;

import xkpackage.dao.DaoFactory;
import xkpackage.domain.Page;
import xkpackage.entities.course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("list".equals(method)) {
            List<course> list;
            Page<course> cPage = DaoFactory.getInstance().getcPage();
            if(cPage.limit == null)
            {
                cPage.limit = new course();
            }
            try {
                list = DaoFactory.getInstance().getCourseDao().list(cPage.limit);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String type = req.getParameter("type");
            if("init".equals(type))
            {
                cPage.init(list,10);
            }
            else if("change".equals(type))
            {
                cPage.setList(list);
            }
            req.setAttribute("list", cPage.page);
            req.setAttribute("curPage",cPage.curPage+1);
            req.setAttribute("pageSize",cPage.pageSize);
            req.setAttribute("totPage",cPage.totPage);
            req.setAttribute("totRecord",cPage.totRecord);
            try {
                req.getRequestDispatcher("page/course/list.jsp").forward(req,resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if("add".equals(method))
        {
            String cName = req.getParameter("cName");
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            course c = new course();
            c.setcName(cName);
            c.settID(tID);
            try {
                DaoFactory.getInstance().getCourseDao().add(c);
            } catch (SQLException e) {
                if(e.getErrorCode() == 1452)
                {
                    req.setAttribute("error","此教师不存在，请重新输入");
                    req.getRequestDispatcher("page/course/add.jsp").forward(req,resp);
                }
                else {
                    throw new RuntimeException(e);
                }
            }
            resp.sendRedirect("course?method=list&type=change");
        }
        else if("edit".equals(method))
        {
            Integer cID = Integer.parseInt(req.getParameter("cID"));
            try {
                course c = DaoFactory.getInstance().getCourseDao().findByID(cID);
                req.setAttribute("course",c);
                req.getRequestDispatcher("page/course/update.jsp").forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if("editsubmit".equals(method))
        {
            Integer cID = Integer.parseInt(req.getParameter("cID"));
            Integer tID = Integer.parseInt(req.getParameter("tID"));
            String cName = req.getParameter("cName");
            course c = new course();
            c.settID(tID);
            c.setcName(cName);
            c.setcID(cID);
            try {
                DaoFactory.getInstance().getCourseDao().updateByAdmin(c);
                resp.sendRedirect("course?method=list&type=change");
            } catch (SQLException e) {
                if(e.getErrorCode() == 1452)   //破坏外键约束异常
                {
                    req.setAttribute("error","此教师不存在，请重新输入");
                    req.getRequestDispatcher("page/course/update.jsp").forward(req,resp);
                }
                else {
                    System.out.println(e.getErrorCode());
                    throw new RuntimeException(e);
                }
            }
        }
        else if("delete".equals(method))
        {
            Integer cID = Integer.parseInt(req.getParameter("cID"));
            try {
                DaoFactory.getInstance().getCourseDao().delete(cID);
                resp.sendRedirect("course?method=list&type=change");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if("nextpage".equals(method))
        {
            Page<course> cPage = DaoFactory.getInstance().getcPage();
            cPage.setNextPage();
            req.setAttribute("list", cPage.page);
            req.setAttribute("curPage",cPage.curPage+1);
            req.setAttribute("pageSize",cPage.pageSize);
            req.setAttribute("totPage",cPage.totPage);
            req.setAttribute("totRecord",cPage.totRecord);
            req.getRequestDispatcher("page/course/list.jsp").forward(req,resp);
        }
        else if("lastpage".equals(method))
        {
            Page<course> cPage = DaoFactory.getInstance().getcPage();
            cPage.setLastPage();
            req.setAttribute("list", cPage.page);
            req.setAttribute("curPage",cPage.curPage+1);
            req.setAttribute("pageSize",cPage.pageSize);
            req.setAttribute("totPage",cPage.totPage);
            req.setAttribute("totRecord",cPage.totRecord);
            req.getRequestDispatcher("page/course/list.jsp").forward(req,resp);
        }
        else if("firstpage".equals(method))
        {
            Page<course> cPage = DaoFactory.getInstance().getcPage();
            cPage.setFirstPage();
            req.setAttribute("list", cPage.page);
            req.setAttribute("curPage",cPage.curPage+1);
            req.setAttribute("pageSize",cPage.pageSize);
            req.setAttribute("totPage",cPage.totPage);
            req.setAttribute("totRecord",cPage.totRecord);
            req.getRequestDispatcher("page/course/list.jsp").forward(req,resp);
        }
        else if ("search".equals(method))
        {
            Integer cID;
            Integer tID;
            if(!Objects.equals(req.getParameter("cID"), ""))
            {
                cID = Integer.parseInt(req.getParameter("cID"));
            }
            else {
                cID = null;
            }
            if(!Objects.equals(req.getParameter("tID"), ""))
            {
                tID = Integer.parseInt(req.getParameter("tID"));
            }
            else {
                tID = null;
            }
            String cName = req.getParameter("cName");
            Page<course> cPage = DaoFactory.getInstance().getcPage();
            cPage.limit.setcID(cID);
            cPage.limit.settID(tID);
            cPage.limit.setcName(cName);
            resp.sendRedirect("course?method=list&type=change");
        }
    }
}
