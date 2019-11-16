package meTube2.web.servlet;

import meTube2.domain.model.service.TubeServiceModel;
import meTube2.service.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/tube/details/*")
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
        String youtubeId = Arrays.stream(req.getRequestURL().toString().split("/")).reduce((f, s) -> s).orElse(null);
        TubeServiceModel tubeServiceModel = this.tubeService.findTubeByYouttubeId(youtubeId);
        req.getSession().setAttribute("currDetailsTube", tubeServiceModel);
        req.getRequestDispatcher("/jsp/details.jsp").forward(req, resp);
    }
}
