package com.upin.Project.Social.App.controller;

import com.upin.Project.Social.App.dto.request.UserProfileRequest;
import com.upin.Project.Social.App.dto.response.ApiResponse;
import com.upin.Project.Social.App.dto.response.UserProfileResponse;
import com.upin.Project.Social.App.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userprofile")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("/{user_id}")
    public ApiResponse<UserProfileResponse> getUserProfile(@PathVariable String user_id){
        return ApiResponse.<UserProfileResponse>builder()
                .message("User Profile of user")
                .result(userProfileService.getUserProfile(user_id))
                .build();
    }

    @PostMapping("/add_update")
    public ApiResponse<UserProfileResponse> create_updateUserProfile(@RequestBody @Valid UserProfileRequest request){
        return ApiResponse.<UserProfileResponse>builder()
                .message("add or update profile user successfully")
                .result(userProfileService.saveOrUpdateUserProfile(request))
                .build();
    }

//    @GetMapping("/myinfo")
//    public ApiResponse<UserProfileResponse> getMyInfo(){
//        return ApiResponse.<UserProfileResponse>builder()
//                .result(userProfileService.getMyInfo())
//                .build();
//    }
}
