package com.servlet;

import com.dao.VClueListDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pojo.VClueListPojo;
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
public class AdminLoadClueList extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Parser.parseInt(request.getParameter("pageNumber"));
        int pageSize = Parser.parseInt(request.getParameter("pageSize"));

        String keyword = request.getParameter("keyword");
        String clueType = request.getParameter("JBKJXSLY_LYFS");
        int clueStatus = Parser.parseInt(request.getParameter("JBKJXSLY_CLZT"));
        String acceptDateStart = request.getParameter("JBKJXSLY_CBRCLRQ_Start");
        String acceptDateEnd = request.getParameter("JBKJXSLY_CBRCLRQ_End");
        //String clueZone = request.getParameter("JBKJXSLY_AFDQ");

        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String sWhere = "where JBKJXSLY_CLZT != '-1'";

        if (keyword != null && keyword.length() > 0)
            sWhere += " and clueContent like '%" + keyword + "%'";
        if (clueType != null && clueType.length() > 0)
            sWhere += "and JBKJXSLY_LYFSDM ='" + clueType + "'";
        if (clueStatus > 0)
            sWhere += " and JBKJXSLY_CLZT='" + clueStatus + "'";
        if (acceptDateStart != null && acceptDateStart.length() > 0)
            sWhere += " and JBKJXSLY_CBRCLRQ > '" + acceptDateStart + "'";
        if (acceptDateEnd != null && acceptDateEnd.length() > 0)
            sWhere += " and JBKJXSLY_CBRCLRQ < '" + acceptDateEnd + "'";

        VClueListDao dao = new VClueListDao();
        int totalCount = dao.getTotalRecordCount(sWhere);
        List<VClueListPojo> list = dao.getAll(pageNumber, pageSize, sWhere);
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
