package br.com.adam.todolist.controllers;

import br.com.adam.todolist.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {


    @PostMapping("/users")
    public void create(@RequestBody User user){
        log.info("User created successfully");
    }
}
