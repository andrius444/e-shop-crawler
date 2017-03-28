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
    private Long id;

    private String email;
    private String password;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    private List<PersonalProduct> myPersonalProducts;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    private List<Meal> myMeals;

    @ManyToMany(
            mappedBy = "inUsersFavorites",
            cascade = {},
            fetch = FetchType.EAGER
    )
    private List<Product> myFavouriteProducts;

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
        this.password = password;
    }

    public List<PersonalProduct> getMyPersonalProducts() {
        return myPersonalProducts;
    }

    public void setMyPersonalProducts(List<PersonalProduct> myPersonalProducts) {
        this.myPersonalProducts = myPersonalProducts;
    }

    public List<Meal> getMyMeals() {
        return myMeals;
    }

    public void setMyMeals(List<Meal> myMeals) {
        this.myMeals = myMeals;
    }

    public List<Product> getMyFavouriteProducts() {
        return myFavouriteProducts;
    }

    public void setMyFavouriteProducts(List<Product> myFavouriteProducts) {
        this.myFavouriteProducts = myFavouriteProducts;
    }

}
