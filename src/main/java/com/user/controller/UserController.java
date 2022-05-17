package com.user.controller;

import com.user.model.Chat;
import com.user.model.Message;
import com.user.model.User;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> findAll() {

        return userService.findAll();
    }

    @GetMapping("/user")
    public User findById(@RequestParam Long id) {

        return userService.findById(id);
    }

    @PostMapping("/update")
    public User update(@RequestBody User user) {

        User userFound = userService.findById(user.getId());
        if(userFound != null) {

            return userService.save(user);
        }

        else {

            return null;
        }
    }

    @PostMapping("/friends")
    public Set<User> getFriends(long id) {

        return userService.getFriends(id);
    }

    @PostMapping("/chats")
    public Set<Chat> getChats(long id) {

        return userService.getChats(id);
    }

    @PostMapping("/messageswith")
    public Set<Message> getMessagesWith(long user_id, long chat_id) {

        return userService.getMessagesWith(user_id, chat_id);
    }
}
