package com.cci.assessment.service.impl;

import com.cci.assessment.exception.RecordNotFoundException;
import com.cci.assessment.repository.UserRepository;
import com.cci.assessment.model.User;
import com.cci.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getUserById(Long id) throws RecordNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) return user.get();
        else throw new RecordNotFoundException("");

    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) throws RecordNotFoundException {
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isPresent()){
            existingUser.get().setLastName(user.getLastName());
            existingUser.get().setFirstName(user.getFirstName());
            userRepository.save(existingUser.get());
        }else {
            throw new RecordNotFoundException("No user has been updated. Record not found");
        }

    }
}
