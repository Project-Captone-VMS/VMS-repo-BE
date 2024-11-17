package org.example.vmsproject.service;



import org.example.vmsproject.dto.request.CreateUserRequest;
import org.example.vmsproject.dto.request.UpdateUserRequest;
import org.example.vmsproject.dto.response.UserResponse;
import org.example.vmsproject.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(CreateUserRequest request);
    List<UserResponse>findAllUser();
    void deleteUser(String id);
    UserResponse getUserDetail(String id);
    UserResponse updateUser(String userId, UpdateUserRequest request);
    UserResponse getMyInfo();
    UserResponse getUserByUserName(String userName);
}
