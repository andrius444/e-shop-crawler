package nutrition.service;

import nutrition.model.User;
import nutrition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

/**
 * Created by andrius on 3/28/17.
 */

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE

    // READ

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) throw new EntityNotFoundException(String.format("User not found with email [%s]", email));
        return user;
    }


    // UPDATE

    @Transactional
    public User updateStaleUser(User user) {
        return userRepository.save(user);
    }

    // DELETE

    // PRIVATE METHODS

}
