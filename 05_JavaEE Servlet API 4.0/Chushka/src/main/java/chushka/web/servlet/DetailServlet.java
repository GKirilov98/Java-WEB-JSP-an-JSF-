package chushka.web.servlet;

import chushka.domain.models.service.ProductServiceModel;
import chushka.service.ProductService;
import chushka.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product/details")
public class DetailServlet extends HttpServlet {
    private final String DETAIL_PRODUCT_HTML_FILE_PATH = "D:\\Projects\\java\\new\\Java-Web\\05_JavaEE Servlet API 4.0\\Chushka\\src\\main\\resources\\views\\detailProduct.html";
    private final FileUtil fileUtil;
    private final ProductService productService;

    @Inject
    public DetailServlet(FileUtil fileUtil, ProductService productService) {
        this.fileUtil = fileUtil;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        ProductServiceModel productServiceModel = this.productService.finByName(name);
        String content = this.fileUtil.fileReader(DETAIL_PRODUCT_HTML_FILE_PATH)
                .replace("{{productName}}", productServiceModel.getName())
                .replace("{{productDescription}}", productServiceModel.getDescription())
                .replace("{{productType}}", productServiceModel.getType());
        resp.getWriter().println(content);
    }
}
