package chushka.service;

import chushka.domain.models.service.ProductServiceModel;
import chushka.domain.models.view.ProductViewModels;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductServiceModel productServiceModel);
    List<ProductViewModels> allProductsName();

    ProductServiceModel finByName(String name);
}
