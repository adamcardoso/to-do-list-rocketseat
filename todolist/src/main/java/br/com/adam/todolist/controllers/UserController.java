package br.com.adam.todolist.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.adam.todolist.entities.user.User;
import br.com.adam.todolist.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody User userEntity){
        var user = this.userRepository.findByUsername(userEntity.getUsername());

        if (Objects.nonNull(user)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado");
        }

        var passwordHashred = BCrypt.withDefaults()
                .hashToString(12, userEntity.getPassword().toCharArray());

        userEntity.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
