package web.servlet;

import domain.model.view.AllTubesViewModel;
import sevice.TubeService;
import utl.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/tubes/all")
public class AllTubesServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public AllTubesServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AllTubesViewModel> allTubes = this.tubeService.findAll()
                .stream()
                .map(tsm -> this.modelMapper.map(tsm, AllTubesViewModel.class))
                .collect(Collectors.toList());
        req.setAttribute("allTubes", allTubes);
        req.getRequestDispatcher("/jsp/all-tubes.jsp").forward(req, resp);
    }
}
