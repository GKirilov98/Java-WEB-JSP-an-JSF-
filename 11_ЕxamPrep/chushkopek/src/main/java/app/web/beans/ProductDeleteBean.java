package app.web.beans;

import app.service.ProductService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ProductDeleteBean {
    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductDeleteBean() {
    }

    @Inject
    public ProductDeleteBean(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    public void deleteProduct(String id) throws Exception {
        if (this.productService.delete(id)){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
        } else {
            throw new Exception("Cannot be deleted!");
        }
    }
}
