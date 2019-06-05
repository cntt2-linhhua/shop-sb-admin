package vn.edu.leading.shop.services;

import org.springframework.stereotype.Service;
import vn.edu.leading.shop.models.ProductModel;
import vn.edu.leading.shop.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductServicelmpl extends BaseService<ProductModel> implements ProductService {

    private final ProductRepository productRepository;

    public ProductServicelmpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductModel> findAllByCategoryModel(Long id) {
        return productRepository.findAllByCategoryModel(id);
    }

    public List<ProductModel> search(String term) {
        return baseRepository.findByAttributeContainsText("productName", term);
    }
}
