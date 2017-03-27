package nutrition.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by osvaldas on 17.3.27.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "USER_PROD",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PROD_ID", referencedColumnName = "ID"))
    private List<Product> favouriteProducts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_FAVPROD",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PROD_ID", referencedColumnName = "ID"))
    private List<Product> personalProducts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Meal> perosnalMeals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getFavouriteProducts() {
        return favouriteProducts;
    }

    public void setFavouriteProducts(List<Product> favouriteProducts) {
        this.favouriteProducts = favouriteProducts;
    }

    public List<Product> getPersonalProducts() {
        return personalProducts;
    }

    public void setPersonalProducts(List<Product> personalProducts) {
        this.personalProducts = personalProducts;
    }

    public List<Meal> getPerosnalMeals() {
        return perosnalMeals;
    }

    public void setPerosnalMeals(List<Meal> perosnalMeals) {
        this.perosnalMeals = perosnalMeals;
    }
}
