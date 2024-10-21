package org.example.vmslogin.mapper;

import org.example.vmslogin.dto.request.CreateUserRequest;
import org.example.vmslogin.dto.request.UpdateUserRequest;
import org.example.vmslogin.dto.response.UserResponse;
import org.example.vmslogin.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface    UserMapper {
    User toUser(CreateUserRequest request);
//    Mapping 2 thuộc tính giống nhau nhưng khác object về dữ liệu giống nhau
//    @Mapping(source = "firstName", target = "lastName")
//    Không mapping thuộc tính lastName thì dữ liệu hiển thị sẽ là null
//    @Mapping(target = "lastName", ignore = true)
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UpdateUserRequest request);
}
