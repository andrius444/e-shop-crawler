package nutrition.service;

import nutrition.model.Product;
import nutrition.model.User;
import nutrition.utils.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by andrius on 3/28/17.
 */

@Service
public class FavoritesService {

    private final UserService userService;
    private final ProductService productService;
    private final AuthHelper authHelper;

    @Autowired
    public FavoritesService(UserService userService, ProductService productService, AuthHelper authHelper) {
        this.userService = userService;
        this.productService = productService;
        this.authHelper = authHelper;
    }

    public List<Product> addProductToFavorites(Long prod_id) {
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (Objects.equals(loggedInEmail, "")) throw new UsernameNotFoundException("No one is logged in");
        User user = userService.getUserByEmail(loggedInEmail);
        Product product = productService.getOneProduct(prod_id);
        User stale = user.addProductToFavorites(product);
        return userService.updateStaleUser(stale).getMyFavouriteProducts();
    }

    public List<Product> removeProductFromFavorites(Long prod_id) {
        User user = authHelper.getLoggedInUser();
        Product product = productService.getOneProduct(prod_id);
        User stale = user.removeProductFromFavorites(product);
        return userService.updateStaleUser(stale).getMyFavouriteProducts();
    }

}
