package xkpackage.servlet;

import xkpackage.dao.DaoFactory;
import xkpackage.domain.Page;
import xkpackage.entities.student;
import xkpackage.entities.take;
import xkpackage.entities.tc;
import xkpackage.entities.sc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet("/take")
public class TakeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("list".equals(method))
        {
            try {
                list(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if("nextpage".equals(method))
        {
            Page<tc> tcPage = DaoFactory.getInstance().getTcPage();
            tcPage.setNextPage();
            req.setAttribute("list", tcPage.page);
            req.setAttribute("curPage",tcPage.curPage+1);
            req.setAttribute("pageSize",tcPage.pageSize);
            req.setAttribute("totPage",tcPage.totPage);
            req.setAttribute("totRecord",tcPage.totRecord);
            req.getRequestDispatcher("page/take/list.jsp").forward(req,resp);
        }
        else if("lastpage".equals(method))
        {
            Page<tc> tcPage = DaoFactory.getInstance().getTcPage();
            tcPage.setLastPage();
            req.setAttribute("list", tcPage.page);
            req.setAttribute("curPage",tcPage.curPage+1);
            req.setAttribute("pageSize",tcPage.pageSize);
            req.setAttribute("totPage",tcPage.totPage);
            req.setAttribute("totRecord",tcPage.totRecord);
            req.getRequestDispatcher("page/take/list.jsp").forward(req,resp);
        }
        else if("firstpage".equals(method))
        {
            Page<tc> tcPage = DaoFactory.getInstance().getTcPage();
            tcPage.setFirstPage();
            req.setAttribute("list", tcPage.page);
            req.setAttribute("curPage",tcPage.curPage+1);
            req.setAttribute("pageSize",tcPage.pageSize);
            req.setAttribute("totPage",tcPage.totPage);
            req.setAttribute("totRecord",tcPage.totRecord);
            req.getRequestDispatcher("page/take/list.jsp").forward(req,resp);
        }
        else if ("search".equals(method))
        {
            Integer cID;
            if(!Objects.equals(req.getParameter("cID"), ""))
            {
                cID = Integer.parseInt(req.getParameter("cID"));
            }
            else {
                cID = null;
            }
            String tName = req.getParameter("tName");
            String cName = req.getParameter("cName");
            Page<tc> tcPage = DaoFactory.getInstance().getTcPage();
            tcPage.limit.setcID(cID);
            tcPage.limit.setcName(cName);
            tcPage.limit.settName(tName);
            resp.sendRedirect("take?method=list&type=change");
        } else if ("select".equals(method)) {
            Integer stuID = DaoFactory.getInstance().getStudentUser().user.getStuID();
            Integer cID = Integer.parseInt(req.getParameter("cID"));
            take temp = new take();
            temp.setcID(cID);
            temp.setStuID(stuID);
            try {
                DaoFactory.getInstance().getTakeDao().add(temp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect("take?method=list&type=change");
        }
        else if("selected".equals(method))
        {
            try {
                selected(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("untake".equals(method)) {
            try {
                untake(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("history".equals(method)) {
            try {
                history(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void list(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Page<tc> tcPage = DaoFactory.getInstance().getTcPage();
        if(tcPage.limit == null)
        {
            tcPage.limit = new tc();
        }
        String type = req.getParameter("type");
        if("init".equals(type)) {
            Integer stuID = Integer.parseInt(req.getParameter("stuID"));
            DaoFactory.getInstance().getStudentUser().user = new student();
            DaoFactory.getInstance().getStudentUser().user.setStuID(stuID);
            List<tc> list = DaoFactory.getInstance().getCourseDao().listTc(tcPage.limit,stuID);
            tcPage.init(list, 10);
        }
        else if ("change".equals(type)) {
            List<tc> list = DaoFactory.getInstance().getCourseDao().listTc(tcPage.limit,DaoFactory.getInstance().getStudentUser().user.getStuID());
            tcPage.setList(list);
        }
        req.setAttribute("list", tcPage.page);
        req.setAttribute("curPage",tcPage.curPage+1);
        req.setAttribute("pageSize",tcPage.pageSize);
        req.setAttribute("totPage",tcPage.totPage);
        req.setAttribute("totRecord",tcPage.totRecord);
        req.getRequestDispatcher("page/take/list.jsp").forward(req,resp);
    }
    public void selected(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        Integer stuID;
        if(DaoFactory.getInstance().getStudentUser().user.getStuID() == null)
        {
            stuID = Integer.parseInt(req.getParameter("stuID"));
            DaoFactory.getInstance().getStudentUser().user.setStuID(stuID);
        }
        else {
            stuID = DaoFactory.getInstance().getStudentUser().user.getStuID();
        }
        List<sc> list = DaoFactory.getInstance().getTakeDao().listScSelected(stuID);
        req.setAttribute("list", list);
        req.getRequestDispatcher("page/take/selected.jsp").forward(req,resp);
    }
    public void untake(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        Integer stuID = DaoFactory.getInstance().getStudentUser().user.getStuID();
        Integer cID = Integer.parseInt(req.getParameter("cID"));
        DaoFactory.getInstance().getTakeDao().delete(stuID,cID);
        resp.sendRedirect("take?method=selected");
    }
    public void history(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        Integer stuID;
        if(DaoFactory.getInstance().getStudentUser().user.getStuID() == null)
        {
            stuID = Integer.parseInt(req.getParameter("stuID"));
            DaoFactory.getInstance().getStudentUser().user.setStuID(stuID);
        }
        else {
            stuID = DaoFactory.getInstance().getStudentUser().user.getStuID();
        }
        List<sc> list = DaoFactory.getInstance().getTakeDao().listScHistory(stuID);
        req.setAttribute("list", list);
        req.getRequestDispatcher("page/take/history.jsp").forward(req,resp);
    }
}
