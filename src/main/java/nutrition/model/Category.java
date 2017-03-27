package nutrition.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by andrius on 3/27/17.
 */

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {},
            mappedBy = "category"
    )
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (name.equals(category.name)) return true;

        if (!id.equals(category.id)) return false;
        if (!name.equals(category.name)) return false;
        return products != null ? products.equals(category.products) : category.products == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }
}
