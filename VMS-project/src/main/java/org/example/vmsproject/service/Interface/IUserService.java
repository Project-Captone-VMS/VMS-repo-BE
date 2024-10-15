package org.example.vmsproject.service.Interface;


import org.example.vmsproject.dto.UserDTO;
import org.example.vmsproject.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    UserDTO createUser(UserDTO user);
//    String  deleteUser(Long id);
//    User getUser(Long id);
}
