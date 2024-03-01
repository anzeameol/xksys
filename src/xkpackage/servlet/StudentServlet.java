package xkpackage.servlet;

import xkpackage.dao.DaoFactory;
import xkpackage.domain.Page;
import xkpackage.entities.student;
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

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("list".equals(method)) {
            List<student> list;
            Page<student> stuPage = DaoFactory.getInstance().getStuPage();
            if(stuPage.limit == null)
            {
                stuPage.limit = new student();
            }
            try {
                list = DaoFactory.getInstance().getStuDao().list(stuPage.limit);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String type = req.getParameter("type");
            if("init".equals(type))
            {
                stuPage.init(list,10);
            }
            else if("change".equals(type))
            {
                stuPage.setList(list);
            }
            req.setAttribute("list", stuPage.page);
            req.setAttribute("curPage",stuPage.curPage+1);
            req.setAttribute("pageSize",stuPage.pageSize);
            req.setAttribute("totPage",stuPage.totPage);
            req.setAttribute("totRecord",stuPage.totRecord);
            try {
                req.getRequestDispatcher("page/student/list.jsp").forward(req,resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if("add".equals(method))
        {
            String stuNum = req.getParameter("stuNum");
            String stuName = req.getParameter("stuName");
            String stuPwd = MD5.encrypt(req.getParameter("stuPwd"));
            student s = new student();
            s.setStuNum(stuNum);
            s.setStuName(stuName);
            s.setStuPwd(stuPwd);
            try {
                DaoFactory.getInstance().getStuDao().add(s);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect("student?method=list&type=change");
        }
        else if("edit".equals(method))
        {
            Integer stuID = Integer.parseInt(req.getParameter("stuID"));
            try {
                student s = DaoFactory.getInstance().getStuDao().findByID(stuID);
                req.setAttribute("student",s);
                req.getRequestDispatcher("page/student/update.jsp").forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if("editsubmit".equals(method))
        {
            Integer stuID = Integer.parseInt(req.getParameter("stuID"));
            String stuNum = req.getParameter("stuNum");
            String stuName = req.getParameter("stuName");
            student s = new student();
            s.setStuNum(stuNum);
            s.setStuName(stuName);
            s.setStuID(stuID);
            try {
                DaoFactory.getInstance().getStuDao().updateByAdmin(s);
                resp.sendRedirect("student?method=list&type=change");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if("delete".equals(method))
        {
            Integer stuID = Integer.parseInt(req.getParameter("stuID"));
            try {
                DaoFactory.getInstance().getStuDao().delete(stuID);
                resp.sendRedirect("student?method=list&type=change");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if("nextpage".equals(method))
        {
            Page<student> stuPage = DaoFactory.getInstance().getStuPage();
            stuPage.setNextPage();
            req.setAttribute("list", stuPage.page);
            req.setAttribute("curPage",stuPage.curPage+1);
            req.setAttribute("pageSize",stuPage.pageSize);
            req.setAttribute("totPage",stuPage.totPage);
            req.setAttribute("totRecord",stuPage.totRecord);
            req.getRequestDispatcher("page/student/list.jsp").forward(req,resp);
        }
        else if("lastpage".equals(method))
        {
            Page<student> stuPage = DaoFactory.getInstance().getStuPage();
            stuPage.setLastPage();
            req.setAttribute("list", stuPage.page);
            req.setAttribute("curPage",stuPage.curPage+1);
            req.setAttribute("pageSize",stuPage.pageSize);
            req.setAttribute("totPage",stuPage.totPage);
            req.setAttribute("totRecord",stuPage.totRecord);
            req.getRequestDispatcher("page/student/list.jsp").forward(req,resp);
        }
        else if("firstpage".equals(method))
        {
            Page<student> stuPage = DaoFactory.getInstance().getStuPage();
            stuPage.setFirstPage();
            req.setAttribute("list", stuPage.page);
            req.setAttribute("curPage",stuPage.curPage+1);
            req.setAttribute("pageSize",stuPage.pageSize);
            req.setAttribute("totPage",stuPage.totPage);
            req.setAttribute("totRecord",stuPage.totRecord);
            req.getRequestDispatcher("page/student/list.jsp").forward(req,resp);
        }
        else if ("search".equals(method))
        {
            Integer stuID;
            if(!Objects.equals(req.getParameter("stuID"), ""))
            {
                stuID = Integer.parseInt(req.getParameter("stuID"));
            }
            else {
                stuID = null;
            }
            String stuNum = req.getParameter("stuNum");
            String stuName = req.getParameter("stuName");
            Page<student> stuPage = DaoFactory.getInstance().getStuPage();
            stuPage.limit.setStuID(stuID);
            stuPage.limit.setStuNum(stuNum);
            stuPage.limit.setStuName(stuName);
            resp.sendRedirect("student?method=list&type=change");
        }
        else if("info".equals(method))
        {
            Integer stuID = Integer.parseInt(req.getParameter("stuID"));
            info(req,resp,stuID);
        } else if ("passwordedit".equals(method)) {
            Integer stuID = Integer.parseInt(req.getParameter("stuID"));
            try {
                student temp = DaoFactory.getInstance().getStuDao().findByID(stuID);
                req.setAttribute("student",temp);
                req.getRequestDispatcher("page/student/infoUpdate.jsp").forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("passwordsubmit".equals(method)) {
            Integer stuID = Integer.parseInt(req.getParameter("stuID"));
            String stuPwd = MD5.encrypt(req.getParameter("stuPwd"));
            try {
                DaoFactory.getInstance().getStuDao().updateBySelf(stuID,stuPwd);
                info(req,resp,stuID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void info(HttpServletRequest req, HttpServletResponse resp,Integer stuID) throws ServletException, IOException {
        try {
            student temp = DaoFactory.getInstance().getStuDao().findByID(stuID);
            req.setAttribute("stuName", temp.getStuName());
            req.setAttribute("stuNum", temp.getStuNum());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("stuID", stuID);
        req.getRequestDispatcher("page/student/stuInfo.jsp").forward(req, resp);
    }
}
