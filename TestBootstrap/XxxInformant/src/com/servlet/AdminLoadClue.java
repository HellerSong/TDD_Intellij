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
public class AdminLoadClue extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clueId = Parser.parseInt(request.getParameter("clueId"));
//        
//        int isXSClue = Parser.parseInt(request.getParameter("isXSClue"));
//        int isJCGJClue = Parser.parseInt(request.getParameter("isJCGJClue"));
//        int isTJJClue = Parser.parseInt(request.getParameter("isTJJClue"));

        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String sWhere = "where JBKJXSLY_CLZT != '-1'";
        int totalCount = 0;
        List<VClueListPojo> list = new ArrayList<VClueListPojo>();

        if (clueId > 0) {
            sWhere += " and JBKJXSLY_ID = '" + clueId + "'";
            VClueListDao dao = new VClueListDao();
            list = dao.getAll(sWhere);

            dao.closeAll();
        }


        //// Result data transfer
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        map.put("total", 1);
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
