package com.example.testproject.service;

import com.example.testproject.entity.UserEntity;
import com.example.testproject.exception.CustomBadRequestException;
import com.example.testproject.exception.CustomNotFoundException;
import com.example.testproject.model.user.User;
import com.example.testproject.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    public void createUser(UserEntity user) throws CustomBadRequestException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new CustomBadRequestException(messageSource.getMessage("user.already.exists",
                    new Object[0], new Locale("en")));
        }
        userRepository.save(user);
    }

    public UserEntity getUser(Long id) throws CustomNotFoundException {
        if (userRepository.findById(id).isEmpty()) {
            throw new CustomNotFoundException(messageSource.getMessage("user.not.found",
                    new Object[0], new Locale("en")));
        }
        return userRepository.findById(id).get();
    }

    public List<User> getUsers() {
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users.stream().map(User::toModel).collect(Collectors.toList());
    }

    public void removeUser(Long id) throws CustomNotFoundException {
        if (userRepository.findById(id).isEmpty()) {
            throw new CustomNotFoundException(messageSource.getMessage("user.not.found",
                    new Object[0], new Locale("en")));
        }
        userRepository.deleteById(id);
    }
}
