package servlet;

import bean.PersonInfo;
import biz.AdminInfoBiz;
import biz.impl.AdminInfoBizImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add.servlet")
public class AddServlet extends HttpServlet {
    private AdminInfoBiz ab = new AdminInfoBizImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String type = req.getParameter("type");
        if ("addsomeperson".equals(type)){
            addSomePerson(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        doPost(req, resp);
    }

    public void addSomePerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        PersonInfo pi = new PersonInfo();
        pi.setPersonName(req.getParameter("personname"));
        pi.setPersonSex(req.getParameter("personsex"));
        pi.setPersonPhone(req.getParameter("personphone"));
        pi.setPersonPhone(req.getParameter("personphone"));
        pi.setPersonAddress(req.getParameter("personaddress"));
        pi.setPersonEmail(req.getParameter("personemail"));
        pi.setHomePhone(Integer.parseInt(req.getParameter("homephone")));
        pi.setPostCode(Integer.parseInt(req.getParameter("postcode")));
        pi.setAdminId(Integer.parseInt(req.getParameter("adminid")));
        Integer judge = ab.addSomePerson(pi);
        if (judge==1){//添加成功
            resp.sendRedirect("successful.html");
        }else{//添加失败
            resp.sendRedirect("false.html");
        }
    }
}
