package org.example.vmsproject.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.example.vmsproject.dto.request.CreateUserRequest;
import org.example.vmsproject.dto.request.UpdateUserRequest;
import org.example.vmsproject.dto.response.UserResponse;
import org.example.vmsproject.entity.Role;
import org.example.vmsproject.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-28T23:23:36+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.dob( request.getDob() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.password( user.getPassword() );
        userResponse.dob( user.getDob() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userResponse.roles( new LinkedHashSet<Role>( set ) );
        }

        return userResponse.build();
    }

    @Override
    public void updateUser(User user, UpdateUserRequest request) {
        if ( request == null ) {
            return;
        }

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setDob( request.getDob() );
    }
}
