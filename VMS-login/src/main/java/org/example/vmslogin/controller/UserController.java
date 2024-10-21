package org.example.vmslogin.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.vmslogin.dto.request.CreateUserRequest;
import org.example.vmslogin.dto.request.UpdateUserRequest;
import org.example.vmslogin.dto.response.ApiResponse;
import org.example.vmslogin.dto.response.UserResponse;
import org.example.vmslogin.entity.User;
import org.example.vmslogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping("/create")
    public ApiResponse<User> saveUser(@RequestBody @Valid CreateUserRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.saveUser(request));
        return apiResponse;
    }

    @GetMapping("/list")
   ApiResponse<List<UserResponse>> findAllUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("username: {}",authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.findAllUser())
                .build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetail(@PathVariable("userId") String userId) {
        UserResponse user = userService.getUserDetail(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("myInfo")
    ApiResponse<UserResponse> getMyInfo() {
        return  ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UpdateUserRequest request) {
        userService.updateUser(userId, request);
        return ResponseEntity.ok().build();
    }

}
