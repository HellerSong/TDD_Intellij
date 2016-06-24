package com.servlet;

import com.dao.JbkjxslyDao;
import com.google.gson.Gson;
import com.pojo.AttachmentItem;
import com.pojo.JbkjxslyPojo;
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
public class AdminLoadAttach extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clueId = Parser.parseInt(request.getParameter("clueId"));

        Map<String, Object> map = new HashMap<String, Object>();
        int totalCount = 0;
        List<AttachmentItem> list = new ArrayList<AttachmentItem>();

        if (clueId > 0) {
            JbkjxslyDao dao = new JbkjxslyDao();
            JbkjxslyPojo pojo = dao.getById(clueId);
            String[] fileArray = pojo.getJBKJXSLY_Fujian().split(";");
            totalCount = fileArray.length;

            for (int i = 0; i < fileArray.length; i++) {
                AttachmentItem item = new AttachmentItem();
                item.setFileName("附件" + (i + 1));
                item.setStatus("已上传");
                item.setServerPath("/upload/" + fileArray[i]);
                list.add(item);
            }

            dao.closeAll();
        }


        //// Result data transfer
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        map.put("total", totalCount);
        map.put("rows", list);
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
