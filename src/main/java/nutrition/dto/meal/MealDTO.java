package nutrition.dto.meal;

import nutrition.dto.user.UserDTO;
import nutrition.dto.product.ProductDTO;

import java.util.Map;

/**
 * Created by andrius on 3/28/17.
 */

public class MealDTO {

    private Long id;
    private String name;
    private Map<ProductDTO, Integer> ingredients;
    private UserDTO owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<ProductDTO, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<ProductDTO, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

}
