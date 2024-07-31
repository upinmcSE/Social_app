package com.upin.Project.Social.App.service;

import com.upin.Project.Social.App.dto.request.UserProfileRequest;
import com.upin.Project.Social.App.dto.response.UserProfileResponse;
import com.upin.Project.Social.App.dto.response.UserResponse;
import com.upin.Project.Social.App.entity.User;
import com.upin.Project.Social.App.entity.UserProfile;
import com.upin.Project.Social.App.enums.Gender;
import com.upin.Project.Social.App.exception.AppException;
import com.upin.Project.Social.App.exception.ErrorCode;
import com.upin.Project.Social.App.resitory.UserProfileRepository;
import com.upin.Project.Social.App.resitory.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserRepository userRepository;

    public UserProfileResponse saveOrUpdateUserProfile(UserProfileRequest request){
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (!optionalUser.isPresent()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        User user = optionalUser.get();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(user.getId());
        UserProfile userProfile;

        if (userProfileOptional.isPresent()) {
            userProfile = userProfileOptional.get();
        } else {
            userProfile = new UserProfile();
            userProfile.setError(0); // Chỉ đặt giá trị error = 0 khi tạo mới
        }

        userProfile.setUser(user);
        userProfile.setEmail(request.getEmail());
        userProfile.setPhoneNumber(request.getPhoneNumber());
        userProfile.setFirstName(request.getFirstName());
        userProfile.setLastName(request.getLastName());
        userProfile.setAddress(request.getAddress());
        userProfile.setDob(request.getDob());
        userProfile.setGender(request.getGender());
        userProfile.setAvatar(request.getAvatar());
        userProfile.setFavoriteCategories(request.getFavoriteCategories());
        userProfile.setActive(true);  // Hoặc bạn có thể để giá trị từ request nếu cần thiết

        userProfile = userProfileRepository.save(userProfile);

        return UserProfileResponse.builder()
                .id(userProfile.getId())
                .username(user.getUsername())
                .email(userProfile.getEmail())
                .phoneNumber(userProfile.getPhoneNumber())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .address(userProfile.getAddress())
                .dob(userProfile.getDob())
                .gender(userProfile.getGender())
                .avatar(userProfile.getAvatar())
                .active(userProfile.isActive())
                .error(userProfile.getError())
                .favoriteCategories(userProfile.getFavoriteCategories())
                .build();
    }

    public UserProfileResponse getUserProfile(String user_id){
        if(!userRepository.existsById(user_id))
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        System.out.println(user_id);

        User user = userRepository.findById(user_id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        UserProfile userProfile = userProfileRepository.findByUserId(user_id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_PROFILE_NOT_EXISTED));

        return UserProfileResponse.builder()
                .id(userProfile.getId())
                .username(user.getUsername())
                .email(userProfile.getEmail())
                .phoneNumber(userProfile.getPhoneNumber())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .address(userProfile.getAddress())
                .dob(userProfile.getDob())
                .gender(userProfile.getGender())
                .avatar(userProfile.getAvatar())
                .active(userProfile.isActive())
                .error(userProfile.getError())
                .favoriteCategories(userProfile.getFavoriteCategories())
                .build();
    }

    public UserProfileResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        UserProfile userProfile = userProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_PROFILE_NOT_EXISTED));

        return UserProfileResponse.builder()
                .id(userProfile.getId())
                .username(userProfile.getUser().getUsername())
                .email(userProfile.getEmail())
                .phoneNumber(userProfile.getPhoneNumber())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .address(userProfile.getAddress())
                .dob(userProfile.getDob())
                .gender(userProfile.getGender())
                .avatar(userProfile.getAvatar())
                .active(userProfile.isActive())
                .error(userProfile.getError())
                .favoriteCategories(userProfile.getFavoriteCategories())
                .build();
    }


    public void plusError(String id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        User user = optionalUser.get();
        UserProfile userProfile = userProfileRepository.findById(user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_PROFILE_NOT_EXISTED));

        userProfile.setError(userProfile.getError() + 1);

        userProfileRepository.save(userProfile);
    }

}
