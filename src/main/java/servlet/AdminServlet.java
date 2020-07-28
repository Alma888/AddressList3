package servlet;

import bean.PersonInfo;
import biz.AdminInfoBiz;
import biz.impl.AdminInfoBizImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//servlet端，用于控制，判断
@WebServlet("/login.servlet")
public class AdminServlet extends HttpServlet {
    private AdminInfoBiz ab = new AdminInfoBizImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码集
        doPost(req, resp);//让任何访问请求都在dopost方法中操作
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码集
        String type = req.getParameter("type");
        if ("login".equals(type)){
            judgeLogin(req,resp);
        }else if("searchallperson".equals(type)){
            searchAllPerson(req, resp);
        }else if ("removeperson".equals(type)){
            removeOnePerson(req,resp);
        }else if ("changeperson".equals(type)){
            changeOnePerson(req,resp);
        }else if ("changesomeperson".equals(type)){
            changeSomePerson(req,resp);
        }else if("order".equals(type)){
            sortPerson(req,resp);
        }
    }

    public void judgeLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码集
        String adminName = req.getParameter("adminname");//得到前端送来的请求参数值
        String adminPassWord = req.getParameter("adminpassword");//同上
        boolean judge = ab.judgeLogin(adminName,adminPassWord);//判断是否登陆成功！
        if (judge==true){//如果登录成功，跳转到成功页面进行下一步操作
            req.getSession().setAttribute("ADMIN_ID",ab.getAdminIdByAdminName(adminName));//将adminid设置到session容器中
            req.getRequestDispatcher("success.html").forward(req,resp);
        }else {//不成功，则跳转到本页面，重新进行登录
            resp.sendRedirect("login.html");
        }
    }

    public void searchAllPerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码集
        HttpSession session = req.getSession();
        List<PersonInfo> list = ab.findAllPerson((Integer) session.getAttribute("ADMIN_ID"));//得到此admin所有的通讯录人员
        session.setAttribute("ALL_PERSON",list);
        resp.sendRedirect("look.jsp");
    }

    public void removeOnePerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码集
        Integer personId = Integer.parseInt(req.getParameter("deleteId"));//得到要删除的对象id
        Integer judge = ab.removeOnePerson(personId);
        HttpSession session = req.getSession();
        List<PersonInfo> list = ab.findAllPerson((Integer) session.getAttribute("ADMIN_ID"));//得到此admin所有的通讯录人员
        session.setAttribute("ALL_PERSON",list);
        if (judge==1){//说明删除成功
            resp.getWriter().print(1);
        }else {//说明删除不成功
            resp.getWriter().print(-1);
        }
    }

    public void changeOnePerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码集
        Integer personId = Integer.parseInt(req.getParameter("changeId"));//得到要修改的对象id
        req.getSession().setAttribute("CHANGE_ID",personId);//存入session容器中
    }

    public void changeSomePerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码集
        Integer personId = (Integer) req.getSession().getAttribute("CHANGE_ID");//得到session容器中预先存取的personid值，即对应的要被修改用户
        if (personId!=null){//执行修改操作
            PersonInfo pi = new PersonInfo();
            pi.setPersonId(personId);
            pi.setPersonName(req.getParameter("personname"));
            pi.setPersonSex(req.getParameter("personsex"));
            pi.setPersonPhone(req.getParameter("personphone"));
            pi.setPersonPhone(req.getParameter("personphone"));
            pi.setPersonAddress(req.getParameter("personaddress"));
            pi.setPersonEmail(req.getParameter("personemail"));
            pi.setHomePhone(Integer.parseInt(req.getParameter("homephone")));
            pi.setPostCode(Integer.parseInt(req.getParameter("postcode")));
            Integer judge = ab.changeSomePerson(pi);
            if (judge == 1){//修改成功，修改此时的查询出来的所有用户，返回look.jsp
                HttpSession session = req.getSession();
                List<PersonInfo> list = ab.findAllPerson((Integer) session.getAttribute("ADMIN_ID"));//得到此admin所有的通讯录人员
                session.setAttribute("ALL_PERSON",list);
                req.getRequestDispatcher("look.jsp").forward(req,resp);
            }
        }else{//没有获取到id值，则让其直接返回到修改页面
            resp.sendRedirect("change.html");
        }
    }

    public void sortPerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置编码集
        Object obj = req.getParameter("choose");
        Integer personId = (Integer) req.getSession().getAttribute("ADMIN_ID");
        //进行排序
        List<PersonInfo> list = ab.sortPerson(personId,obj);
        //将排序后的元素写回session容器中，用于展示
        req.getSession().setAttribute("ALL_PERSON",list);
        req.getRequestDispatcher("look.jsp").forward(req,resp);
    }
}
