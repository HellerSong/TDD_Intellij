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
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Parser.parseInt(request.getParameter("pageNumber"));
        int pageSize = Parser.parseInt(request.getParameter("pageSize"));

        int isALLClue = Parser.parseInt(request.getParameter("isALLClue"));
        int isXSClue = Parser.parseInt(request.getParameter("isXSClue"));
        int isCFClue = Parser.parseInt(request.getParameter("isCFClue"));
        int isJCGJClue = Parser.parseInt(request.getParameter("isJCGJClue"));
        int isTJClue = Parser.parseInt(request.getParameter("isTJClue"));
        int isSBJClue = Parser.parseInt(request.getParameter("isSBJClue"));

        String keyword = request.getParameter("keyword");
        int clueType = Parser.parseInt(request.getParameter("JBKJXSLY_LYFS"));
        int clueStatus = Parser.parseInt(request.getParameter("JBKJXSLY_CLZT"));
        String acceptDateStart = request.getParameter("JBKJXSLY_SLRQ_Start");
        String acceptDateEnd = request.getParameter("JBKJXSLY_SLRQ_End");
        //String clueZone = request.getParameter("JBKJXSLY_AFDQ");

        Map<String, Object> map = new HashMap<String, Object>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String sWhere = "where JBKJXSLY_CLZT != '-1'";

        if (keyword != null && keyword.length() > 0) {
            sWhere += " and (JBKJXSLY_JBRXM like '%" + keyword + "%'";
            sWhere += " or JBKJXSLY_JBRDWZZ like '%" + keyword + "%'";
            sWhere += " or JBKJXSLY_BJBRXM like '%" + keyword + "%'";
            sWhere += " or JBKJXSLY_BJBRDWZZ like '%" + keyword + "%'";
            sWhere += " or JBKJXSLY_SYZY like '%" + keyword + "%'";
            sWhere += " or JBKJXSLY_NRZY like '%" + keyword + "%'";
            sWhere += " or JBKJXSLY_Keywords like '%" + keyword + "%')";
        }
        if (clueType > 0)
            sWhere += " and JBKJXSLY_LYFSDM ='" + clueType + "'";
        if (clueStatus > 0)
            sWhere += " and JBKJXSLY_CLZT='" + clueStatus + "'";
        if (acceptDateStart != null && acceptDateStart.length() > 0)
            sWhere += " and '" + acceptDateStart + "' <= JBKJXSLY_SLRQ";
        if (acceptDateEnd != null && acceptDateEnd.length() > 0)
            sWhere += " and JBKJXSLY_SLRQ <= '" + acceptDateEnd + "'";

        if (isALLClue == 1) {

        } else if (isXSClue == 1) {
            sWhere += " and JBKJXSLY_JBJCGJWFWJ <> '1'";
            sWhere += " and (JBKJXSLY_ZJDM <> '05' or JBKJXSLY_ZJDM <> '06')";
//            sWhere += " or JBKJXSLY_QTZJDM <> '05' or JBKJXSLY_QTZJDM <> '06')";
        } else if (isJCGJClue == 1) {
            sWhere += " and JBKJXSLY_JBJCGJWFWJ = '1'";
            sWhere += " and (JBKJXSLY_ZJDM <> '05' and JBKJXSLY_ZJDM <> '06')";
//            sWhere += " and JBKJXSLY_QTZJDM <> '05' and JBKJXSLY_QTZJDM <> '06')";
        } else if (isTJClue == 1) {
            sWhere += " and (JBKJXSLY_ZJDM = '05' or JBKJXSLY_ZJDM = '06')";
//            sWhere += " or JBKJXSLY_QTZJDM = '05' or JBKJXSLY_QTZJDM = '06')";
        } else if (isSBJClue == 1) {
            // 03-正部级，04-副部级
            sWhere += " and (JBKJXSLY_ZJ like '%03%' or JBKJXSLY_ZJ like '%04%')";
//            sWhere += " or JBKJXSLY_QTZJDM = '05' or JBKJXSLY_QTZJDM = '06')";
        }

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
