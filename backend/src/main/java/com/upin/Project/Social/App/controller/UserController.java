package com.upin.Project.Social.App.controller;

import com.upin.Project.Social.App.dto.request.RegisterRequest;
import com.upin.Project.Social.App.dto.response.ApiResponse;
import com.upin.Project.Social.App.dto.response.RegisterResponse;
import com.upin.Project.Social.App.dto.response.UserResponse;
import com.upin.Project.Social.App.entity.User;
import com.upin.Project.Social.App.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    @GetMapping()
    public ApiResponse<List<UserResponse>> getUsers(){
        return ApiResponse.<List<UserResponse>>builder()
                .message("ahihi")
                .result(userService.getUsers())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<RegisterResponse> addUser(@RequestBody @Valid RegisterRequest request){
        log.info("Controller: create User");
        return ApiResponse.<RegisterResponse>builder()
                .message("add user succes")
                .result(userService.createUser(request))
                .build();

    }

    @GetMapping("/my-info")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable String id){
        return ApiResponse.<User>builder()
                .message("ahuhu")
                .result(userService.getUser(id))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ApiResponse.<Void>builder()
                .message("delete user successfully")
                .build();
    }
}
