package com.cci.assessment.service;

import com.cci.assessment.exception.RecordNotFoundException;
import com.cci.assessment.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(Long id) throws RecordNotFoundException;

    User createUser(User user);

    void updateUser(Long id, User user) throws RecordNotFoundException;
}
