package org.example.vmsproject.service.Implement;


import org.example.vmsproject.dto.UserDTO;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.User;
import org.example.vmsproject.repository.DriverRepository;
import org.example.vmsproject.repository.UserRepository;
import org.example.vmsproject.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;


    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getUserId(), user.getUsername(),
                user.getFirstName(), user.getLastName(), user.getPassword(), user.getRole().getRoleId()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(userDTO.getRole());
        User savedUser = userRepository.save(user);
        userDTO.setUserId(savedUser.getUserId());

        if (savedUser.getRole().getRoleId() == 2) {
            Driver driver = new Driver();
            driver.setDriverId(savedUser.getUserId());
            driver.setFirstName(savedUser.getFirstName());
            driver.setLastName(savedUser.getLastName());
            driverRepository.save(driver);
        }

        return userDTO;
    }
}

