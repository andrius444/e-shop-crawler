package nutrition.dto.product;

import nutrition.model.Category;
import nutrition.validation.UniqueProductName;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by andrius on 3/27/17.
 */

public class ProductData {

    @NotNull(message = "Product name must be present")
    @NotBlank(message = "Product name must not be blank")
    //@UniqueProductName(message = "There is already Product with such name")
    private String name;

    @NotNull(message = "K calories name must be present")
    @DecimalMin(value = "0.0", message = "Kcals must not be negative")
    private Double kcals;

    @NotNull(message = "Fats must be present")
    @DecimalMin(value = "0.0", message = "Fats must not be negative")
    private Double fats;

    @NotNull(message = "Carbs must be present")
    @DecimalMin(value = "0.0", message = "Carbs must not be negative")
    private Double carbs;

    @NotNull(message = "Proteins must be present")
    @DecimalMin(value = "0.0", message = "Proteins must not be negative")
    private Double proteins;

    @NotNull(message = "Category must be present")
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getKcals() {
        return kcals;
    }

    public void setKcals(Double kcals) {
        this.kcals = kcals;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
