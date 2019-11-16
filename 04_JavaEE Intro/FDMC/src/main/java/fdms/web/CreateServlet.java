package fdms.web;

import fdms.domain.eninies.Cat;
import fdms.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/cats/create")
public class CreateServlet extends HttpServlet {
    private static final String CAT_CREATE_HTML_FILE_PATH = "D:\\Projects\\java\\new\\Java-Web\\04_JavaEE Intro\\FDMC\\src\\main\\resources\\views\\catCreate.html";
    private final HtmlReader htmlReader;

    @Inject
    public CreateServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(this.htmlReader.readHtmlFile(CAT_CREATE_HTML_FILE_PATH));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("cats") == null){
            req.getSession().setAttribute("cats", new HashMap<String, Cat>());
        }

        Cat cat = new Cat();
        cat.setName(req.getParameter("name"));
        cat.setBreed(req.getParameter("breed"));
        cat.setColor(req.getParameter("color"));
        cat.setAge(Integer.parseInt(req.getParameter("age")));

        ((Map<String, Cat>) req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(), cat);
        resp.sendRedirect("/cats/profile?catName=" + cat.getName());
    }
}