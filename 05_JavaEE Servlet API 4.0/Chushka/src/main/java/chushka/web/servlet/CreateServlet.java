package chushka.web.servlet;

import chushka.domain.entity.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.service.ProductService;
import chushka.util.FileUtil;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/products/create")
public class CreateServlet extends HttpServlet {
    private final String CREATE_HTML_FILE_PATH =
            "D:\\Projects\\java\\new\\Java-Web\\05_JavaEE Servlet API 4.0\\Chushka\\src\\main\\resources\\views\\createProduct.html";
    private final ProductService productService;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;

    @Inject
    public CreateServlet(ProductService productService, FileUtil fileUtil, ModelMapper modelMapper) {
        this.productService = productService;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typesOption = Arrays.stream(Type.values())
                .map(type -> String.format("<option>%s</option>", type.name()))
                .collect(Collectors.joining("\n"));
        String content = this.fileUtil.fileReader(CREATE_HTML_FILE_PATH)
                .replace("{{typeOptions}}", typesOption );
        resp.getWriter().println(content);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(req.getParameter("name"));
        productServiceModel.setDescription(req.getParameter("description"));
        productServiceModel.setType(req.getParameter("type"));

       this.productService.saveProduct(productServiceModel);
       resp.sendRedirect("/");
    }
}
