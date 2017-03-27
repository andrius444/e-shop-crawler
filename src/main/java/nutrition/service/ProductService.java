package nutrition.service;

import nutrition.model.Product;
import nutrition.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by andrius on 3/27/17.
 */

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public long getCount() {
        return productRepository.count();
    }
}
