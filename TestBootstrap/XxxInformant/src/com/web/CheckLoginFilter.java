package com.web;

import com.utils.DevLog;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class CheckLoginFilter implements Filter {
    private String redirectUrl = null;
    private String sessionKeyName = null;
    private List<String> notCheckUrlList = new ArrayList<String>();

    private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
        String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
        DevLog.write(uri);
        return notCheckUrlList.contains(uri);
    }

    public void destroy() {
        notCheckUrlList.clear();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if (sessionKeyName == null) {
            filterChain.doFilter(request, response);
            return;
        }
        if ((!checkRequestURIIntNotFilterList(request))
                && (session.getAttribute(sessionKeyName) == null || "".equals(session.getAttribute(sessionKeyName).toString()))) {
            String ajax = request.getHeader("x-Requested-with");
            if (ajax != null && ajax.equals("XMLHttpRequest")) {
                //访问一个不存在的页面，然后通过AJAX Error事件进行处理
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error");
                dispatcher.forward(request, response);
                return;
            } else {
                //普通HTTP请求Session超时处理
                response.sendRedirect(request.getContextPath() + redirectUrl);
            }
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        redirectUrl = filterConfig.getInitParameter("redirectUrl");
        sessionKeyName = filterConfig.getInitParameter("sessionKeyName");
        String notCheckUrlListStr = filterConfig.getInitParameter("notCheckUrlList");

        if (notCheckUrlListStr != null && notCheckUrlListStr.length() > 0) {
            StringTokenizer st = new StringTokenizer(notCheckUrlListStr, ";");
            notCheckUrlList.clear();
            while (st.hasMoreTokens()) {
                notCheckUrlList.add(st.nextToken());
            }
        }
    }

}
