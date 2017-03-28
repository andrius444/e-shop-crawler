package nutrition.service;

import nutrition.dto.user.UserData;
import nutrition.model.User;
import nutrition.repository.UserRepository;
import nutrition.utils.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public User saveUser(UserData data) {
        User user = mapDataToEntity(data);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        Iterable<User> iter = userRepository.findAll();
        return new ArrayList<>(Transformer.makeCollection(iter));
    }

    @Transactional(readOnly = true)
    public User getOneUser(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) throw new EntityNotFoundException(String.format("User not found with id [%d]", id));
        return user;
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) throw new EntityNotFoundException(String.format("User not found with email [%s]", email));
        return user;
    }

    @Transactional
    public User updateUser(UserData data, Long id) {
        User user = userRepository.findOne(id);
        if (user == null) throw new EntityNotFoundException(String.format("User not found with id [%d]", id));
        User merged = mapUpdatingDataToEntity(data, user);
        return userRepository.save(merged);
    }

    @Transactional
    public User updateStaleUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    // PRIVATE

    private User mapDataToEntity(UserData data) {
        User user = new User();
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        return user;
    }

    private User mapUpdatingDataToEntity(UserData data, User user) {
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        return user;
    }

}
