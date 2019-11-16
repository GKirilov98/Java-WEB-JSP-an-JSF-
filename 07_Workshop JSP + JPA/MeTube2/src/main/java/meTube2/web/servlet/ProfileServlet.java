package meTube2.web.servlet;

import meTube2.domain.model.service.UserServiceModel;
import meTube2.domain.model.servlets.UserLoginServletModel;
import meTube2.domain.model.view.UserProfileViewModel;
import meTube2.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public ProfileServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginServletModel userLogin = ((UserLoginServletModel) req.getSession().getAttribute("username"));
        UserServiceModel userServiceModel = this.userService.findUserByUsername(userLogin.getUsername(), userLogin.getPassword());
        UserProfileViewModel userProfileViewModel = this.modelMapper.map(userServiceModel, UserProfileViewModel.class);
        req.setAttribute("model", userProfileViewModel);
        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }
}
