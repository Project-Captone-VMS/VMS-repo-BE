package org.example.vmsproject.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTS(1002, "User exists", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTS(1005, "User not existed", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND(1005, "Role not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Authenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "You are must be least {min}", HttpStatus.BAD_REQUEST),
    INVALID_CAPACITY(1009, "Cannot add product: Quantity exceeds warehouse capacity", HttpStatus.BAD_REQUEST),
    INVALID_WAREHOUSE(1010, "Warehouse not found", HttpStatus.BAD_REQUEST),
    INVALID_PRODUCT(1011, "Product not found", HttpStatus.BAD_REQUEST),
    INVALID_CAPACITY_ZERO(1011, "Cannot decrease stock below zero", HttpStatus.BAD_REQUEST),
    WAREHOUSE_ALREADY_EXISTS(1012,"Warehouse name anh location exists", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1013, "User not found", HttpStatus.NOT_FOUND),
    DRIVER_NOT_FOUND(1014, "User not found", HttpStatus.NOT_FOUND),
    INVALID_OLDPASSWORD(1015, "Old password invalid",  HttpStatus.BAD_REQUEST),
    SHIPMENT_NOT_FOUND(1016, "Old password invalid",  HttpStatus.BAD_REQUEST),
    ITEM_NOT_FOUND(1016, "Item not found",  HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_EXISTS(1017, "Phone number exists",  HttpStatus.BAD_REQUEST),
    EMAIL_EXISTS(1018, "Email exists",  HttpStatus.BAD_REQUEST),
    ROUTE_NOT_FOUND(1018, "Route not found",  HttpStatus.BAD_REQUEST);
    ;


    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }


}
