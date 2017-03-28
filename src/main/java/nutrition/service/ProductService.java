package nutrition.service;

import nutrition.dto.product.ProductData;
import nutrition.enumerator.Nutritions;
import nutrition.exception.DataMappingException;
import nutrition.model.Product;
import nutrition.repository.ProductRepository;
import nutrition.utils.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.persistence.EntityNotFoundException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andrius on 3/27/17.
 */

@Service
public class ProductService {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator javaxValidator = factory.getValidator();
    private final SpringValidatorAdapter validator = new SpringValidatorAdapter(javaxValidator);

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    public Product saveProduct(ProductData data) {
        Product product = mapDataToEntity(data);
        return productRepository.save(product);
    }

    @Transactional
    public List<Product> saveAllProducts(List<Product> rawProducts) {
        Iterable<Product> iter = productRepository.save(rawProducts);
        return new ArrayList<>(Transformer.makeCollection(iter));
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        Iterable<Product> iter = productRepository.findAll();
        return new ArrayList<>(Transformer.makeCollection(iter));
    }

    @Transactional(readOnly = true)
    public Product getOneProduct(Long id) {
        Product product = productRepository.findOne(id);
        if (product == null) throw new EntityNotFoundException(String.format("Product not found with id [%d]", id));
        return product;
    }

    @Transactional(readOnly = true)
    public long getCount() {
        return productRepository.count();
    }

    @Transactional
    public Product updateProduct(ProductData data, Long id) {
        Product product = getOneProduct(id);
        if (product == null) throw new EntityNotFoundException(String.format("Product not found with id [%d]", id));
        Product merged = mapUpdatingDataToEntity(data, product);
        return productRepository.save(merged);
    }

    @Transactional(readOnly = true)
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    // HELPER METHODS

    public Product crawledDataToEntity(Map<String, Double> nutritions, String productName, String categoryName) {
        ProductData data = new ProductData();
        data.setName(productName);
        data.setKcals(nutritions.get(Nutritions.KCALS.getValue()));
        data.setFats(nutritions.get(Nutritions.FATS.getValue()));
        data.setCarbs(nutritions.get(Nutritions.CARBS.getValue()));
        data.setProteins(nutritions.get(Nutritions.PROTEINS.getValue()));
        data.setCategory(categoryService.getCategoryByName(categoryName));

        Errors bindingResult = new BeanPropertyBindingResult(data, "productData");
        validator.validate(data, bindingResult);
        if (bindingResult.hasErrors())
            throw new DataMappingException("Errors while creating crawled Product", bindingResult.getAllErrors());
        return mapDataToEntity(data);
    }

    // PRIVATE

    private Product mapDataToEntity(ProductData data) {
        Product product = new Product();
        return mapCommonFields(data, product);
    }

    private Product mapUpdatingDataToEntity(ProductData data, Product product) {
        return mapCommonFields(data, product);
    }

    private Product mapCommonFields(ProductData data, Product product) {
        product.setName(data.getName());
        product.setKcals(data.getKcals());
        product.setFats(data.getFats());
        product.setCarbs(data.getCarbs());
        product.setProteins(data.getProteins());
        product.setCategory(data.getCategory());
        return product;
    }

}
