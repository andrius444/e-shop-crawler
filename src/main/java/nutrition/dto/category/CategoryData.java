package nutrition.dto.category;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by andrius on 3/27/17.
 */

public class CategoryData {

    @NotNull(message = "Category name must be present")
    @NotBlank(message = "Category name must not be blank")
    @Size(min = 6, message = "Category name must be min 6 chars")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
