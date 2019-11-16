package web.filter;

import domain.model.create.TubeCreateModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getMethod().toLowerCase().equals("post")) {
            TubeCreateModel tubeCreateModel = new TubeCreateModel();
            tubeCreateModel.setName(req.getParameter("name"));
            tubeCreateModel.setDescription(req.getParameter("description"));
            tubeCreateModel.setYouTubeLink(req.getParameter("youTubeLink"));
            tubeCreateModel.setUploader(req.getParameter("uploader"));
            req.setAttribute("tubeCreateModel", tubeCreateModel);
        }

        filterChain.doFilter(req, res);
    }
}
