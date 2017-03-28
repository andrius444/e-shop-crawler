package nutrition.validation;

import nutrition.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by andrius on 3/28/17.
 */

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {

   private final ProductService productService;

   @Autowired
   public UniqueProductNameValidator(ProductService productService) {
      this.productService = productService;
   }

   public void initialize(UniqueProductName constraint) { }

   public boolean isValid(String value, ConstraintValidatorContext context) {
      boolean isValid;

      if (value == null) return true;
      isValid = productService.getAllProducts()
              .stream()
              .noneMatch(prod -> prod.getName().toLowerCase().equals(value.toLowerCase()));

      if (!isValid) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(String.format("There is already a Product with name <%s>", value))
                 .addConstraintViolation();
         return false;
      }
      return true;
   }
}
