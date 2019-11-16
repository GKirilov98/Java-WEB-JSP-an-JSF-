package app.service;

import app.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {
    boolean createProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAll();

    ProductServiceModel getProductById(String id);

    boolean delete(String id);
}
