package nutrition.service;

import nutrition.dto.category.CategoryDTO;
import nutrition.dto.category.CategoryData;
import nutrition.model.Category;
import nutrition.repository.CategoryRepository;
import nutrition.utils.DTOfactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

/**
 * Created by andrius on 3/27/17.
 */

@Service
public class CategoryService {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator javaxValidator = factory.getValidator();
    private final SpringValidatorAdapter validator = new SpringValidatorAdapter(javaxValidator);

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryDTO saveCategory(CategoryData data) {
        Category cat = mapDataToEntity(data);
        Category persisted = categoryRepository.save(cat);
        return DTOfactory.makeDTO(persisted);
    }

    @Transactional
    public void saveCrawledCategories(List<String> categories) {
        categories.forEach(str -> {
            CategoryData data = new CategoryData();
            data.setName(str);

            // FIXME if errors throw Exception
            Errors bindingResult = new BeanPropertyBindingResult(data, "candidateData");
            validator.validate(data, bindingResult);
            saveCategory(data);
        });
    }

    @Transactional(readOnly = true)
    public Category findCategory(Long id) {
        return categoryRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }

    // PRIVATE

    private Category mapDataToEntity(CategoryData data) {
        Category cat = new Category();
        cat.setName(data.getName());
        return cat;
    }

}
