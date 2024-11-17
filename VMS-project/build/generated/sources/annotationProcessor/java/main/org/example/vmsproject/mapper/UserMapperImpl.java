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
    date = "2024-11-17T18:00:51+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( request.getId() );
        user.username( request.getUsername() );
        user.firstName( request.getFirstName() );
        user.lastName( request.getLastName() );
        user.password( request.getPassword() );
        user.email( request.getEmail() );
        user.phoneNumber( request.getPhoneNumber() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        if ( user.getId() != null ) {
            userResponse.id( String.valueOf( user.getId() ) );
        }
        userResponse.username( user.getUsername() );
        userResponse.password( user.getPassword() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userResponse.roles( new LinkedHashSet<Role>( set ) );
        }
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );

        return userResponse.build();
    }

    @Override
    public void updateUser(User user, UpdateUserRequest request) {
        if ( request == null ) {
            return;
        }

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
    }
}
