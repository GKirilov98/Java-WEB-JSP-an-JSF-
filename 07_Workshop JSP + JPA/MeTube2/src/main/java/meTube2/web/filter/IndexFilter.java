//package meTube2.web.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter("/")
//public class IndexFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//
//        if (req.getMethod().toLowerCase().equals("get")) {
//            if (req.getSession().getAttribute("username") != null) {
//                resp.sendRedirect("/home");
//                return;
//            }
//        }
//
//        filterChain.doFilter(req, resp);
//    }
//}
