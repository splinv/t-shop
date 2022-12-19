package com.tsmc.demo.shop.service;

import com.tsmc.demo.shop.exception.UserAlreadyExistingException;
import com.tsmc.demo.shop.repository.UserRepository;
import com.tsmc.demo.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @author shihpeng
 * @date 2019/12/6
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(String username) {

        User user = new User();
        user.setName(username);

        try {
            userRepository.insert(user);
        } catch(DuplicateKeyException ex) {
            throw new UserAlreadyExistingException("user already existing");
        }

        return userRepository.findById(user.getId());
    }

    public User getUserById(long id) {

        return userRepository.findById(id);
    }

    public User getUserByName(String name) {

        return userRepository.findByName(name);
    }
}
