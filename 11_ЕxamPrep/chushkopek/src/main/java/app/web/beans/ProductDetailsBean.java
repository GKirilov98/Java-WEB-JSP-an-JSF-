package app.web.beans;

import app.domain.models.view.ProductDetailsViewModel;
import app.service.ProductService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ProductDetailsBean {
    private ProductDetailsViewModel viewModel;
    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductDetailsBean() {
    }

    @Inject
    public ProductDetailsBean(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        this.viewModel = this.modelMapper.map(this.productService.getProductById(id), ProductDetailsViewModel.class);
    }

    public ProductDetailsViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ProductDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
