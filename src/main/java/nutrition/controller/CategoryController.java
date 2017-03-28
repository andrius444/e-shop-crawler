package nutrition.controller;

import nutrition.dto.category.CategoryDTO;
import nutrition.dto.category.CategoryData;
import nutrition.dto.product.ProductDTO;
import nutrition.dto.product.ProductData;
import nutrition.exception.DataMappingException;
import nutrition.model.Category;
import nutrition.model.Product;
import nutrition.service.CategoryService;
import nutrition.utils.DTOfactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by andrius on 3/28/17.
 */

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryDTO createCategory(@Valid @RequestBody CategoryData data, BindingResult result) {
        if (result.hasErrors()) throw new DataMappingException("Errors while creating Category", result.getAllErrors());
        Category persisted = categoryService.saveCategory(data);
        return DTOfactory.makeDTO(persisted);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return DTOfactory.makeCategoryDTOlist(categories);
    }

    @GetMapping(path = "{id}")
    public CategoryDTO getCategory(@PathVariable Long id) {
        Category category = categoryService.getOneCategory(id);
        return DTOfactory.makeDTO(category);
    }

    @PatchMapping(path = "{id")
    public CategoryDTO updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryData data, BindingResult result) {
        if (result.hasErrors()) throw new DataMappingException("Errors while updating Category", result.getAllErrors());
        Category merged = categoryService.updateCategory(data, id);
        return DTOfactory.makeDTO(merged);
    }

    @DeleteMapping(path = "{id")
    public void deleteProduct(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}
