package com.leandroProject.apideinvestimentos.service;

import com.leandroProject.apideinvestimentos.controller.DTO.AccountResponseDto;
import com.leandroProject.apideinvestimentos.controller.DTO.CreateAccountDto;
import com.leandroProject.apideinvestimentos.controller.DTO.CreateUserDto;
import com.leandroProject.apideinvestimentos.controller.DTO.UpdateUserDto;
import com.leandroProject.apideinvestimentos.entity.Account;
import com.leandroProject.apideinvestimentos.entity.BillingAddress;
import com.leandroProject.apideinvestimentos.entity.User;
import com.leandroProject.apideinvestimentos.repository.AccountRepository;
import com.leandroProject.apideinvestimentos.repository.BillingAddressRepository;
import com.leandroProject.apideinvestimentos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository,
                       AccountRepository accountRepository,
                       BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UUID createUser(CreateUserDto createUserDTO) {
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

    public void updateById(String userId, UpdateUserDto updateUserDTO) {
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void createAccount(String userId, CreateAccountDto createAccountDto) {
      var user = this.userRepository.findById(UUID.fromString(userId))
              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario não exixste"));

      var account = new Account(
              UUID.randomUUID(),
              createAccountDto.description(),
              user,
              null,
              new ArrayList<>()
      );

      var accountCreated = accountRepository.save(account);

      var billingAddress = new BillingAddress(
              accountCreated.getAccountId(),
              createAccountDto.street(),
              createAccountDto.number(),
              account
      );

      this.billingAddressRepository.save(billingAddress);

    }

    public List<AccountResponseDto> listAccounts(String userId) {
        var user = this.userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario não exixste"));

        return user.getAccounts()
                .stream()
                .map(ac -> new AccountResponseDto(ac.getAccountId().toString(), ac.getDescription()))
                .toList();
    }
}
