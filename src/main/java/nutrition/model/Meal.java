package nutrition.model;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by osvaldas on 17.3.27.
 */

@Entity
public class Meal {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    boolean approved;

    @ElementCollection
    private Map<Product, Integer> ingredients;

    @ManyToOne(
            cascade = {},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "owner_id")
    private User owner;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Map<Product, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<Product, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
