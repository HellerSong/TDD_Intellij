package com.servlet;

import com.dao.JbkjxslyDao;
import com.dao.XsclDao;
import com.google.gson.Gson;
import com.pojo.AllEnums;
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
        int clueId = Parser.parseInt(request.getParameter("clueId"));

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        String message;

        if (isDataReady(request)) {
            //// JBKJXSLY_XH，线索序号，数据库Insert触发自动生成（jbkjxsly表与xscl表关联用）


            //// 举报线索情况参数
            String JBKJXSLY_LYFSDM = request.getParameter("JBKJXSLY_LYFS");
            String JBKJXSLY_LYZLDM = request.getParameter("JBKJXSLY_LYZL");
            String JBKJXSLY_BJBRZTLBDM = request.getParameter("JBKJXSLY_BJBRZTLB");
            String JBKJXSLY_ZJDWDM = request.getParameter("JBKJXSLY_ZJDW");
            String JBKJXSLY_SLRQ = request.getParameter("JBKJXSLY_SLRQ");
            String JBKJXSLY_ZJR = request.getParameter("JBKJXSLY_ZJR");

            JbkjxslyPojo jbkjxslyPojo = new JbkjxslyPojo();
            jbkjxslyPojo.setJBKJXSLY_LYFSDM(JBKJXSLY_LYFSDM);
            jbkjxslyPojo.setJBKJXSLY_LYFS(Parser.parseDropdownContent("JBKJXSLY_LYFS", JBKJXSLY_LYFSDM));
            jbkjxslyPojo.setJBKJXSLY_LYZLDM(JBKJXSLY_LYZLDM);
            jbkjxslyPojo.setJBKJXSLY_LYZL(Parser.parseDropdownContent("JBKJXSLY_LYZL", JBKJXSLY_LYZLDM));
            jbkjxslyPojo.setJBKJXSLY_BJBRZTLBDM(JBKJXSLY_BJBRZTLBDM);
            jbkjxslyPojo.setJBKJXSLY_BJBRZTLB(Parser.parseDropdownContent("JBKJXSLY_BJBRZTLB", JBKJXSLY_BJBRZTLBDM));
            jbkjxslyPojo.setJBKJXSLY_ZJDWDM(JBKJXSLY_ZJDWDM);
            jbkjxslyPojo.setJBKJXSLY_ZJDW(Parser.parseTreeDropdownContent("COMPANY", JBKJXSLY_ZJDWDM));
            jbkjxslyPojo.setJBKJXSLY_SLRQ(Parser.parseDate(JBKJXSLY_SLRQ)); // Deafult is: today
            jbkjxslyPojo.setJBKJXSLY_ZJR(JBKJXSLY_ZJR);
            jbkjxslyPojo.setJBKJXSLY_CLZT(AllEnums.ClueStatus.Pending.value());   // Default status: 待处理


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

            XsclPojo xsclPojo = new XsclPojo();
            xsclPojo.setCLFSDM(CLFSDM);
            if (CLFSDM != null && CLFSDM.length() > 0) {
                xsclPojo.setCLFS(Parser.parseDropdownContent("CLFS", CLFSDM));
            }

            xsclPojo.setCBR(CBR);
            xsclPojo.setZWDWDM(ZWDWDM);
            xsclPojo.setZWDW(Parser.parseDropdownContent("COMPANY", ZWDWDM));
            xsclPojo.setCSDWDM(CSDWDM);
            xsclPojo.setCSDW(Parser.parseDropdownContent("COMPANY", CSDWDM));

            if (CSRQ != null && CSRQ.length() > 0) {
                xsclPojo.setCSRQ(Parser.parseDate(CSRQ));
            }

            if (CBRCLRQ != null && CBRCLRQ.length() > 0) {
                xsclPojo.setCBRCLRQ(Parser.parseDate(CBRCLRQ));
            }

            xsclPojo.setJBZRYJ(JBZRYJ);
            xsclPojo.setCZYJ(CZYJ);
            if (CZSPRQ != null && CZSPRQ.length() > 0) {
                xsclPojo.setCZSPRQ(Parser.parseDate(CZSPRQ));
            }

            xsclPojo.setTZYJ(TZYJ);
            if (TZSPRQ != null && TZSPRQ.length() > 0) {
                xsclPojo.setTZSPRQ(Parser.parseDate(TZSPRQ));
            }

            xsclPojo.setJCZPS(JCZPS);
            if (JCZPSRQ != null && JCZPSRQ.length() > 0) {
                xsclPojo.setJCZPSRQ(Parser.parseDate(JCZPSRQ));
            }


            jbkjxslyPojo.setJBKJXSLY_JBJCGJWFWJ(JBKJXSLY_JBJCGJWFWJ);
            jbkjxslyPojo.setJBKJXSLY_JYLXDM(JBKJXSLY_JYLXDM);
            if (JBKJXSLY_JYLXDM != null && JBKJXSLY_JYLXDM.length() > 0) {
                jbkjxslyPojo.setJBKJXSLY_JYLX(Parser.parseDropdownContent("JBKJXSLY_JYLX", JBKJXSLY_JYLXDM));
            }

            jbkjxslyPojo.setJBKJXSLY_XJWH(JBKJXSLY_XJWH);
            jbkjxslyPojo.setJBKJXSLY_CLQK(JBKJXSLY_CLQK);
            jbkjxslyPojo.setJBKJXSLY_JDDD(JBKJXSLY_JDDD);
            if (JBKJXSLY_HFRQ != null && JBKJXSLY_HFRQ.length() > 0) {
                jbkjxslyPojo.setJBKJXSLY_HFRQ(Parser.parseDate(JBKJXSLY_HFRQ));
            }

            jbkjxslyPojo.setJBKJXSLY_BZ(JBKJXSLY_BZ);

            //// 举报人基本情况参数
            String JBKJXSLY_JBRXM = request.getParameter("JBKJXSLY_JBRXM1");
            String JBKJXSLY_SFSM = request.getParameter("JBKJXSLY_SFSM1");
            String JBKJXSLY_JBRSFZH = request.getParameter("JBKJXSLY_JBRSFZH1");
            String JBKJXSLY_JBRDH = request.getParameter("JBKJXSLY_JBRDH1");
            String JBKJXSLY_LXDQDM = request.getParameter("JBKJXSLY_LXDQ1");
            String JBKJXSLY_LXDQ = Parser.parseTreeDropdownContent("ZONE", JBKJXSLY_LXDQDM);
            String JBKJXSLY_JBRDWZZ = request.getParameter("JBKJXSLY_JBRDWZZ1");
            for (int i = 2; i <= 3; i++) {
                if (request.getParameter("JBKJXSLY_JBRXM" + i) != null) {
                    JBKJXSLY_JBRXM += "@#@" + request.getParameter("JBKJXSLY_JBRXM" + i);
                    JBKJXSLY_SFSM += "@#@" + request.getParameter("JBKJXSLY_SFSM" + i);
                    JBKJXSLY_JBRSFZH += "@#@" + request.getParameter("JBKJXSLY_JBRSFZH" + i);
                    JBKJXSLY_JBRDH += "@#@" + request.getParameter("JBKJXSLY_JBRDH" + i);
                    JBKJXSLY_LXDQDM += "@#@" + request.getParameter("JBKJXSLY_LXDQ" + i);
                    JBKJXSLY_LXDQ += "@#@" + Parser.parseTreeDropdownContent("ZONE", request.getParameter("JBKJXSLY_LXDQ" + i));
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
            String JBKJXSLY_AFDQ = Parser.parseTreeDropdownContent("ZONE", JBKJXSLY_AFDQDM);
            String JBKJXSLY_ZWDM = request.getParameter("JBKJXSLY_ZW1");
            String JBKJXSLY_ZW = Parser.parseDropdownContent("JBKJXSLY_ZW", JBKJXSLY_ZWDM);
            String JBKJXSLY_BJBRDWZZ = request.getParameter("JBKJXSLY_BJBRDWZZ1");
            String JBKJXSLY_SFDM = request.getParameter("JBKJXSLY_SF1");
            String JBKJXSLY_SF = Parser.parseDropdownContent("JBKJXSLY_SF", JBKJXSLY_SFDM);
            String JBKJXSLY_TSSFDM = request.getParameter("JBKJXSLY_TSSF1");
            String JBKJXSLY_TSSF = Parser.parseDropdownContent("JBKJXSLY_TSSF", JBKJXSLY_TSSFDM);
            String JBKJXSLY_ZJDM = request.getParameter("JBKJXSLY_ZJ1");
            String JBKJXSLY_ZJ = Parser.parseDropdownContent("JBKJXSLY_ZJ", JBKJXSLY_ZJDM);
            String JBKJXSLY_QTZJDM = request.getParameter("JBKJXSLY_QTZJ1");
            String JBKJXSLY_QTZJ = Parser.parseDropdownContent("JBKJXSLY_QTZJ", JBKJXSLY_QTZJDM);
            String JBKJXSLY_ZYSXXZDM = request.getParameter("JBKJXSLY_ZYSXXZ1");
            String JBKJXSLY_ZYSXXZ = Parser.parseDropdownContent("JBKJXSLY_ZYSXXZ", JBKJXSLY_ZYSXXZDM);
            String JBKJXSLY_QTSXXZDM = request.getParameter("JBKJXSLY_CYSXXZ1");
            String JBKJXSLY_CYSXXZ = Parser.parseDropdownContent("JBKJXSLY_CYSXXZ", JBKJXSLY_QTSXXZDM);
            String JBKJXSLY_SALYDM = request.getParameter("JBKJXSLY_SALY1");
            String JBKJXSLY_SALY = Parser.parseDropdownContent("JBKJXSLY_SALY", JBKJXSLY_SALYDM);
            String JBKJXSLY_SXJE = request.getParameter("JBKJXSLY_SXJE1");
            String JBKJXSLY_NRSFJT = request.getParameter("JBKJXSLY_NRSFJT1");
            String JBKJXSLY_SFXKQT = request.getParameter("JBKJXSLY_SFXKQT1");

            for (int i = 2; i <= 3; i++) {
                if (request.getParameter("JBKJXSLY_BJBRXM" + i) != null) {
                    JBKJXSLY_BJBRXM += "@#@" + request.getParameter("JBKJXSLY_BJBRXM" + i);
                    JBKJXSLY_XB += "@#@" + request.getParameter("JBKJXSLY_XB" + i);
                    JBKJXSLY_MZDM += "@#@" + request.getParameter("JBKJXSLY_MZ" + i);
                    JBKJXSLY_MZ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_MZ", request.getParameter("JBKJXSLY_MZ" + i));
                    JBKJXSLY_ZZMMDM += "@#@" + request.getParameter("JBKJXSLY_ZZMM" + i);
                    JBKJXSLY_ZZMM += "@#@" + Parser.parseDropdownContent("JBKJXSLY_ZZMM", request.getParameter("JBKJXSLY_ZZMM" + i));
                    JBKJXSLY_AFDQDM += "@#@" + request.getParameter("JBKJXSLY_AFDQ" + i);
                    JBKJXSLY_AFDQ += "@#@" + Parser.parseTreeDropdownContent("ZONE", request.getParameter("JBKJXSLY_AFDQ" + i));
                    JBKJXSLY_ZWDM += "@#@" + request.getParameter("JBKJXSLY_ZW" + i);
                    JBKJXSLY_ZW += "@#@" + Parser.parseDropdownContent("JBKJXSLY_ZW", request.getParameter("JBKJXSLY_ZW" + i));
                    JBKJXSLY_BJBRDWZZ += "@#@" + request.getParameter("JBKJXSLY_BJBRDWZZ" + i);
                    JBKJXSLY_SFDM += "@#@" + request.getParameter("JBKJXSLY_SF" + i);
                    JBKJXSLY_SF += "@#@" + Parser.parseDropdownContent("JBKJXSLY_SF", request.getParameter("JBKJXSLY_SF" + i));
                    JBKJXSLY_TSSFDM += "@#@" + request.getParameter("JBKJXSLY_TSSF" + i);
                    JBKJXSLY_TSSF += "@#@" + Parser.parseDropdownContent("JBKJXSLY_TSSF", request.getParameter("JBKJXSLY_TSSF" + i));
                    JBKJXSLY_ZJDM += "@#@" + request.getParameter("JBKJXSLY_ZJ" + i);
                    JBKJXSLY_ZJ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_ZJ", request.getParameter("JBKJXSLY_ZJ" + i));
                    JBKJXSLY_QTZJDM += "@#@" + request.getParameter("JBKJXSLY_QTZJ" + i);
                    JBKJXSLY_QTZJ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_QTZJ", request.getParameter("JBKJXSLY_QTZJ" + i));
                    JBKJXSLY_ZYSXXZDM += "@#@" + request.getParameter("JBKJXSLY_ZYSXXZ" + i);
                    JBKJXSLY_ZYSXXZ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_ZYSXXZ", request.getParameter("JBKJXSLY_ZYSXXZ" + i));
                    JBKJXSLY_QTSXXZDM += "@#@" + request.getParameter("JBKJXSLY_CYSXXZ" + i);
                    JBKJXSLY_CYSXXZ += "@#@" + Parser.parseDropdownContent("JBKJXSLY_CYSXXZ", request.getParameter("JBKJXSLY_CYSXXZ" + i));
                    JBKJXSLY_SALYDM += "@#@" + request.getParameter("JBKJXSLY_SALY" + i);
                    JBKJXSLY_SALY += "@#@" + Parser.parseDropdownContent("JBKJXSLY_SALY", request.getParameter("JBKJXSLY_SALY" + i));
                    JBKJXSLY_SXJE += "@#@" + request.getParameter("JBKJXSLY_SXJE" + i);
                    JBKJXSLY_NRSFJT += "@#@" + request.getParameter("JBKJXSLY_NRSFJT" + i);
                    JBKJXSLY_SFXKQT += "@#@" + request.getParameter("JBKJXSLY_SFXKQT" + i);
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
            jbkjxslyPojo.setJBKJXSLY_ZWDM(JBKJXSLY_ZWDM);
            jbkjxslyPojo.setJBKJXSLY_ZW(JBKJXSLY_ZW);
            jbkjxslyPojo.setJBKJXSLY_BJBRDWZZ(JBKJXSLY_BJBRDWZZ);
            jbkjxslyPojo.setJBKJXSLY_SFDM(JBKJXSLY_SFDM);
            jbkjxslyPojo.setJBKJXSLY_SF(JBKJXSLY_SF);
            jbkjxslyPojo.setJBKJXSLY_TSSFDM(JBKJXSLY_TSSFDM);
            jbkjxslyPojo.setJBKJXSLY_TSSF(JBKJXSLY_TSSF);
            jbkjxslyPojo.setJBKJXSLY_ZJDM(JBKJXSLY_ZJDM);
            jbkjxslyPojo.setJBKJXSLY_ZJ(JBKJXSLY_ZJ);
            jbkjxslyPojo.setJBKJXSLY_QTZJDM(JBKJXSLY_QTZJDM);
            jbkjxslyPojo.setJBKJXSLY_QTZJ(JBKJXSLY_QTZJ);
            jbkjxslyPojo.setJBKJXSLY_ZYSXXZDM(JBKJXSLY_ZYSXXZDM);
            jbkjxslyPojo.setJBKJXSLY_ZYSXXZ(JBKJXSLY_ZYSXXZ);
            jbkjxslyPojo.setJBKJXSLY_QTSXXZDM(JBKJXSLY_QTSXXZDM);
            jbkjxslyPojo.setJBKJXSLY_CYSXXZ(JBKJXSLY_CYSXXZ);
            jbkjxslyPojo.setJBKJXSLY_SALYDM(JBKJXSLY_SALYDM);
            jbkjxslyPojo.setJBKJXSLY_SALY(JBKJXSLY_SALY);
            jbkjxslyPojo.setJBKJXSLY_SXJE(JBKJXSLY_SXJE);
            jbkjxslyPojo.setJBKJXSLY_NRSFJT(JBKJXSLY_NRSFJT);
            jbkjxslyPojo.setJBKJXSLY_SFXKQT(JBKJXSLY_SFXKQT);

//            String JBKJXSLY_SFSBYGX = request.getParameter("JBKJXSLY_SFSBYGX");
//            String JBKJXSLY_SFKG = request.getParameter("JBKJXSLY_SFKG");
//            String JBKJXSLY_SFSS = request.getParameter("JBKJXSLY_SFSS");
//            String JBKJXSLY_SFQT = request.getParameter("JBKJXSLY_SFQT");
            String JBKJXSLY_Keywords = request.getParameter("JBKJXSLY_Keywords");
            String JBKJXSLY_SYZY = request.getParameter("JBKJXSLY_SYZY");
            String JBKJXSLY_NRZY = request.getParameter("JBKJXSLY_NRZY");
//            jbkjxslyPojo.setJBKJXSLY_SFSBYGX(JBKJXSLY_SFSBYGX);
//            jbkjxslyPojo.setJBKJXSLY_SFKG(JBKJXSLY_SFKG);
//            jbkjxslyPojo.setJBKJXSLY_SFSS(JBKJXSLY_SFSS);
//            jbkjxslyPojo.setJBKJXSLY_SFQT(JBKJXSLY_SFQT);
            jbkjxslyPojo.setJBKJXSLY_Keywords(JBKJXSLY_Keywords);
            jbkjxslyPojo.setJBKJXSLY_SYZY(JBKJXSLY_SYZY);
            jbkjxslyPojo.setJBKJXSLY_NRZY(JBKJXSLY_NRZY);


            JbkjxslyDao jbkjxslyDao = new JbkjxslyDao();
            XsclDao xsclDao = new XsclDao();
            String XH = request.getParameter("JBKJXSLY_XH");

            if (clueId > 0) {
                // Set main key value
                jbkjxslyPojo.setJBKJXSLY_ID(clueId);
                jbkjxslyPojo.setJBKJXSLY_XH(XH);
                xsclPojo.setJBKJXSLY_XH(XH);

                // Do not update attachment segment value;
                String oldFujianValue = jbkjxslyDao.getById(clueId).getJBKJXSLY_Fujian();
                if (oldFujianValue != null && oldFujianValue.length() > 0) {
                    jbkjxslyPojo.setJBKJXSLY_Fujian(oldFujianValue);
                }

                // Update the status value
                jbkjxslyPojo.setJBKJXSLY_CLZT(AllEnums.ClueStatus.Finish.value());
                jbkjxslyDao.update(jbkjxslyPojo);
                xsclDao.update(xsclPojo);
                message = "修改更新成功！";
            } else {
                jbkjxslyPojo.setJBKJXSLY_CLZT(AllEnums.ClueStatus.Finish.value());
                jbkjxslyPojo.setJBKJXSLY_XH("AutoGenerate");
                jbkjxslyDao.add(jbkjxslyPojo);

                int lastId = jbkjxslyDao.getLastAddedId();
                XH = jbkjxslyDao.getById(lastId).getJBKJXSLY_XH();
                xsclPojo.setJBKJXSLY_XH(XH);
                xsclDao.add(xsclPojo);
                message = "创建保存成功！";
            }

            xsclDao.closeAll();
            jbkjxslyDao.closeAll();
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
