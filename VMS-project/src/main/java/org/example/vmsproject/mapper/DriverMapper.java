package org.example.vmsproject.mapper;

import org.example.vmsproject.dto.DriverDTO;
import org.example.vmsproject.dto.request.DriverRequest;
import org.example.vmsproject.dto.request.UpdateUserRequest;
import org.example.vmsproject.dto.response.DriverResponse;
import org.example.vmsproject.dto.response.UserResponse;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    Driver toDriverDTO(DriverDTO request);
    Driver toDriver(DriverRequest request);
    DriverResponse toDriverResponse(Driver driver);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UpdateUserRequest request);
}
