package nutrition.controller;

import nutrition.dto.product.ProductDTO;
import nutrition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by andrius on 3/28/17.
 */

@RestController
@RequestMapping("api/favorites")
public class FavoriteController {

    @Autowired
    private final UserService userService;

    @PostMapping(path = "add/product/{prod_id}")
    public List<ProductDTO> addProductToFavorites(@PathVariable Long prod_id) {
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        return null;
    }

}
