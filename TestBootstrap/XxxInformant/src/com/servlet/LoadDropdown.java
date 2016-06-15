package com.servlet;

import com.dao.DropdownDao;
import com.google.gson.Gson;
import com.utils.DevLog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by v-hesong on 6/14/2016.
 */
public class LoadDropdown extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DropdownDao dao = new DropdownDao();
        String json = new Gson().toJson(dao.dropdownTable);
        DevLog.write(dao.dropdownTable.size());
        DevLog.write(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
