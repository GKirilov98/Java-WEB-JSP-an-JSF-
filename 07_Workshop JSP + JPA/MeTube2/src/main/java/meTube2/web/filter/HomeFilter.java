//package meTube2.web.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter("/home")
//public class HomeFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        if (request.getMethod().toLowerCase().equals("get")){
//            if (request.getSession().getAttribute("username") == null){
//                response.sendRedirect("/");
//                return;
//            }
//        } else if (request.getMethod().toLowerCase().equals("post")){
//            //TODO
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
