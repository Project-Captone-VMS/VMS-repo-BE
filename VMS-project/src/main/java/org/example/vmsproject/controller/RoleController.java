package org.example.vmsproject.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.vmsproject.dto.request.RoleRequest;
import org.example.vmsproject.dto.response.ApiResponse;
import org.example.vmsproject.dto.response.RoleResponse;
import org.example.vmsproject.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class RoleController {
    RoleService roleService;

    @PostMapping("/create")
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping("/list")
     ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/delete/{name}")
    ApiResponse<Void> delete(@PathVariable String name){
        roleService.delete(name);
        return ApiResponse.<Void>builder().build();
    }
}
