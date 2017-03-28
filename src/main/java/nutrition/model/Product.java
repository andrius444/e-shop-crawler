package nutrition.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by andrius on 3/28/17.
 */

@Entity
public class Product extends AbstractProduct {

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
    private Set<User> inUsersFavorites;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<User> getInUsersFavorites() {
        return inUsersFavorites;
    }

    public void setInUsersFavorites(Set<User> inUsersFavorites) {
        this.inUsersFavorites = inUsersFavorites;
    }
}
