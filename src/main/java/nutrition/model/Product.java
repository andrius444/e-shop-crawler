package nutrition.model;

import nutrition.enumerator.Category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by andrius on 3/26/17.
 */

@Entity
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Category category;
    private Double kcals;
    private Double fats;
    private Double carbs;
    private Double proteins;

    public Product(String name, String category, Double kcals, Double fats, Double carbs, Double proteins) {
        this.name = name;
        this.category = this.setCategory(category.toUpperCase());
        this.kcals = kcals;
        this.fats = fats;
        this.carbs = carbs;
        this.proteins = proteins;
    }

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

    public Category setCategory(String string) {
        for (Category cat : Category.values()) {
            if (cat.getValue().equals(string)) return cat;
        }
        return null;
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

}
