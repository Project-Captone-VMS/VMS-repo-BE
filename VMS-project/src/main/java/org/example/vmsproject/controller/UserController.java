package org.example.vmsproject.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.vmsproject.dto.request.CreateUserRequest;
import org.example.vmsproject.dto.request.UpdateUserRequest;
import org.example.vmsproject.dto.response.ApiResponse;
import org.example.vmsproject.dto.response.UserResponse;
import org.example.vmsproject.entity.User;
import org.example.vmsproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    UserService userService;


    @PostMapping("/create")
    public ApiResponse<User> registerUser(@RequestBody CreateUserRequest request) {
        User user = userService.saveUser(request);
        return ApiResponse.<User>builder()
                .result(user)
                .build();
    }


//    @PostMapping("/create")
//    public ApiResponse<User> saveUser(@RequestBody @Valid CreateUserRequest request) {
//        ApiResponse<User> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(userService.saveUser(request));
//        return apiResponse;
//    }


    @GetMapping("/list")
    ApiResponse<List<UserResponse>> findAllUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("username: {}", authentication.getName());
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
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UpdateUserRequest request) {
        userService.updateUser(userId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByUsername(@PathVariable String username) {
        UserResponse userResponse = userService.getUserByUserName(username);
        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
        return ResponseEntity.ok(response);
    }

}
