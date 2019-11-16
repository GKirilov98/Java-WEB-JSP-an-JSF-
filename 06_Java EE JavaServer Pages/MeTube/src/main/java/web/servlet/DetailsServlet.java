package web.servlet;

import domain.model.service.TubeServiceModel;
import domain.model.view.TubeViewModel;
import sevice.TubeService;
import utl.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/details")
public class DetailsServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public DetailsServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name").replace("%20", " ");
        TubeServiceModel tsm = this.tubeService.findTube(name);
        TubeViewModel tvm = this.modelMapper
                .map(tsm, TubeViewModel.class);
        req.setAttribute("tubeDetailsViewModel", tvm);
        req.getRequestDispatcher("/jsp/details-tube.jsp").forward(req, resp);
    }
}
