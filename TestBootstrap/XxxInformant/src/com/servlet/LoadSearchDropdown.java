package com.servlet;

import com.dao.DropdownDao;
import com.google.gson.Gson;
import com.pojo.DropdownItem;
import com.utils.DevLog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class LoadSearchDropdown extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();

        // This kind of dropdown is with "All" option value
        Hashtable<String, List<DropdownItem>> dropdownHt = DropdownDao.getSearchDropdownHt();

        //// Result data transfer
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        map.put("total", dropdownHt.size());
        map.put("rows", dropdownHt);
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
