package com.team.house.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("别蹭了，能不能男人点？");
        //强转
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获取网址
        String uri = request.getRequestURI();
        System.out.println(uri);
        String path = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println(path);
        //放行
        if (path.equals("login.jsp") || path.equals("regs.jsp")
                || path.equals("checkname") || path.equals("getSmsCode")
                || path.equals("searchPage")|| path.equals("details.jsp")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
