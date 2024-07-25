package com.leandroProject.apideinvestimentos.controller;

import com.leandroProject.apideinvestimentos.controller.DTO.CreateAccountDto;
import com.leandroProject.apideinvestimentos.controller.DTO.CreateUserDTO;
import com.leandroProject.apideinvestimentos.controller.DTO.UpdateUserDTO;
import com.leandroProject.apideinvestimentos.entity.User;
import com.leandroProject.apideinvestimentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody CreateUserDTO createUserDTO) {
        var userId = this.userService.createUser(createUserDTO);
        return ResponseEntity.created(URI.create("/users/" + userId.toString())).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> readAll() {
        var users = this.userService.listAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> readOne(@PathVariable("userId") String userId) {
        var user = this.userService.getUserById(userId);

        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> update(@PathVariable("userId") String userId, @RequestBody UpdateUserDTO updateUserDTO) {
        this.userService.updateById(userId, updateUserDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") String userId) {
        this.userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////


    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> accountUser(@PathVariable("userId") String userId,
                                            @RequestBody CreateAccountDto createAccountDto) {
        userService.createAccount(userId, createAccountDto);
        return ResponseEntity.ok().build();
    }

}
