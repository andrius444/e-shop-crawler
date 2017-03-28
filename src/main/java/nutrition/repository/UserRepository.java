package nutrition.repository;

import nutrition.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andrius on 3/28/17.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findUserByEmail(String email);

}