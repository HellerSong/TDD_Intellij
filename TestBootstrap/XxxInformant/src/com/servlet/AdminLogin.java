package com.servlet;

import com.dao.OrgmemberinfoDao;
import com.google.gson.Gson;
import com.pojo.OrgmemberinfoPojo;
import com.utils.DevLog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class AdminLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String loginId = request.getParameter("loginId");
        String pwd = request.getParameter("pwd");

        Map<String, Object> map = new HashMap<String, Object>();
        String message;

        if (loginId == null || loginId.length() <= 0) {
            message = "用户名异常！";
        }
        if (pwd == null || pwd.length() <= 0) {
            message = "密码异常！";
        }

        OrgmemberinfoDao dao = new OrgmemberinfoDao();
        List<OrgmemberinfoPojo> list = dao.getAll("where LoginID='" + loginId + "'");
        if (list.size() <= 0) {
            message = "用户名不存在，请重新输入！";
        } else {
            list = dao.getAll("where LoginID='" + loginId + "' and Passwords='" + pwd + "'");
            if (list.size() <= 0) {
                message = "密码不正确！";
            } else {
                session.setAttribute("currentAdmin", list.get(0).getLoginID());
                message = "登录成功！";
                map.put("rows", list.get(0));
            }
        }
        dao.closeAll();


        //// Result data transfer
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        map.put("status", message);
        String json = new Gson().toJson(map);
        DevLog.write(json);
        out.println(json);
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
