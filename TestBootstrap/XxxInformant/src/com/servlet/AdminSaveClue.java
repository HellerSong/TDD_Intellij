package com.servlet;

import com.dao.JbkjxslyDao;
import com.dao.XsclDao;
import com.google.gson.Gson;
import com.pojo.JbkjxslyPojo;
import com.pojo.XsclPojo;
import com.utils.DevLog;
import com.utils.Parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

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
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        String message;

        if (isDataReady(request)) {
            //// 线索序号（jbkjxsly表与xscl表关联用）
            String XH = request.getParameter("JBKJXSLY_XH");

            //// 举报线索情况参数
            String JBKJXSLY_LYFSDM = request.getParameter("JBKJXSLY_LYFS");
            String JBKJXSLY_LYZLDM = request.getParameter("JBKJXSLY_LYZL");
            String JBKJXSLY_BJBRZTLBDM = request.getParameter("JBKJXSLY_BJBRZTLB");
            String JBKJXSLY_ZJDWDM = request.getParameter("JBKJXSLY_ZJDW");
            String JBKJXSLY_SLRQ = request.getParameter("JBKJXSLY_SLRQ");
            String JBKJXSLY_ZJR = request.getParameter("JBKJXSLY_ZJR");


            JbkjxslyDao jbkjxslyDao = new JbkjxslyDao();
            JbkjxslyPojo jbkjxslyPojo = new JbkjxslyPojo();
            jbkjxslyPojo.setJBKJXSLY_LYFSDM(JBKJXSLY_LYFSDM);
            jbkjxslyPojo.setJBKJXSLY_LYFS(Parser.parseDropdownContent("JBKJXSLY_LYFS", JBKJXSLY_LYFSDM));
            jbkjxslyPojo.setJBKJXSLY_LYZLDM(JBKJXSLY_LYZLDM);
            jbkjxslyPojo.setJBKJXSLY_LYZL(Parser.parseDropdownContent("JBKJXSLY_LYZL", JBKJXSLY_LYZLDM));
            jbkjxslyPojo.setJBKJXSLY_BJBRZTLBDM(JBKJXSLY_BJBRZTLBDM);
            jbkjxslyPojo.setJBKJXSLY_BJBRZTLB(Parser.parseDropdownContent("JBKJXSLY_BJBRZTLB", JBKJXSLY_BJBRZTLBDM));
            jbkjxslyPojo.setJBKJXSLY_ZJDWDM(JBKJXSLY_ZJDWDM);
            jbkjxslyPojo.setJBKJXSLY_ZJDW(Parser.parseDropdownContent("JBKJXSLY_ZJDW", JBKJXSLY_ZJDWDM));
            jbkjxslyPojo.setJBKJXSLY_SLRQ(Parser.parseDate(JBKJXSLY_SLRQ)); // Deafult is: today
            jbkjxslyPojo.setJBKJXSLY_ZJR(JBKJXSLY_ZJR);
            jbkjxslyPojo.setJBKJXSLY_CLZT(1);   // Default status: 待处理
            jbkjxslyPojo.setJBKJXSLY_XH(XH);    // 线索编号


            //// 办理情况参数
            String CLFSDM = request.getParameter("CLFS");
            String CBR = request.getParameter("CBR");
            String ZWDWDM = request.getParameter("ZWDW");
            String CSDWDM = request.getParameter("CSDW");
            String CSRQ = request.getParameter("CSRQ");
            String CBRCLRQ = request.getParameter("CBRCLRQ");
            String JBKJXSLY_JBJCGJWFWJ = request.getParameter("JBKJXSLY_JBJCGJWFWJ");
            String JBZRYJ = request.getParameter("JBZRYJ");
            String CZYJ = request.getParameter("CZYJ");
            String CZSPRQ = request.getParameter("CZSPRQ");
            String JBKJXSLY_JYLXDM = request.getParameter("JBKJXSLY_JYLX");
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
            xsclPojo.setCLFSDM(CLFSDM);
            xsclPojo.setCLFS(Parser.parseDropdownContent("CLFS", CLFSDM));
            xsclPojo.setCBR(CBR);
            xsclPojo.setZWDWDM(ZWDWDM);
            xsclPojo.setZWDW(Parser.parseDropdownContent("ZWDW", ZWDWDM));
            xsclPojo.setCSDWDM(CSDWDM);
            xsclPojo.setCSDW(Parser.parseDropdownContent("CSDW", CSDWDM));
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

            jbkjxslyPojo.setJBKJXSLY_JBJCGJWFWJ(JBKJXSLY_JBJCGJWFWJ);
            jbkjxslyPojo.setJBKJXSLY_JYLXDM(JBKJXSLY_JYLXDM);
            jbkjxslyPojo.setJBKJXSLY_JYLX(Parser.parseDropdownContent("JBKJXSLY_JYLX", JBKJXSLY_JYLXDM));
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
            String JBKJXSLY_LXDQDM = request.getParameter("JBKJXSLY_LXDQ1");
            String JBKJXSLY_LXDQ = Parser.parseDropdownContent("JBKJXSLY_LXDQ", JBKJXSLY_LXDQDM);
            String JBKJXSLY_JBRDWZZ = request.getParameter("JBKJXSLY_JBRDWZZ1");
            for (int i = 2; i <= 3; i++) {
                if (request.getParameter("JBKJXSLY_JBRXM" + i) != null) {
                    JBKJXSLY_JBRXM += "@#@" + request.getParameter("JBKJXSLY_JBRXM" + i);
                    JBKJXSLY_SFSM += "@#@" + request.getParameter("JBKJXSLY_SFSM" + i);
                    JBKJXSLY_JBRSFZH += "@#@" + request.getParameter("JBKJXSLY_JBRSFZH" + i);
                    JBKJXSLY_JBRDH += "@#@" + request.getParameter("JBKJXSLY_JBRDH" + i);
                    JBKJXSLY_LXDQDM += "@#@" + request.getParameter("JBKJXSLY_LXDQ" + i);
                    JBKJXSLY_LXDQ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_LXDQ", request.getParameter("JBKJXSLY_LXDQ" + i));
                    JBKJXSLY_JBRDWZZ += "@#@" + request.getParameter("JBKJXSLY_JBRDWZZ" + i);
                }
            }
            jbkjxslyPojo.setJBKJXSLY_JBRXM(JBKJXSLY_JBRXM);
            jbkjxslyPojo.setJBKJXSLY_SFSM(JBKJXSLY_SFSM);
            jbkjxslyPojo.setJBKJXSLY_JBRSFZH(JBKJXSLY_JBRSFZH);
            jbkjxslyPojo.setJBKJXSLY_JBRDH(JBKJXSLY_JBRDH);
            jbkjxslyPojo.setJBKJXSLY_LXDQDM(JBKJXSLY_LXDQDM);
            jbkjxslyPojo.setJBKJXSLY_LXDQ(JBKJXSLY_LXDQ);
            jbkjxslyPojo.setJBKJXSLY_LXDQ(JBKJXSLY_LXDQ);
            jbkjxslyPojo.setJBKJXSLY_JBRDWZZ(JBKJXSLY_JBRDWZZ);


            //// 被举报人基本情况参数
            String JBKJXSLY_BJBRXM = request.getParameter("JBKJXSLY_BJBRXM1");
            String JBKJXSLY_XB = request.getParameter("JBKJXSLY_XB1");
            String JBKJXSLY_MZDM = request.getParameter("JBKJXSLY_MZ1");
            String JBKJXSLY_MZ = Parser.parseDropdownContent("JBKJXSLY_MZ", JBKJXSLY_MZDM);
            String JBKJXSLY_ZZMMDM = request.getParameter("JBKJXSLY_ZZMM1");
            String JBKJXSLY_ZZMM = Parser.parseDropdownContent("JBKJXSLY_ZZMM", JBKJXSLY_ZZMMDM);
            String JBKJXSLY_AFDQDM = request.getParameter("JBKJXSLY_AFDQ1");
            String JBKJXSLY_AFDQ = Parser.parseDropdownContent("JBKJXSLY_AFDQ", JBKJXSLY_AFDQDM);
            String JBKJXSLY_ZJDM = request.getParameter("JBKJXSLY_ZJ1");
            String JBKJXSLY_ZJ = Parser.parseDropdownContent("JBKJXSLY_ZJ", JBKJXSLY_ZJDM);
            String JBKJXSLY_BJBRDWZZ = request.getParameter("JBKJXSLY_BJBRDWZZ1");
            String JBKJXSLY_SFDM = request.getParameter("JBKJXSLY_SF1");
            String JBKJXSLY_SF = Parser.parseDropdownContent("JBKJXSLY_SF", JBKJXSLY_SFDM);
            String JBKJXSLY_TSSFDM = request.getParameter("JBKJXSLY_TSSF1");
            String JBKJXSLY_TSSF = Parser.parseDropdownContent("JBKJXSLY_TSSF", JBKJXSLY_TSSFDM);
            String JBKJXSLY_ZWDM = request.getParameter("JBKJXSLY_ZW1");
            String JBKJXSLY_ZW = Parser.parseDropdownContent("JBKJXSLY_ZW", JBKJXSLY_ZWDM);
            String JBKJXSLY_QTZWDM = request.getParameter("JBKJXSLY_QTZW1");
            String JBKJXSLY_QTZW = Parser.parseDropdownContent("JBKJXSLY_QTZW", JBKJXSLY_QTZWDM);
            String JBKJXSLY_ZYSXXZDM = request.getParameter("JBKJXSLY_ZYSXXZ1");
            String JBKJXSLY_ZYSXXZ = Parser.parseDropdownContent("JBKJXSLY_ZYSXXZ", JBKJXSLY_ZYSXXZDM);
            String JBKJXSLY_CYSXXZDM = request.getParameter("JBKJXSLY_CYSXXZ1");
            String JBKJXSLY_CYSXXZ = Parser.parseDropdownContent("JBKJXSLY_CYSXXZ", JBKJXSLY_CYSXXZDM);
            String JBKJXSLY_SALYDM = request.getParameter("JBKJXSLY_SALY1");
            String JBKJXSLY_SALY = Parser.parseDropdownContent("JBKJXSLY_SALY", JBKJXSLY_SALYDM);
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
                    JBKJXSLY_MZDM += "@#@" + request.getParameter("JBKJXSLY_MZ" + i);
                    JBKJXSLY_MZ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_MZ", request.getParameter("JBKJXSLY_MZ" + i));
                    JBKJXSLY_ZZMMDM += "@#@" + request.getParameter("JBKJXSLY_ZZMM" + i);
                    JBKJXSLY_ZZMM += "@#@" + Parser.parseDropdownContent("JBKJXSLY_ZZMM", request.getParameter("JBKJXSLY_ZZMM" + i));
                    JBKJXSLY_AFDQDM += "@#@" + request.getParameter("JBKJXSLY_AFDQ" + i);
                    JBKJXSLY_AFDQ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_AFDQ", request.getParameter("JBKJXSLY_AFDQ" + i));
                    JBKJXSLY_ZJDM += "@#@" + request.getParameter("JBKJXSLY_ZJ" + i);
                    JBKJXSLY_ZJ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_ZJ", request.getParameter("JBKJXSLY_ZJ" + i));
                    JBKJXSLY_BJBRDWZZ += "@#@" + request.getParameter("JBKJXSLY_BJBRDWZZ" + i);
                    JBKJXSLY_SFDM += "@#@" + request.getParameter("JBKJXSLY_SF" + i);
                    JBKJXSLY_SF += "@#@" + Parser.parseDropdownContent("JBKJXSLY_SF", request.getParameter("JBKJXSLY_SF" + i));
                    JBKJXSLY_TSSFDM += "@#@" + request.getParameter("JBKJXSLY_TSSF" + i);
                    JBKJXSLY_TSSF += "@#@" + Parser.parseDropdownContent("JBKJXSLY_TSSF", request.getParameter("JBKJXSLY_TSSF" + i));
                    JBKJXSLY_ZWDM += "@#@" + request.getParameter("JBKJXSLY_ZW" + i);
                    JBKJXSLY_ZW += "@#@" + Parser.parseDropdownContent("JBKJXSLY_ZW", request.getParameter("JBKJXSLY_ZW" + i));
                    JBKJXSLY_QTZWDM += "@#@" + request.getParameter("JBKJXSLY_QTZW" + i);
                    JBKJXSLY_QTZW += "@#@" + Parser.parseDropdownContent("JBKJXSLY_QTZW", request.getParameter("JBKJXSLY_QTZW" + i));
                    JBKJXSLY_ZYSXXZDM += "@#@" + request.getParameter("JBKJXSLY_ZYSXXZ" + i);
                    JBKJXSLY_ZYSXXZ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_ZYSXXZ", request.getParameter("JBKJXSLY_ZYSXXZ" + i));
                    JBKJXSLY_CYSXXZDM += "@#@" + request.getParameter("JBKJXSLY_CYSXXZ" + i);
                    JBKJXSLY_CYSXXZ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_CYSXXZ", request.getParameter("JBKJXSLY_CYSXXZ" + i));
                    JBKJXSLY_SALYDM += "@#@" + request.getParameter("JBKJXSLY_SALY" + i);
                    JBKJXSLY_SALY += "@#@" + Parser.parseDropdownContent("JBKJXSLY_SALY", request.getParameter("JBKJXSLY_SALY" + i));
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
            jbkjxslyPojo.setJBKJXSLY_MZDM(JBKJXSLY_MZDM);
            jbkjxslyPojo.setJBKJXSLY_MZ(JBKJXSLY_MZ);
            jbkjxslyPojo.setJBKJXSLY_ZZMMDM(JBKJXSLY_ZZMMDM);
            jbkjxslyPojo.setJBKJXSLY_ZZMM(JBKJXSLY_ZZMM);
            jbkjxslyPojo.setJBKJXSLY_AFDQDM(JBKJXSLY_AFDQDM);
            jbkjxslyPojo.setJBKJXSLY_AFDQ(JBKJXSLY_AFDQ);
            jbkjxslyPojo.setJBKJXSLY_ZJDM(JBKJXSLY_ZJDM);
            jbkjxslyPojo.setJBKJXSLY_ZJ(JBKJXSLY_ZJ);
            jbkjxslyPojo.setJBKJXSLY_BJBRDWZZ(JBKJXSLY_BJBRDWZZ);
            jbkjxslyPojo.setJBKJXSLY_SFDM(JBKJXSLY_SFDM);
            jbkjxslyPojo.setJBKJXSLY_SF(JBKJXSLY_SF);
            jbkjxslyPojo.setJBKJXSLY_TSSFDM(JBKJXSLY_TSSFDM);
            jbkjxslyPojo.setJBKJXSLY_TSSF(JBKJXSLY_TSSF);
            jbkjxslyPojo.setJBKJXSLY_ZWDM(JBKJXSLY_ZWDM);
            jbkjxslyPojo.setJBKJXSLY_ZW(JBKJXSLY_ZW);
            jbkjxslyPojo.setJBKJXSLY_QTZWDM(JBKJXSLY_QTZWDM);
            jbkjxslyPojo.setJBKJXSLY_QTZW(JBKJXSLY_QTZW);
            jbkjxslyPojo.setJBKJXSLY_ZYSXXZDM(JBKJXSLY_ZYSXXZDM);
            jbkjxslyPojo.setJBKJXSLY_ZYSXXZ(JBKJXSLY_ZYSXXZ);
            jbkjxslyPojo.setJBKJXSLY_CYSXXZDM(JBKJXSLY_CYSXXZDM);
            jbkjxslyPojo.setJBKJXSLY_CYSXXZ(JBKJXSLY_CYSXXZ);
            jbkjxslyPojo.setJBKJXSLY_SALYDM(JBKJXSLY_SALYDM);
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
            xsclDao.closeAll();
            jbkjxslyDao.closeAll();
            message = "保存成功！";
        } else {
            message = "数据提交有错误！";
        }

        //// Result data transfer
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        resultMap.put("status", message);
        String json = new Gson().toJson(resultMap);
        DevLog.write(json);
        out.println(json);
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
