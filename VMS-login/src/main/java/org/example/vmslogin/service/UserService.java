package org.example.vmslogin.service;

import org.example.vmslogin.dto.request.CreateUserRequest;
import org.example.vmslogin.dto.request.UpdateUserRequest;
import org.example.vmslogin.dto.response.UserResponse;
import org.example.vmslogin.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(CreateUserRequest request);
    List<UserResponse>findAllUser();
    void deleteUser(String id);
    UserResponse getUserDetail(String id);
    UserResponse updateUser(String userId, UpdateUserRequest request);
    UserResponse getMyInfo();
}
