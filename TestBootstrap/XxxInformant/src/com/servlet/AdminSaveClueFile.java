package com.servlet;

import com.dao.JbkjxslyDao;
import com.google.gson.Gson;
import com.utils.DateUtil;
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

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class AdminSaveClueFile extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "文件上传失败！";

        //// Create server folder
        String uploadPath = session.getServletContext().getRealPath("/upload");
        File dir = new File(uploadPath);
        if (!dir.exists())
            dir.mkdirs();


        JbkjxslyDao dao = new JbkjxslyDao();
        int lastId = dao.getLastAddedId();
        String uploadedFileJoinStr = dao.getById(lastId).getJBKJXSLY_Fujian();

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String fileJoinStr = "";
        try {
            List<FileItem> fileList = upload.parseRequest(request);
            if (fileList.size() >= 1 && fileList.get(0).getName().length() > 0) {
                DevLog.write("File Count: " + fileList.size());
                for (int i = 0; i < fileList.size(); i++) {
                    String regionFileName = fileList.get(i).getName();
                    DevLog.write("Upload file name: " + regionFileName);
                    if (regionFileName.length() > 0) {
                        String fileExtension = regionFileName.substring(regionFileName.lastIndexOf('.'));
                        String saveFileName = DateUtil.getCurrentTime("yyyyMMdd") + "_" + lastId + "_" + (i + 1) + fileExtension;
                        DevLog.write("Server file name: " + saveFileName);
                        fileList.get(i).write(new File(uploadPath, saveFileName));
                        fileJoinStr += saveFileName + ";";
                    }
                }

                if (fileJoinStr.length() > 0) {
                    fileJoinStr = fileJoinStr.substring(0, fileJoinStr.length() - 1);
                    // The clue editing will append server file name to db segment;
                    // The new create clue will update db segment directly;
                    if (uploadedFileJoinStr != null && uploadedFileJoinStr.length() > 0) {
                        fileJoinStr = uploadedFileJoinStr + ";" + fileJoinStr;
                    }
                    dao.update(lastId, "JBKJXSLY_Fujian", fileJoinStr);

                    dao.closeAll();
                }

                message = "文件上传成功！";
            } else {
                message = "未检测到文件！";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


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
