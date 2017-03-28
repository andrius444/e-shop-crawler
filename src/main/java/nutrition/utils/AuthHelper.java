package nutrition.utils;

import nutrition.model.User;
import nutrition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by andrius on 3/28/17.
 */

@Component
public class AuthHelper {

    private final UserService userService;

    @Autowired
    public AuthHelper(UserService userService) {
        this.userService = userService;
    }

    public User getLoggedInUser() {
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (Objects.equals(loggedInEmail, "")) throw new UsernameNotFoundException("No one is logged in");
        return userService.getUserByEmail(loggedInEmail);
    }

}
