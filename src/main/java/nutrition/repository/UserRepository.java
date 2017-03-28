package nutrition.repository;

import nutrition.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by osvaldas on 17.3.28.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> { }
