package org.example.vmslogin.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.vmslogin.dto.request.CreateUserRequest;
import org.example.vmslogin.dto.request.UpdateUserRequest;
import org.example.vmslogin.dto.response.UserResponse;
import org.example.vmslogin.entity.ERole;
import org.example.vmslogin.entity.Role;
import org.example.vmslogin.entity.User;
import org.example.vmslogin.exception.AppException;
import org.example.vmslogin.exception.ErrorCode;
import org.example.vmslogin.mapper.UserMapper;
import org.example.vmslogin.repository.RoleRepository;
import org.example.vmslogin.repository.UserRepository;
import org.example.vmslogin.service.UserService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public User saveUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }


//    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasAuthority('READ_DATA')")
    @Override
    public List<UserResponse> findAllUser() {
        log.info("In method get Users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @PostAuthorize("returnObject.username==authentication.name")
    @Override
    public UserResponse getUserDetail(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse getMyInfo(){
        var context= SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        userRepository.findByUsername(name);
        User user = userRepository.findByUsername(name).orElseThrow(
                ()-> new AppException(ErrorCode.USER_NOT_EXISTS));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(String userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTS));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

}
