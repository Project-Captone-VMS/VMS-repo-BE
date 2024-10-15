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
import java.util.Optional;


@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
//
//    @Override
//    public String deleteUser(Long id) {
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent()) {
//            driverRepository.deleteByUserUserId(id);
//            userRepository.delete(user.get());
//            return "User deleted successfully";
//        }else{
//            return "User not found";
//        }
//    }

//    @Override
//    public User getUser(Long id) {
//        return userRepository.findById(id).orElse(null);
//    }

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
            driver.setIsDeleted(false);
            driver.setDriverId(savedUser.getUserId());
            driver.setFirstName(savedUser.getFirstName());
            driver.setLastName(savedUser.getLastName());
            driverRepository.save(driver);
        }

        return userDTO;
    }
}

