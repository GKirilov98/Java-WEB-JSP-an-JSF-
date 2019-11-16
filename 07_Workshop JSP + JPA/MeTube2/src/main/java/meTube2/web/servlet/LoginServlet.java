package meTube2.web.servlet;

import meTube2.domain.model.service.UserServiceModel;
import meTube2.domain.model.servlets.UserLoginServletModel;
import meTube2.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
     private final UserService userService;
     private final ModelMapper modelMapper;

     @Inject
    public LoginServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserServiceModel userService = this.userService.findUserByNameAndPassword(username, password);
        if (userService == null){
            resp.sendRedirect("/login");
        } else {
            UserLoginServletModel userLogin = this.modelMapper
                    .map(userService, UserLoginServletModel.class);
            req.getSession().setAttribute("username", userLogin);
            resp.sendRedirect("/home");
        }
    }
}
