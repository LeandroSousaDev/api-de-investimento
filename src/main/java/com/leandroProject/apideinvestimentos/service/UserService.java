package com.leandroProject.apideinvestimentos.service;

import com.leandroProject.apideinvestimentos.controller.CreateUserDTO;
import com.leandroProject.apideinvestimentos.controller.UpdateUserDTO;
import com.leandroProject.apideinvestimentos.entity.User;
import com.leandroProject.apideinvestimentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDTO createUserDTO) {
       var entity = new User(UUID.randomUUID(),
                createUserDTO.username(),
                createUserDTO.email(),
                createUserDTO.password(),
                Instant.now(),
                null);

       var userSaved = this.userRepository.save(entity);

       return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {
       return this.userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listAllUsers() {
       return this.userRepository.findAll();
    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);
        var userExist = this.userRepository.existsById(id);

        if(userExist) {
            this.userRepository.deleteById(id);
        }
    }

    public void updateById(String userId, UpdateUserDTO updateUserDTO) {
        var id = UUID.fromString(userId);
        var userEntity = this.userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if(updateUserDTO.username() != null) {
                user.setUserName(updateUserDTO.username());
            }
            if (updateUserDTO.password() != null) {
                user.setPassword(updateUserDTO.password());
            }

            this.userRepository.save(user);
        }

    }
}
