package nutrition.controller;

import nutrition.dto.product.ProductDTO;
import nutrition.model.Product;
import nutrition.model.User;
import nutrition.service.FavoritesService;
import nutrition.utils.DTOfactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by andrius on 3/28/17.
 */

@RestController
@RequestMapping("api/favorites")
public class FavoritesController {

    private final FavoritesService favoritesService;

    @Autowired
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @PostMapping(path = "add/{prod_id}")
    public List<ProductDTO> addProductToFavorites(@PathVariable Long prod_id) {
        List<Product> favorites = favoritesService.addProductToFavorites(prod_id);
        return DTOfactory.makeProductDTOlist(favorites);
    }

    @DeleteMapping(path = "remove/{prod_id}")
    public List<ProductDTO> removeProductFromFavorites(@PathVariable Long prod_id) {
        List<Product> favorites = favoritesService.removeProductFromFavorites(prod_id);
        return DTOfactory.makeProductDTOlist(favorites);
    }

}
