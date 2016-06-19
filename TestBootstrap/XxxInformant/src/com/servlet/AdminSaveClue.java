package com.servlet;

import com.dao.JbkjxslyDao;
import com.dao.XsclDao;
import com.pojo.JbkjxslyPojo;
import com.pojo.XsclPojo;
import com.utils.DevLog;
import com.utils.Parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class AdminSaveClue extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private boolean isDataReady(HttpServletRequest request) {
//        if (request.getParameter("informantTypeValue") == null || request.getParameter("informantTypeValue").length() <= 0)
//            return false;
//
//        if (request.getParameter("informantTypeValue") == null || request.getParameter("informantTypeValue").length() <= 0)
//            return false;
//
//        if (request.getParameter("informantTypeValue") == null || request.getParameter("informantTypeValue").length() <= 0)
//            return false;
//
//        if (request.getParameter("informantTypeValue") == null || request.getParameter("informantTypeValue").length() <= 0)
//            return false;
//
//        if (request.getParameter("informantTypeValue") == null || request.getParameter("informantTypeValue").length() <= 0)
//            return false;

        return true;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isDataReady(request)) {
            //// 线索序号（jbkjxsly表与xscl表关联用）
            String XH = UUID.randomUUID().toString();

            //// 举报线索情况参数
            String JBKJXSLY_LYFS = request.getParameter("JBKJXSLY_LYFS");
            String JBKJXSLY_LYZL = request.getParameter("JBKJXSLY_LYZL");
            String JBKJXSLY_BJBRZTLB = request.getParameter("JBKJXSLY_BJBRZTLB");
            String JBKJXSLY_ZJDW = request.getParameter("JBKJXSLY_ZJDW");
            String JBKJXSLY_SLRQ = request.getParameter("JBKJXSLY_SLRQ");
            String JBKJXSLY_ZJR = request.getParameter("JBKJXSLY_ZJR");

            DevLog.write(JBKJXSLY_LYFS);
            DevLog.write(JBKJXSLY_ZJR);

            JbkjxslyDao jbkjxslyDao = new JbkjxslyDao();
            JbkjxslyPojo jbkjxslyPojo = new JbkjxslyPojo();
            jbkjxslyPojo.setJBKJXSLY_LYFS(JBKJXSLY_LYFS);
            jbkjxslyPojo.setJBKJXSLY_LYZL(JBKJXSLY_LYZL);
            jbkjxslyPojo.setJBKJXSLY_BJBRZTLB(JBKJXSLY_BJBRZTLB);
            jbkjxslyPojo.setJBKJXSLY_ZJDW(JBKJXSLY_ZJDW);
            jbkjxslyPojo.setJBKJXSLY_SLRQ(Parser.parseDate(JBKJXSLY_SLRQ));
            jbkjxslyPojo.setJBKJXSLY_ZJR(JBKJXSLY_ZJR);
            jbkjxslyPojo.setJBKJXSLY_CLZT(1);   // 默认状态：待处理
            jbkjxslyPojo.setJBKJXSLY_XH(XH);    // 序号


            //// 办理情况参数
            String CLYJ = request.getParameter("CLYJ");
            String CBR = request.getParameter("CBR");
            String ZWDW = request.getParameter("ZWDW");
            String JBKJXSLY_OutId = request.getParameter("JBKJXSLY_OutId");
            String CSDW = request.getParameter("CSDW");
            String CSRQ = request.getParameter("CSRQ");
            String CBRCLRQ = request.getParameter("CBRCLRQ");
            String JBKJXSLY_JBJCGJWFWJ = request.getParameter("JBKJXSLY_JBJCGJWFWJ");
            String JBZRYJ = request.getParameter("JBZRYJ");
            String CZYJ = request.getParameter("CZYJ");
            String CZSPRQ = request.getParameter("CZSPRQ");
            String JBKJXSLY_JYLX = request.getParameter("JBKJXSLY_JYLX");
            String JBKJXSLY_XJWH = request.getParameter("JBKJXSLY_XJWH");
            String TZYJ = request.getParameter("TZYJ");
            String TZSPRQ = request.getParameter("TZSPRQ");
            String JCZPS = request.getParameter("JCZPS");
            String JCZPSRQ = request.getParameter("JCZPSRQ");
            String JBKJXSLY_CLQK = request.getParameter("JBKJXSLY_CLQK");
            String JBKJXSLY_JDDD = request.getParameter("JBKJXSLY_JDDD");
            String JBKJXSLY_HFRQ = request.getParameter("JBKJXSLY_HFRQ");
            String JBKJXSLY_BZ = request.getParameter("JBKJXSLY_BZ");

            XsclDao xsclDao = new XsclDao();
            XsclPojo xsclPojo = new XsclPojo();
            xsclPojo.setCLYJ(CLYJ);
            xsclPojo.setCBR(CBR);
            xsclPojo.setZWDW(ZWDW);
            xsclPojo.setCSDW(CSDW);
            xsclPojo.setCSRQ(Parser.parseDate(CSRQ));
            xsclPojo.setCBRCLRQ(Parser.parseDate(CBRCLRQ));
            xsclPojo.setJBZRYJ(JBZRYJ);
            xsclPojo.setCZYJ(CZYJ);
            xsclPojo.setCZSPRQ(Parser.parseDate(CZSPRQ));
            xsclPojo.setTZYJ(TZYJ);
            xsclPojo.setTZSPRQ(Parser.parseDate(TZSPRQ));
            xsclPojo.setJCZPS(JCZPS);
            xsclPojo.setJCZPSRQ(Parser.parseDate(JCZPSRQ));
            xsclPojo.setJBKJXSLY_XH(XH);

            jbkjxslyPojo.setJBKJXSLY_OutId(Parser.parseInt(JBKJXSLY_OutId));
            jbkjxslyPojo.setJBKJXSLY_JBJCGJWFWJ(JBKJXSLY_JBJCGJWFWJ);
            jbkjxslyPojo.setJBKJXSLY_JYLX(JBKJXSLY_JYLX);
            jbkjxslyPojo.setJBKJXSLY_XJWH(JBKJXSLY_XJWH);
            jbkjxslyPojo.setJBKJXSLY_CLQK(JBKJXSLY_CLQK);
            jbkjxslyPojo.setJBKJXSLY_JDDD(JBKJXSLY_JDDD);
            jbkjxslyPojo.setJBKJXSLY_HFRQ(Parser.parseDate(JBKJXSLY_HFRQ));
            jbkjxslyPojo.setJBKJXSLY_BZ(JBKJXSLY_BZ);

//            for(int i = 1; i <=3;  i++) {
//                //// 举报人基本情况参数
//                String JBKJXSLY_JBRXM = request.getParameter("JBKJXSLY_JBRXM1");
//                String JBKJXSLY_SFSM = request.getParameter("JBKJXSLY_SFSM1");
//                String JBKJXSLY_JBRSFZH = request.getParameter("JBKJXSLY_JBRSFZH1");
//                String JBKJXSLY_JBRDH = request.getParameter("JBKJXSLY_JBRDH1");
//                String JBKJXSLY_LXDQ = request.getParameter("JBKJXSLY_LXDQ1");
//                String JBKJXSLY_JBRDWZZ = request.getParameter("JBKJXSLY_JBRDWZZ1");
//            }

            xsclDao.add(xsclPojo);
            jbkjxslyDao.add(jbkjxslyPojo);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
