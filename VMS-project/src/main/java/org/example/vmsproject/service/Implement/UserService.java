package org.example.vmsproject.service.Implement;

import org.example.vmsproject.entity.User;
import org.example.vmsproject.repository.UserRepository;
import org.example.vmsproject.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
