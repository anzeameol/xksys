package xkpackage.servlet;

import xkpackage.dao.DaoFactory;
import xkpackage.entities.admin;
import xkpackage.entities.admin;
import xkpackage.entities.admin;
import xkpackage.utils.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("info".equals(method))
        {
            Integer adminID = Integer.parseInt(req.getParameter("adminID"));
            info(req,resp,adminID);
        } else if ("passwordedit".equals(method)) {
            Integer adminID = Integer.parseInt(req.getParameter("adminID"));
            try {
                admin temp = DaoFactory.getInstance().getAdminDao().findByID(adminID);
                req.setAttribute("admin",temp);
                req.getRequestDispatcher("page/admin/infoUpdate.jsp").forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("passwordsubmit".equals(method)) {
            Integer adminID = Integer.parseInt(req.getParameter("adminID"));
            String adminPwd = MD5.encrypt(req.getParameter("adminPwd"));
            try {
                DaoFactory.getInstance().getAdminDao().updateBySelf(adminID,adminPwd);
                info(req,resp,adminID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void info(HttpServletRequest req, HttpServletResponse resp,Integer adminID) throws ServletException, IOException {
        try {
            admin temp = DaoFactory.getInstance().getAdminDao().findByID(adminID);
            req.setAttribute("adminName", temp.getAdminName());
            req.setAttribute("adminAcc", temp.getAdminAcc());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("adminID", adminID);
        req.getRequestDispatcher("page/admin/adminInfo.jsp").forward(req, resp);
    }
}
