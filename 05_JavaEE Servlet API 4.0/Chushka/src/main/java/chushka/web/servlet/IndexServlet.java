package chushka.web.servlet;

import chushka.domain.models.view.ProductViewModels;
import chushka.service.ProductService;
import chushka.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private final String  INDEX_FILE_PATH  =
            "D:\\Projects\\java\\new\\Java-Web\\05_JavaEE Servlet API 4.0\\Chushka\\src\\main\\resources\\views\\index.html";
    private final ProductService productService;
    private final FileUtil fileUtil;

    @Inject
    public IndexServlet(ProductService productService, FileUtil fileUtil) {
        this.productService = productService;
        this.fileUtil = fileUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String content = this.fileUtil
                .fileReader(INDEX_FILE_PATH)
                .replace("{{listItems}}", this.joinedListItems());
        resp.getWriter().println(content);
    }

    private String joinedListItems() {
       return this.productService.allProductsName()
               .stream()
               .map(pvm -> String.format("<li><a href=\"/product/details?name=%s\">%s</a></li>", pvm.getName(), pvm.getName()))
               .collect(Collectors.joining("\r\n"));
    }
}
