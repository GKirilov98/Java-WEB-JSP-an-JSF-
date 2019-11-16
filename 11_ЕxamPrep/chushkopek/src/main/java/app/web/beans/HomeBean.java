package app.web.beans;

import app.domain.models.view.HomeProductViewModel;
import app.service.ProductService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {
    private List<HomeProductViewModel> viewModelList;
    private ProductService productService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.viewModelList = this.productService.findAll()
                .stream()
                .map(psm -> {
                    HomeProductViewModel map = this.modelMapper.map(psm, HomeProductViewModel.class);
                    if (map.getDescription().length() >50){
                        map.setDescription(map.getDescription().substring(0, 50) + "...");
                    }

                    return map;
                })
                .collect(Collectors.toList());
    }


    public List<HomeProductViewModel> getViewModelList() {
        return viewModelList;
    }

    public void setViewModelList(List<HomeProductViewModel> viewModelList) {
        this.viewModelList = viewModelList;
    }
}
