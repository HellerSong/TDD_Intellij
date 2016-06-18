package com.servlet;

import com.dao.JbkjxslyDao;
import com.google.gson.Gson;
import com.utils.DevLog;
import com.utils.Parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class AdminDeleteClue extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clueCollection = request.getParameter("clueCollection");
        boolean result = false;
        String message = "删除失败！";

        String[] clueIdList = clueCollection.split("@#@");
        JbkjxslyDao dao = new JbkjxslyDao();
        for (String clueIdStr : clueIdList) {
            int JBKJXSLY_ID = Parser.parseInt(clueIdStr);
            DevLog.write(JBKJXSLY_ID);
            result = dao.update(JBKJXSLY_ID, "JBKJXSLY_CLZT", "-1");
        }
        dao.closeAll();

        if (result) {
            message = "删除成功！";
        }


        //// Result data transfer
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
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
