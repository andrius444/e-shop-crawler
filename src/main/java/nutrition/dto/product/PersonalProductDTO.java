package nutrition.dto.product;

import nutrition.dto.category.CategoryDTO;
import nutrition.dto.user.UserDTO;

import java.util.List;

/**
 * Created by andrius on 3/28/17.
 */

public class PersonalProductDTO {

    private Long id;
    private String name;
    private Double kcals;
    private Double fats;
    private Double carbs;
    private Double proteins;
    private UserDTO owner;
    private CategoryDTO category;
    private List<UserDTO> inUsersFavorites;

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

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public List<UserDTO> getInUsersFavorites() {
        return inUsersFavorites;
    }

    public void setInUsersFavorites(List<UserDTO> inUsersFavorites) {
        this.inUsersFavorites = inUsersFavorites;
    }

}
