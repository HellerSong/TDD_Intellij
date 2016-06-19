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

            JbkjxslyDao jbkjxslyDao = new JbkjxslyDao();
            JbkjxslyPojo jbkjxslyPojo = new JbkjxslyPojo();
            jbkjxslyPojo.setJBKJXSLY_LYFSDM(JBKJXSLY_LYFS);
            jbkjxslyPojo.setJBKJXSLY_LYFS(Parser.parseDropdownContent("JBKJXSLY_LYFS", JBKJXSLY_LYFS));
            jbkjxslyPojo.setJBKJXSLY_LYZLDM(JBKJXSLY_LYZL);
            jbkjxslyPojo.setJBKJXSLY_LYZL(Parser.parseDropdownContent("JBKJXSLY_LYZL", JBKJXSLY_LYZL));
            jbkjxslyPojo.setJBKJXSLY_BJBRZTLBDM(JBKJXSLY_BJBRZTLB);
            jbkjxslyPojo.setJBKJXSLY_BJBRZTLB(Parser.parseDropdownContent("JBKJXSLY_BJBRZTLB", JBKJXSLY_BJBRZTLB));
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


            //// 举报人基本情况参数
            String JBKJXSLY_JBRXM = request.getParameter("JBKJXSLY_JBRXM1");
            String JBKJXSLY_SFSM = request.getParameter("JBKJXSLY_SFSM1");
            String JBKJXSLY_JBRSFZH = request.getParameter("JBKJXSLY_JBRSFZH1");
            String JBKJXSLY_JBRDH = request.getParameter("JBKJXSLY_JBRDH1");
            String JBKJXSLY_LXDQ = request.getParameter("JBKJXSLY_LXDQ1");
            String JBKJXSLY_JBRDWZZ = request.getParameter("JBKJXSLY_JBRDWZZ1");
            for (int i = 2; i <= 3; i++) {
                if (request.getParameter("JBKJXSLY_JBRXM" + i) != null) {
                    JBKJXSLY_JBRXM += "@#@" + request.getParameter("JBKJXSLY_JBRXM" + i);
                    JBKJXSLY_SFSM += "@#@" + request.getParameter("JBKJXSLY_SFSM" + i);
                    JBKJXSLY_JBRSFZH += "@#@" + request.getParameter("JBKJXSLY_JBRSFZH" + i);
                    JBKJXSLY_JBRDH += "@#@" + request.getParameter("JBKJXSLY_JBRDH" + i);
                    JBKJXSLY_LXDQ += "@#@" + request.getParameter("JBKJXSLY_LXDQ" + i);
                    JBKJXSLY_JBRDWZZ += "@#@" + request.getParameter("JBKJXSLY_JBRDWZZ" + i);
                }
            }
            jbkjxslyPojo.setJBKJXSLY_JBRXM(JBKJXSLY_JBRXM);
            jbkjxslyPojo.setJBKJXSLY_SFSM(JBKJXSLY_SFSM);
            jbkjxslyPojo.setJBKJXSLY_JBRSFZH(JBKJXSLY_JBRSFZH);
            jbkjxslyPojo.setJBKJXSLY_JBRDH(JBKJXSLY_JBRDH);
            jbkjxslyPojo.setJBKJXSLY_LXDQ(JBKJXSLY_LXDQ);
            jbkjxslyPojo.setJBKJXSLY_JBRDWZZ(JBKJXSLY_JBRDWZZ);


            //// 被举报人基本情况参数
            String JBKJXSLY_BJBRXM = request.getParameter("JBKJXSLY_BJBRXM1");

            DevLog.write(JBKJXSLY_BJBRXM);
            String JBKJXSLY_XB = request.getParameter("JBKJXSLY_XB1");
            String JBKJXSLY_MZ = request.getParameter("JBKJXSLY_MZ1");
            String JBKJXSLY_ZZMM = request.getParameter("JBKJXSLY_ZZMM1");
            String JBKJXSLY_AFDQ = request.getParameter("JBKJXSLY_AFDQ1");
            String JBKJXSLY_ZJ = request.getParameter("JBKJXSLY_ZJ1");
            String JBKJXSLY_BJBRDWZZ = request.getParameter("JBKJXSLY_BJBRDWZZ1");
            String JBKJXSLY_SF = request.getParameter("JBKJXSLY_SF1");
            String JBKJXSLY_TSSF = request.getParameter("JBKJXSLY_TSSF1");
            String JBKJXSLY_ZW = request.getParameter("JBKJXSLY_ZW1");
            String JBKJXSLY_QTZW = request.getParameter("JBKJXSLY_QTZW1");
            String JBKJXSLY_ZYSXXZ = request.getParameter("JBKJXSLY_ZYSXXZ1");
            String JBKJXSLY_CYSXXZ = request.getParameter("JBKJXSLY_CYSXXZ1");
            String JBKJXSLY_SALY = request.getParameter("JBKJXSLY_SALY1");
            String JBKJXSLY_SXJE = request.getParameter("JBKJXSLY_SXJE1");
            String JBKJXSLY_NRSFJT = request.getParameter("JBKJXSLY_NRSFJT1");
            String JBKJXSLY_SFXKQT = request.getParameter("JBKJXSLY_SFXKQT1");
            String JBKJXSLY_SFSBYGX = request.getParameter("JBKJXSLY_SFSBYGX1");
            String JBKJXSLY_SFKG = request.getParameter("JBKJXSLY_SFKG1");
            String JBKJXSLY_SFSS = request.getParameter("JBKJXSLY_SFSS1");
            String JBKJXSLY_SFQT = request.getParameter("JBKJXSLY_SFQT1");
            String JBKJXSLY_SYZY = request.getParameter("JBKJXSLY_SYZY1");
            String JBKJXSLY_NRZY = request.getParameter("JBKJXSLY_NRZY1");
            for (int i = 2; i <= 3; i++) {
                if (request.getParameter("JBKJXSLY_BJBRXM" + i) != null) {
                    JBKJXSLY_BJBRXM += "@#@" + request.getParameter("JBKJXSLY_BJBRXM" + i);
                    JBKJXSLY_XB += "@#@" + request.getParameter("JBKJXSLY_XB" + i);
                    JBKJXSLY_MZ += "@#@" + request.getParameter("JBKJXSLY_MZ" + i);
                    JBKJXSLY_ZZMM += "@#@" + request.getParameter("JBKJXSLY_ZZMM" + i);
                    JBKJXSLY_AFDQ += "@#@" + request.getParameter("JBKJXSLY_AFDQ" + i);
                    JBKJXSLY_ZJ += "@#@" + request.getParameter("JBKJXSLY_ZJ" + i);
                    JBKJXSLY_BJBRDWZZ += "@#@" + request.getParameter("JBKJXSLY_BJBRDWZZ" + i);
                    JBKJXSLY_SF += "@#@" + request.getParameter("JBKJXSLY_SF" + i);
                    JBKJXSLY_TSSF += "@#@" + request.getParameter("JBKJXSLY_TSSF" + i);
                    JBKJXSLY_ZW += "@#@" + request.getParameter("JBKJXSLY_ZW" + i);
                    JBKJXSLY_QTZW += "@#@" + request.getParameter("JBKJXSLY_QTZW" + i);
                    JBKJXSLY_ZYSXXZ += "@#@" + request.getParameter("JBKJXSLY_ZYSXXZ" + i);
                    JBKJXSLY_CYSXXZ += "@#@" + request.getParameter("JBKJXSLY_CYSXXZ" + i);
                    JBKJXSLY_SALY += "@#@" + request.getParameter("JBKJXSLY_SALY" + i);
                    JBKJXSLY_SXJE += "@#@" + request.getParameter("JBKJXSLY_SXJE" + i);
                    JBKJXSLY_NRSFJT += "@#@" + request.getParameter("JBKJXSLY_NRSFJT" + i);
                    JBKJXSLY_SFXKQT += "@#@" + request.getParameter("JBKJXSLY_SFXKQT" + i);
                    JBKJXSLY_SFSBYGX += "@#@" + request.getParameter("JBKJXSLY_SFSBYGX" + i);
                    JBKJXSLY_SFKG += "@#@" + request.getParameter("JBKJXSLY_SFKG" + i);
                    JBKJXSLY_SFSS += "@#@" + request.getParameter("JBKJXSLY_SFSS" + i);
                    JBKJXSLY_SFQT += "@#@" + request.getParameter("JBKJXSLY_SFQT" + i);
                    JBKJXSLY_SYZY += "@#@" + request.getParameter("JBKJXSLY_SYZY" + i);
                    JBKJXSLY_NRZY += "@#@" + request.getParameter("JBKJXSLY_NRZY" + i);
                }
            }
            jbkjxslyPojo.setJBKJXSLY_BJBRXM(JBKJXSLY_BJBRXM);
            jbkjxslyPojo.setJBKJXSLY_XB(JBKJXSLY_XB);
            jbkjxslyPojo.setJBKJXSLY_MZ(JBKJXSLY_MZ);
            jbkjxslyPojo.setJBKJXSLY_ZZMM(JBKJXSLY_ZZMM);
            jbkjxslyPojo.setJBKJXSLY_AFDQ(JBKJXSLY_AFDQ);
            jbkjxslyPojo.setJBKJXSLY_ZJ(JBKJXSLY_ZJ);
            jbkjxslyPojo.setJBKJXSLY_BJBRDWZZ(JBKJXSLY_BJBRDWZZ);
            jbkjxslyPojo.setJBKJXSLY_SF(JBKJXSLY_SF);
            jbkjxslyPojo.setJBKJXSLY_TSSF(JBKJXSLY_TSSF);
            jbkjxslyPojo.setJBKJXSLY_ZW(JBKJXSLY_ZW);
            jbkjxslyPojo.setJBKJXSLY_QTZW(JBKJXSLY_QTZW);
            jbkjxslyPojo.setJBKJXSLY_ZYSXXZ(JBKJXSLY_ZYSXXZ);
            jbkjxslyPojo.setJBKJXSLY_CYSXXZ(JBKJXSLY_CYSXXZ);
            jbkjxslyPojo.setJBKJXSLY_SALY(JBKJXSLY_SALY);
            jbkjxslyPojo.setJBKJXSLY_SXJE(JBKJXSLY_SXJE);
            jbkjxslyPojo.setJBKJXSLY_NRSFJT(JBKJXSLY_NRSFJT);
            jbkjxslyPojo.setJBKJXSLY_SFXKQT(JBKJXSLY_SFXKQT);
            jbkjxslyPojo.setJBKJXSLY_SFSBYGX(JBKJXSLY_SFSBYGX);
            jbkjxslyPojo.setJBKJXSLY_SFKG(JBKJXSLY_SFKG);
            jbkjxslyPojo.setJBKJXSLY_SFSS(JBKJXSLY_SFSS);
            jbkjxslyPojo.setJBKJXSLY_SFQT(JBKJXSLY_SFQT);
            jbkjxslyPojo.setJBKJXSLY_SYZY(JBKJXSLY_SYZY);
            jbkjxslyPojo.setJBKJXSLY_NRZY(JBKJXSLY_NRZY);


            xsclDao.add(xsclPojo);
            jbkjxslyDao.add(jbkjxslyPojo);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
