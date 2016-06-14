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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class AdminLoadClueList extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int pageNumber = Parser.parseInt(request.getParameter("pageNumber"));
        int pageSize = Parser.parseInt(request.getParameter("pageSize"));

//        String acceptDateStart = request.getParameter("acceptDateStart");
//        String acceptDateEnd = request.getParameter("acceptDateEnd");
//        String caseZone = request.getParameter("caseZone");
//        int informantKindValue = Parser.parseInt(request.getParameter("informantKindValue"));
//        int status = Parser.parseInt(request.getParameter("status"));
//
        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String sWhere = "where status != '-1'";
//
//        if (keyword != null && keyword.length() > 0) {
//            sWhere += " and clueContent like '%" + keyword + "%'";
//        }
//        if(acceptDateStart != null && acceptDateStart.length() > 0)
//            sWhere += " and acceptDate > '" + acceptDateStart + "'";
//        if(acceptDateEnd != null && acceptDateEnd.length() > 0)
//            sWhere += " and acceptDate < '" + acceptDateEnd + "'";
//        if (caseZone != null && caseZone.length() > 0) {
//            sWhere += " and caseZone like '%" + caseZone + "%'";
//        }
//        if(informantKindValue > 0)
//            sWhere += " and informantKindValue='" + informantKindValue + "'";
//        if(status > 0)
//            sWhere += " and status='" + status + "'";
//
        List<VClueListPojo> list = new ArrayList<VClueListPojo>();
        VClueListDao dao = new VClueListDao();
        int totalCount = dao.totalCount;
        list = dao.getAll(pageNumber, pageSize, "");
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
