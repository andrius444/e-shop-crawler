package nutrition.model;

import nutrition.validation.UniqueProductName;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

/**
 * Created by andrius on 3/26/17.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double kcals;
    private Double fats;
    private Double carbs;
    private Double proteins;

    @ManyToOne(
            cascade = {},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(
            cascade = {},
            fetch = FetchType.EAGER,
            mappedBy = "myFavouriteProducts"
    )
    private List<User> inUsersFavorites;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public List<User> getInUsersFavorites() {
        return inUsersFavorites;
    }

    public void setInUsersFavorites(List<User> inUsersFavorites) {
        this.inUsersFavorites = inUsersFavorites;
    }

    @Override
    public boolean equals(Object o) {
        if (this != o) return false;
        if (o == null || getClass() != o.getClass()) return false;
        return name.equals(((Product)o).getName());
    }

    @Override
    public int hashCode() {
        int result = (id == null) ? 128 : id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + kcals.hashCode();
        result = 31 * result + fats.hashCode();
        result = 31 * result + carbs.hashCode();
        result = 31 * result + proteins.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }
}
