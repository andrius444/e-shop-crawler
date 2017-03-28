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
public abstract class AbstractProduct {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double kcals;
    private Double fats;
    private Double carbs;
    private Double proteins;

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this != o) return false;
        if (o == null || getClass() != o.getClass()) return false;
        return name.equals(((AbstractProduct)o).getName());
    }

    @Override
    public int hashCode() {
        int result = (id == null) ? 128 : id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + kcals.hashCode();
        result = 31 * result + fats.hashCode();
        result = 31 * result + carbs.hashCode();
        result = 31 * result + proteins.hashCode();
        return result;
    }
}
