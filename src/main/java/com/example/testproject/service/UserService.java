package com.example.testproject.service;

import com.example.testproject.entity.UserEntity;
import com.example.testproject.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Getter
    private final MessageSource messageSource;

    @Autowired
    public UserService(MessageSource messageSource, UserRepository userRepository) {
        this.messageSource = messageSource;
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity findUser(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public UserEntity findUser(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    public List<UserEntity> findAllUsers() {
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }
}
