package com.servlet;

import com.dao.VClueListDao;
import com.google.gson.Gson;
import com.pojo.VClueListPojo;
import com.utils.ClueUtil;
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
public class AdminGetClueId extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clueId = Parser.parseInt(request.getParameter("clueId"));
        String getType = request.getParameter("getType");
        int isALLClue = Parser.parseInt(request.getParameter("isALLClue"));
        int isXSClue = Parser.parseInt(request.getParameter("isXSClue"));
        int isCFClue = Parser.parseInt(request.getParameter("isCFClue"));
        int isJCGJClue = Parser.parseInt(request.getParameter("isJCGJClue"));
        int isTJJClue = Parser.parseInt(request.getParameter("isTJJClue"));
        int isSBJClue = Parser.parseInt(request.getParameter("isSBJClue"));

        Map<String, Object> map = new HashMap<String, Object>();
        int targetClueId = -1;
        String sWhere = "where JBKJXSLY_CLZT != '-1'";
        sWhere += ClueUtil.getClueTypeWhereString(isALLClue, isCFClue, isXSClue, isJCGJClue, isTJJClue, isSBJClue);

        if (clueId > 0) {
            VClueListDao dao = new VClueListDao();
            List<VClueListPojo> list = dao.getAll(sWhere);
            if (getType.equals("First")) {
                targetClueId = list.get(0).getJBKJXSLY_ID();
            } else if (getType.equals("Last")) {
                targetClueId = list.get(list.size() - 1).getJBKJXSLY_ID();
            } else if (getType.equals("Previous")) {
                int previousId = list.get(0).getJBKJXSLY_ID();
                if (clueId == previousId) {
                    targetClueId = -1;
                } else {
                    for (int i = 1; i < list.size() - 1; i++) {
                        previousId = list.get(i).getJBKJXSLY_ID();
                        if (clueId == list.get(i + 1).getJBKJXSLY_ID()) {
                            targetClueId = previousId;
                            break;
                        }
                    }
                }
            } else if (getType.equals("Next")) {
                int nextId;
                if (clueId == list.get(list.size() - 1).getJBKJXSLY_ID()) {
                    targetClueId = -1;
                } else {
                    for (int i = 0; i < list.size() - 1; i++) {
                        if (clueId == list.get(i).getJBKJXSLY_ID()) {
                            targetClueId = list.get(i + 1).getJBKJXSLY_ID();
                            break;
                        }
                    }
                }
            }

            dao.closeAll();
        }


        //// Result data transfer
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        map.put("targetClueId", targetClueId);
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
