package nutrition.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by osvaldas on 17.3.27.
 */

@Entity
public class User {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;

    @OneToMany(
            mappedBy = "owner",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.EAGER
    )
    private Set<PersonalProduct> myPersonalProducts;            // FIXME MultipleBagFetchException workaround with Set

    @OneToMany(
            mappedBy = "owner",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.EAGER
    )
    private Set<Meal> myMeals;                                  // FIXME MultipleBagFetchException workaround with Set

    @ManyToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "favorite_product_id")
    private Set<Product> myFavouriteProducts;                   // FIXME MultipleBagFetchException workaround with Set

    public Long getId() {
        return id;
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
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public Set<PersonalProduct> getMyPersonalProducts() {
        return myPersonalProducts;
    }

    public void setMyPersonalProducts(Set<PersonalProduct> myPersonalProducts) {
        this.myPersonalProducts = myPersonalProducts;
    }

    public Set<Meal> getMyMeals() {
        return myMeals;
    }

    public void setMyMeals(Set<Meal> myMeals) {
        this.myMeals = myMeals;
    }

    public Set<Product> getMyFavouriteProducts() {
        return myFavouriteProducts;
    }

    public void setMyFavouriteProducts(Set<Product> myFavouriteProducts) {
        this.myFavouriteProducts = myFavouriteProducts;
    }

    // HELPER METHODS

    public User addProductToFavorites(Product product) {
        this.getMyFavouriteProducts().add(product);
        return this;
    }

    public User removeProductFromFavorites(Product product) {
        this.getMyFavouriteProducts().remove(product);
        return this;
    }
}
