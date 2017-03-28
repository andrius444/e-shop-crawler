package nutrition.validation;

import nutrition.service.CategoryService;
import nutrition.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by andrius on 3/28/17.
 */

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {

   private final CategoryService categoryService;

   @Autowired
   public UniqueCategoryNameValidator(CategoryService categoryService) {
      this.categoryService = categoryService;
   }

   public void initialize(UniqueCategoryName constraint) { }

   public boolean isValid(String value, ConstraintValidatorContext context) {
      boolean isValid;

      if (value == null) return true;
      isValid = categoryService.getAllCategories()
                               .stream()
                               .noneMatch(prod -> prod.getName().toLowerCase().equals(value.toLowerCase()));

      if (!isValid) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(String.format("There is already a Category with name <%s>", value))
                 .addConstraintViolation();
         return false;
      }
      return true;
   }
}
