package com.servlet;

import com.dao.DropdownDao;
import com.google.gson.Gson;
import com.pojo.DropdownItemPojo;
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
 * Created by v-hesong on 6/14/2016.
 */
public class LoadSearchDropdown extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();

        DropdownDao dao = new DropdownDao();
        // This kind of dropdown is with "All" option value
        Hashtable<String, List<DropdownItemPojo>> dropdownHt = dao.getSearchDropdownHt();

        //// Result data transfer
        response.setContentType("text/plain");
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