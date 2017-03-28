package nutrition.controller;

import nutrition.model.User;
import nutrition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by osvaldas on 17.3.28.
 */
@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /*@PostMapping
    public User createUser (@Valid @RequestBody UserData data, BindingResult result) {
        return service.createUser(newUser);
    }*/

}
