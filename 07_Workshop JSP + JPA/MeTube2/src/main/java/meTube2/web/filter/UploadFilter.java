package meTube2.web.filter;

import meTube2.domain.model.servlets.TubeUploadServletModel;
import meTube2.domain.model.servlets.UserLoginServletModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tube/upload")
public class UploadFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toLowerCase().equals("post")) {
            TubeUploadServletModel tubeUploadServletModel = new TubeUploadServletModel();
            tubeUploadServletModel.setTitle(req.getParameter("title"));
            tubeUploadServletModel.setAuthor(req.getParameter("author"));
            tubeUploadServletModel.setYoutubeId(req.getParameter("youtube-link"));
            tubeUploadServletModel.setDescription(req.getParameter("description"));

            req.getSession().setAttribute("tube", tubeUploadServletModel);
        }

        filterChain.doFilter(req, resp);
    }
}
