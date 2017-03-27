package nutrition.repository;

import nutrition.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andrius on 3/26/17.
 */

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> { }
