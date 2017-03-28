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
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser (@Valid
                            @RequestBody
                            User newUser){
        return service.createUser(newUser);
    }

}
