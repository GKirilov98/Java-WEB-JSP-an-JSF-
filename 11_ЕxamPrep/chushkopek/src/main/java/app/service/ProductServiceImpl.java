package app.service;

import app.domain.entities.Product;
import app.domain.entities.Type;
import app.domain.models.service.ProductServiceModel;
import app.repository.ProductRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setType(Type.valueOf(productServiceModel.getType()));
        return this.productRepository.save(product) != null;
    }

    @Override
    public List<ProductServiceModel> findAll() {
        return this.productRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel getProductById(String id) {
        Product user = this.productRepository.findById(id);
        if (user == null) {
            return null;
        }

        return this.modelMapper.map(user, ProductServiceModel.class);
    }

    @Override
    public boolean delete(String id) {

        return productRepository.delete(id);
    }
}
