package nutrition.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

/**
 * Created by osvaldas on 17.3.27.
 */
@Entity
public class Meal {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    private String name;
    private Map<Long, Integer> ingredients;
    boolean approved;

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

    public Map<Long, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<Long, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
