package app.web.beans;

import app.domain.entities.Type;
import app.domain.models.binding.ProductCreateBindingModel;
import app.domain.models.service.ProductServiceModel;
import app.service.ProductService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Arrays;

@Named
@RequestScoped
public class ProductCreateBean {
    private ProductService productService;
    private ModelMapper modelMapper;
    private ProductCreateBindingModel model;
    private String[] typesValues;

    public ProductCreateBean() {
    }

    @Inject
    public ProductCreateBean(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        this.model = new ProductCreateBindingModel();
        this.typesValues = Arrays.stream(Type.values()).map(Enum::name).toArray(String[]::new);
    }

    public ProductCreateBindingModel getModel() {
        return model;
    }

    public void setModel(ProductCreateBindingModel model) {
        this.model = model;
    }

    public String[] getTypesValues() {
        return typesValues;
    }

    public void setTypesValues(String[] typesValues) {
        this.typesValues = typesValues;
    }

    public void createProduct() throws IOException {
        ProductServiceModel productServiceModel = this.modelMapper.map(this.model, ProductServiceModel.class);
        if (!this.productService.createProduct(productServiceModel)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/products/create");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
        }
    }
}
