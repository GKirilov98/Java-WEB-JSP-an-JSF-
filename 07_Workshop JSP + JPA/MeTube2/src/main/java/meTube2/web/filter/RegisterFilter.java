package meTube2.web.filter;

import meTube2.domain.model.servlets.UserRegisterServletModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/register")
public class RegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toLowerCase().equals("post")){
            if (req.getParameter("password").equals(req.getParameter("confirmPassword"))){
                UserRegisterServletModel userRegisterServletModel = new UserRegisterServletModel();
                userRegisterServletModel.setUsername(req.getParameter("username"));
                userRegisterServletModel.setPassword(req.getParameter("password"));
                userRegisterServletModel.setEmail(req.getParameter("email"));

                req.getSession().setAttribute("user", userRegisterServletModel);
            }
        }

        filterChain.doFilter(req, resp);
    }
}
