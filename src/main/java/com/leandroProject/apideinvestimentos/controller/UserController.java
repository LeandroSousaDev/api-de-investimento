package com.leandroProject.apideinvestimentos.controller;

import com.leandroProject.apideinvestimentos.controller.DTO.AccountResponseDto;
import com.leandroProject.apideinvestimentos.controller.DTO.CreateAccountDto;
import com.leandroProject.apideinvestimentos.controller.DTO.CreateUserDto;
import com.leandroProject.apideinvestimentos.controller.DTO.UpdateUserDto;
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
    public ResponseEntity<User> create(@RequestBody CreateUserDto createUserDto) {
        var userId = this.userService.createUser(createUserDto);
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
    public ResponseEntity<Void> update(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto) {
        this.userService.updateById(userId, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") String userId) {
        this.userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////


    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> createAccount(@PathVariable("userId") String userId,
                                            @RequestBody CreateAccountDto createAccountDto) {
        userService.createAccount(userId, createAccountDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>> listAccount(@PathVariable("userId") String userId) {
        var accounts = userService.listAccounts(userId);
        return ResponseEntity.ok(accounts);
    }

}
