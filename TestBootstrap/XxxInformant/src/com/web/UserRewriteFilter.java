package com.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class UserRewriteFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestPath = httpRequest.getRequestURI();
        if (requestPath.indexOf("/user") > 0) {
            //request.getRequestDispatcher("/redirect.html").forward(request, response);  
            httpResponse.sendRedirect("http://www.csdn.net");
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig arg0) throws ServletException {

    }

}
