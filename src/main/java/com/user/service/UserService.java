package com.user.service;

import com.user.model.Chat;
import com.user.model.Message;
import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public User findById(long id) {

        return checkAndGetById(id);
    }

    public User save(User user) {

        return userRepository.save(user);
    }

    public Set<User> getFriends(long id) {

        User user = checkAndGetById(id);
        if(user != null) {

            return user.getFriends();
        }

        else {

            return null;
        }
    }

    public Set<Chat> getChats(long id) {

        User user = checkAndGetById(id);
        if(user != null) {

            return user.getChats();
        }

        else {

            return null;
        }
    }

    public User checkAndGetById(long id) {

        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            return null;
        }
        else {
            return user.get();
        }
    }

    public Set<Message> getMessagesWith(long user_id, long chat_id) {

        return getChats(user_id)
                .stream()
                .filter(e -> e.getId() == chat_id)
                .findFirst()
                .orElse(new Chat())
                .getMessages();
    }
}
