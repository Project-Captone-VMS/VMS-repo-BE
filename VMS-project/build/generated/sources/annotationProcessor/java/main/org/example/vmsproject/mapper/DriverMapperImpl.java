package org.example.vmsproject.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.example.vmsproject.dto.DriverDTO;
import org.example.vmsproject.dto.request.DriverRequest;
import org.example.vmsproject.dto.request.UpdateUserRequest;
import org.example.vmsproject.dto.response.DriverResponse;
import org.example.vmsproject.dto.response.UserResponse;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.Role;
import org.example.vmsproject.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-27T19:05:18+0700",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class DriverMapperImpl implements DriverMapper {

    @Override
    public Driver toDriverDTO(DriverDTO request) {
        if ( request == null ) {
            return null;
        }

        Driver.DriverBuilder driver = Driver.builder();

        driver.firstName( request.getFirstName() );
        driver.lastName( request.getLastName() );
        driver.licenseNumber( request.getLicenseNumber() );
        driver.email( request.getEmail() );
        driver.phoneNumber( request.getPhoneNumber() );

        return driver.build();
    }

    @Override
    public Driver toDriver(DriverRequest request) {
        if ( request == null ) {
            return null;
        }

        Driver.DriverBuilder driver = Driver.builder();

        driver.driverId( request.getDriverId() );
        driver.firstName( request.getFirstName() );
        driver.lastName( request.getLastName() );
        driver.licenseNumber( request.getLicenseNumber() );
        driver.workSchedule( request.getWorkSchedule() );
        driver.status( request.getStatus() );
        driver.email( request.getEmail() );
        driver.phoneNumber( request.getPhoneNumber() );

        return driver.build();
    }

    @Override
    public DriverResponse toDriverResponse(Driver driver) {
        if ( driver == null ) {
            return null;
        }

        DriverResponse.DriverResponseBuilder driverResponse = DriverResponse.builder();

        driverResponse.driverId( driver.getDriverId() );
        driverResponse.firstName( driver.getFirstName() );
        driverResponse.lastName( driver.getLastName() );
        driverResponse.licenseNumber( driver.getLicenseNumber() );
        driverResponse.workSchedule( driver.getWorkSchedule() );
        if ( driver.getStatus() != null ) {
            driverResponse.status( String.valueOf( driver.getStatus() ) );
        }

        return driverResponse.build();
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
        user.setFirstName( request.getFirstName() );
        user.setLastName( request.getLastName() );
        user.setPassword( request.getPassword() );
        user.setEmail( request.getEmail() );
        user.setPhoneNumber( request.getPhoneNumber() );
    }
}
