package meTube2.web.servlet;

import meTube2.domain.model.service.TubeServiceModel;
import meTube2.domain.model.service.UserServiceModel;
import meTube2.domain.model.servlets.TubeUploadServletModel;
import meTube2.domain.model.servlets.UserLoginServletModel;
import meTube2.service.TubeService;
import meTube2.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/upload")
public class UploadTubeServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Inject
    public UploadTubeServlet(TubeService tubeService, ModelMapper modelMapper, UserService userService) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeUploadServletModel tubeUploadServletModel = (TubeUploadServletModel) req.getSession().getAttribute("tube");
        UserLoginServletModel userLoginServletModel = (UserLoginServletModel)
                req.getSession().getAttribute("username");
        tubeUploadServletModel.setUser(userLoginServletModel);
        TubeServiceModel tubeServiceModel = this.modelMapper
                .map(tubeUploadServletModel, TubeServiceModel.class);

        this.tubeService.saveTube(tubeServiceModel);
        resp.sendRedirect("/tube/details/" + tubeUploadServletModel.getYoutubeId());
    }
}
