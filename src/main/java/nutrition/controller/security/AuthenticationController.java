package nutrition.controller.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by andrius on 3/28/17.
 */

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @PostMapping(path = "authorities")
    @ResponseBody
    public Object checkSecuredAuthorities() {
        boolean user = isContextContainsRole("ROLE_USER");
        boolean admin = isContextContainsRole("ROLE_ADMIN");

        if (user) {
            return "USER";
        } else if (admin) {
            return "ADMIN";
        } else {
            return null;
        }
    }

    //  PRIVATE

    private boolean isContextContainsRole(String role) {
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()).contains(role);
    }
}
