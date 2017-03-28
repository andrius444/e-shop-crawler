package nutrition.service;

import nutrition.dto.category.CategoryDTO;
import nutrition.dto.category.CategoryData;
import nutrition.exception.DataMappingException;
import nutrition.model.Category;
import nutrition.repository.CategoryRepository;
import nutrition.utils.DTOfactory;
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
    public Category saveCategory(CategoryData data) {
        Category cat = mapDataToEntity(data);
        return categoryRepository.save(cat);
    }

    @Transactional
    public void saveCrawledCategories(List<String> categories) {
        categories.forEach(str -> {
            CategoryData data = new CategoryData();
            data.setName(str);

            Errors bindingResult = new BeanPropertyBindingResult(data, "categoryData");
            validator.validate(data, bindingResult);
            if (bindingResult.hasErrors())
                throw new DataMappingException("Errors while creating crawled Category", bindingResult.getAllErrors());
            saveCategory(data);
        });
    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        Iterable<Category> iter = categoryRepository.findAll();
        return new ArrayList<>(Transformer.makeCollection(iter));
    }

    @Transactional(readOnly = true)
    public Category getOneCategory(Long id) {
        Category category = categoryRepository.findOne(id);
        if (category == null) throw new EntityNotFoundException(String.format("Category not found with id [%d]", id));
        return category;
    }

    @Transactional(readOnly = true)
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }

    @Transactional
    public Category updateCategory(CategoryData data, Long id) {
        Category category = getOneCategory(id);
        if (category == null) throw new EntityNotFoundException(String.format("Category not found with id [%d]", id));
        Category merged = mapUpdatingDataToEntity(data, category);
        return categoryRepository.save(merged);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.delete(id);
    }

    // PRIVATE

    private Category mapDataToEntity(CategoryData data) {
        Category category = new Category();
        category.setName(data.getName());
        return category;
    }

    private Category mapUpdatingDataToEntity(CategoryData data, Category category) {
        category.setName(data.getName());
        return category;
    }

}
