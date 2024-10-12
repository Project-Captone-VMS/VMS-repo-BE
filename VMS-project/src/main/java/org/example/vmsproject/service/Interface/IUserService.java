package org.example.vmsproject.service.Interface;


import org.example.vmsproject.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO user);
}
