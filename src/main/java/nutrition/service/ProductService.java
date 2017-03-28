package nutrition.service;

import nutrition.dto.product.ProductDTO;
import nutrition.dto.product.ProductData;
import nutrition.enumerator.Nutritions;
import nutrition.model.Product;
import nutrition.repository.ProductRepository;
import nutrition.utils.DTOfactory;
import nutrition.utils.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

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
    public ProductDTO saveProduct(ProductData data) {
        Product product = mapDataToEntity(data);
        Product persisted = productRepository.save(product);
        return DTOfactory.makeDTO(persisted);
    }

    @Transactional
    public List<ProductDTO> saveAll(List<Product> rawProducts) {
        Iterable<Product> iter = productRepository.save(rawProducts);
        List<Product> persisted = new ArrayList<>(Transformer.makeCollection(iter));
        return DTOfactory.makeDTOlist(persisted);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        Iterable<Product> iter = productRepository.findAll();
        List<Product> persisted = new ArrayList<>(Transformer.makeCollection(iter));
        return DTOfactory.makeDTOlist(persisted);
    }

    @Transactional(readOnly = true)
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public long getCount() {
        return productRepository.count();
    }

    // HELPER METHOD

    public Product makeRawProduct(Map<String, Double> nutritions, String productName, String categoryName) {
        ProductData data = new ProductData();
        data.setName(productName);
        data.setKcals(nutritions.get(Nutritions.KCALS.getValue()));
        data.setFats(nutritions.get(Nutritions.FATS.getValue()));
        data.setCarbs(nutritions.get(Nutritions.CARBS.getValue()));
        data.setProteins(nutritions.get(Nutritions.PROTEINS.getValue()));
        data.setCategory(categoryService.findCategoryByName(categoryName));

        // FIXME if errors throw Exception
        Errors bindingResult = new BeanPropertyBindingResult(data, "candidateData");
        validator.validate(data, bindingResult);

        return mapDataToEntity(data);
    }

    // PRIVATE

    private Product mapDataToEntity(ProductData data) {
        Product prod = new Product();
        prod.setName(data.getName());
        prod.setKcals(data.getKcals());
        prod.setFats(data.getFats());
        prod.setCarbs(data.getCarbs());
        prod.setProteins(data.getProteins());
        prod.setCategory(data.getCategory());
        return prod;
    }
}
