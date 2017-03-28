package nutrition.service;

import nutrition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by osvaldas on 17.3.28.
 */
@Service
public class UserService {

    @Autowired
    private final UserRepository repository;


}
