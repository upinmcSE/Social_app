package com.upin.Project.Social.App.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(1006, "Uncategorized error", HttpStatus.FORBIDDEN),
    USER_EXISTED(1001, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002, "User not existed", HttpStatus.NOT_FOUND),
    USER_PROFILE_NOT_EXISTED(1003,"UserProfile of user not existed", HttpStatus.NOT_FOUND),
    TYPE_LIKE_EXISTED(2001,"Type like existed", HttpStatus.BAD_REQUEST),
    HASHTAG_EXISTED(2002,"hashtag name existed", HttpStatus.BAD_REQUEST),
    POST_CATEGORY_EXISTED(2003,"post category existed", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1004,"Unauthenticated", HttpStatus.UNAUTHORIZED),
    NOT_MATCH_PASSWORD(1005,"password and re-password not match", HttpStatus.BAD_REQUEST),
    INVALID_DOB(3001,"Invalid data of birth, must be least {min} age", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(3002,"Invalid username, must be least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(3003,"Invalid password, must be least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_KEY(3004,"Uncategorized error",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_EMAIL(3005,"Invalid email , malformed email ", HttpStatus.BAD_REQUEST),
    ;

    private final int code;
    private final String message;
    private HttpStatusCode statusCode;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
