package web.servlet;

import domain.model.service.TubeServiceModel;
import domain.model.create.TubeCreateModel;
import sevice.TubeService;
import utl.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class CreateServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public CreateServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateModel tubeCrateModel = (TubeCreateModel) req.getAttribute("tubeCreateModel");
        this.tubeService.saveTube(this.modelMapper.map(tubeCrateModel, TubeServiceModel.class));
        resp.sendRedirect("/tubes/details?name=" + tubeCrateModel.getName());
    }
}
