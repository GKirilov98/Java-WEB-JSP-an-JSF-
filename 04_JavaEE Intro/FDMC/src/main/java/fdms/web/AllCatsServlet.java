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
import java.util.Set;

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {
    private final HtmlReader htmlReader;
    private final String CAT_ALL_HTML_FILE_PATH = "D:\\Projects\\java\\new\\Java-Web\\04_JavaEE Intro\\FDMC\\src\\main\\resources\\views\\catAll.html";

    @Inject
    public AllCatsServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder links = new StringBuilder();
        String content = this.htmlReader.readHtmlFile(CAT_ALL_HTML_FILE_PATH);

        if (req.getSession().getAttribute("cats") == null){
            links.append("There are no Cats. <a href=\"/cats/create\">Create some!</a>");
        } else {
            Set<String> cats = ((HashMap<String, Cat>) req.getSession().getAttribute("cats")).keySet();
            for (String cat : cats) {
                links.append(String.format("<a href=\"/cats/profile?catName=%s\">%s</a><br>", cat, cat ));
            }
        }

        content = content.replace("{{allCats}}", links);
        resp.getWriter().println(content);
    }
}
