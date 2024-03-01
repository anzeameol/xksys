package xkpackage.servlet;

import com.mysql.cj.util.StringUtils;
import xkpackage.dao.DaoFactory;
import xkpackage.entities.admin;
import xkpackage.entities.student;
import xkpackage.entities.teacher;
import xkpackage.utils.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        if(StringUtils.isNullOrEmpty(type)) {
            req.setAttribute("error","登录类型不能为空");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        else if(StringUtils.isNullOrEmpty(userName) || StringUtils.isNullOrEmpty(password)) {
            req.setAttribute("error","用户名或密码不能为空");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        else {
            password = MD5.encrypt(password);
            HttpSession session = req.getSession();
            if("0".equals(type)){
                try {
                    student s = DaoFactory.getInstance().getStuDao().login(userName,password);
                    if(s != null) {
                        session.setAttribute("userName",s.getStuName());
                        session.setAttribute("userID",s.getStuID());
                        session.setAttribute("type",type);
                        resp.sendRedirect("index.jsp");
                    }
                    else{
                        req.setAttribute("error","用户名或密码错误");
                        req.getRequestDispatcher("login.jsp").forward(req,resp);
                    }
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if("1".equals(type)){
                try {
                    teacher t = DaoFactory.getInstance().getTeacherDao().login(userName,password);
                    if(t != null) {
                        session.setAttribute("userName",t.gettName());
                        session.setAttribute("userID",t.gettID());
                        session.setAttribute("type",type);
                        resp.sendRedirect("index.jsp");
                    }
                    else{
                        req.setAttribute("error","用户名或密码错误");
                        req.getRequestDispatcher("login.jsp").forward(req,resp);
                    }
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                try {
                    admin a = DaoFactory.getInstance().getAdminDao().login(userName,password);
                    if(a != null) {
                        session.setAttribute("userName",a.getAdminName());
                        session.setAttribute("userID",a.getAdminID());
                        session.setAttribute("type",type);
                        resp.sendRedirect("index.jsp");
                    }
                    else{
                        req.setAttribute("error","用户名或密码错误");
                        req.getRequestDispatcher("login.jsp").forward(req,resp);
                    }
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
