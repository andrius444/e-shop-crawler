package nutrition.utils;

import nutrition.dto.category.CategoryDTO;
import nutrition.dto.product.ProductDTO;
import nutrition.model.Category;
import nutrition.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 3/27/17.
 */

public class DTOfactory {

    public static ProductDTO makeDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setKcals(entity.getKcals());
        dto.setFats(entity.getFats());
        dto.setCarbs(entity.getCarbs());
        dto.setProteins(entity.getProteins());
        return dto;
    }

    public static CategoryDTO makeDTO(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        List<Product> products = entity.getProducts();
        List<ProductDTO> prodDTOs = (products == null) ? new ArrayList<>() : makeDTOlist(products);
        dto.setProducts(prodDTOs);
        return dto;
    }

    public static List<ProductDTO> makeDTOlist(List<Product> products) {
        return products.stream().map(DTOfactory::makeDTO).collect(Collectors.toList());
    }

}
