package com.servlet;

import com.dao.RoleinfoDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pojo.RoleinfoPojo;
import com.utils.DevLog;
import com.utils.Parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class AdminLoadRoleList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Parser.parseInt(request.getParameter("pageNumber"));
        int pageSize = Parser.parseInt(request.getParameter("pageSize"));

        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String sWhere = "where newType = 1";

        RoleinfoDao dao = new RoleinfoDao();
        int totalCount = dao.getTotalRecordCount(sWhere);
        List<RoleinfoPojo> list = dao.getAll(pageNumber, pageSize, sWhere);
        dao.closeAll();


        //// Result data transfer
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        map.put("total", totalCount);
        map.put("rows", list);
        String json = gson.toJson(map);
        DevLog.write(json);
        out.println(json);
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
