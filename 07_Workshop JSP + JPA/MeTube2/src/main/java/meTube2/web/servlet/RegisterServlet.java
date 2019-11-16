package meTube2.web.servlet;

import meTube2.domain.model.service.UserServiceModel;
import meTube2.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public RegisterServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            UserServiceModel username = this.modelMapper.map(
                    req.getSession().getAttribute("user"),
                    UserServiceModel.class);

            this.userService.saveUser(username);
            resp.sendRedirect("/login");
        } catch (Exception e){
            resp.sendRedirect("/register");
        }
    }
}
