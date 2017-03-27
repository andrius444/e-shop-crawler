package nutrition.repository;

import nutrition.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andrius on 3/27/17.
 */

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByName(String categoryName);

}
