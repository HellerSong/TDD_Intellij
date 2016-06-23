package com.servlet;

import com.google.gson.Gson;
import com.utils.DevLog;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class AdminSaveClueFile extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Map<String, Object> map = new HashMap<String, Object>();
        String message;

        //// Create server folder
        String uploadPath = session.getServletContext().getRealPath("/upload");
        File dir = new File(uploadPath);
        if (!dir.exists())
            dir.mkdirs();

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> fileList = upload.parseRequest(request);
            DevLog.write("File Count: " + fileList.size());
            for (FileItem item : fileList) {
                String regionFileName = item.getName();
                DevLog.write("Upload file name: " + regionFileName);
                if (regionFileName.length() > 0) {
                    String fileExtension = regionFileName.substring(regionFileName.lastIndexOf('.'));
                    String saveFileName = UUID.randomUUID().toString() + fileExtension;
                    DevLog.write("Server file name: " + saveFileName);
                    item.write(new File(uploadPath, saveFileName));
                    //serverFileList.add(saveFileName);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        message = "文件上传成功";

        //// Result data transfer
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
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
