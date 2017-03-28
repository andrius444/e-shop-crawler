package nutrition.dto.user;

import nutrition.dto.product.PersonalProductDTO;
import nutrition.dto.meal.MealDTO;
import nutrition.dto.product.ProductDTO;

import java.util.List;

/**
 * Created by andrius on 3/28/17.
 */

public class UserDTO {

    private Long id;
    private String email;
    private List<PersonalProductDTO> myPersonalProducts;
    private List<MealDTO> myMeals;
    private List<ProductDTO> myFavouriteProducts;

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

    public List<PersonalProductDTO> getMyPersonalProducts() {
        return myPersonalProducts;
    }

    public void setMyPersonalProducts(List<PersonalProductDTO> myPersonalProducts) {
        this.myPersonalProducts = myPersonalProducts;
    }

    public List<MealDTO> getMyMeals() {
        return myMeals;
    }

    public void setMyMeals(List<MealDTO> myMeals) {
        this.myMeals = myMeals;
    }

    public List<ProductDTO> getMyFavouriteProducts() {
        return myFavouriteProducts;
    }

    public void setMyFavouriteProducts(List<ProductDTO> myFavouriteProducts) {
        this.myFavouriteProducts = myFavouriteProducts;
    }

}
