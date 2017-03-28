package nutrition.controller;

import nutrition.dto.product.ProductDTO;
import nutrition.dto.product.ProductData;
import nutrition.exception.DataMappingException;
import nutrition.model.Product;
import nutrition.service.ProductService;
import nutrition.utils.DTOfactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by andrius on 3/28/17.
 */

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDTO createProduct(@Valid @RequestBody ProductData data, BindingResult result) {
        if (result.hasErrors()) throw new DataMappingException("Errors while creating Product", result.getAllErrors());
        Product persisted = productService.saveProduct(data);
        return DTOfactory.makeDTO(persisted);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return DTOfactory.makeProductDTOlist(products);
    }

    @GetMapping(path = "{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        Product product = productService.getOneProduct(id);
        return DTOfactory.makeDTO(product);
    }

    @PatchMapping(path = "{id")
    public ProductDTO updateProduct(@PathVariable Long id, @Valid @RequestBody ProductData data, BindingResult result) {
        if (result.hasErrors()) throw new DataMappingException("Errors while updating Product", result.getAllErrors());
        Product merged = productService.updateProduct(data, id);
        return DTOfactory.makeDTO(merged);
    }

    @DeleteMapping(path = "{id")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
