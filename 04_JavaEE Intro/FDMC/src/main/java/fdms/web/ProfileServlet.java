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

@WebServlet("/cats/profile")
public class ProfileServlet extends HttpServlet {
    private final HtmlReader htmlReader;
    private final String PROFILE_HTML_FILE_PATH = "D:\\Projects\\java\\new\\Java-Web\\04_JavaEE Intro\\FDMC\\src\\main\\resources\\views\\catProfile.html";
    private final String NO_SUCH_CAT_HTML_FILE_PATH = "D:\\Projects\\java\\new\\Java-Web\\04_JavaEE Intro\\FDMC\\src\\main\\resources\\views\\noSuchCat.html";

    @Inject
    public ProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = "";
        String catName = req.getParameter("catName");
        Map<String, Cat> cats = (HashMap<String, Cat>) req.getSession().getAttribute("cats");
        if (cats.containsKey(catName)) {
            Cat cat = cats.get(catName);
            content = this.htmlReader.readHtmlFile(PROFILE_HTML_FILE_PATH);
            content = content.replace("{{catName}}", catName);
            content = content.replace("{{catBreed}}", cat.getBreed());
            content = content.replace("{{catColor}}", cat.getColor());
            content = content.replace("{{catAge}}", cat.getAge().toString());
        } else {
            content = this.htmlReader.readHtmlFile(NO_SUCH_CAT_HTML_FILE_PATH);
            content = content.replace("{{catName}}", catName);
        }

        resp.getWriter().println(content);
    }
}
