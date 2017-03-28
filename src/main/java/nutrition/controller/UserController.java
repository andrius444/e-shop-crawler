package nutrition.controller;

import nutrition.dto.user.UserDTO;
import nutrition.dto.user.UserData;
import nutrition.exception.DataMappingException;
import nutrition.model.User;
import nutrition.service.UserService;
import nutrition.utils.DTOfactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by osvaldas on 17.3.28.
 */

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @PostMapping
    public UserDTO createProduct(@Valid @RequestBody UserData data, BindingResult result) {
        if (result.hasErrors()) throw new DataMappingException("Errors while creating User", result.getAllErrors());
        User persisted = userService.saveUser(data);
        return DTOfactory.makeDTO(persisted);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return DTOfactory.makeUserDTOlist(users);
    }

    @GetMapping(path = "{id}")
    public UserDTO getUser(@PathVariable Long id) {
        User user = userService.getOneUser(id);
        return DTOfactory.makeDTO(user);
    }

    @PatchMapping(path = "{id")
    public UserDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserData data, BindingResult result) {
        if (result.hasErrors()) throw new DataMappingException("Errors while updating User", result.getAllErrors());
        User merged = userService.updateUser(data, id);
        return DTOfactory.makeDTO(merged);
    }

    @DeleteMapping(path = "{id")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
