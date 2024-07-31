package com.upin.Project.Social.App.service;

import com.upin.Project.Social.App.constant.PredefinedRole;
import com.upin.Project.Social.App.dto.request.RegisterRequest;
import com.upin.Project.Social.App.dto.response.RegisterResponse;
import com.upin.Project.Social.App.dto.response.UserResponse;
import com.upin.Project.Social.App.entity.Role;
import com.upin.Project.Social.App.entity.User;
import com.upin.Project.Social.App.exception.AppException;
import com.upin.Project.Social.App.exception.ErrorCode;
import com.upin.Project.Social.App.resitory.RoleRepository;
import com.upin.Project.Social.App.resitory.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public RegisterResponse createUser(RegisterRequest request){

        if(!request.getPassword().equals(request.getRePassword()))
            throw new AppException(ErrorCode.NOT_MATCH_PASSWORD);

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        if(userRepository.existsByUsername(user.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        Role role = roleRepository.findByName("USER");

        System.out.println(role);

        user.setRole(role);

        user = userRepository.save(user);

        return RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roleName(user.getRole().getName())
                .build();
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userProfile_id("")
                .roleName(user.getRole().getName())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .userProfile_id(user.getUserProfile() != null ? (user.getUserProfile().getId() != null ? user.getUserProfile().getId().toString() : "") : "")
                        .roleName(user.getRole().getName())
                        .build())
                .collect(Collectors.toList());
    }


    @PostAuthorize("returnObject.username == authentication.name")
    public User getUser(String id){
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("user not found"));
    }

    public void deleteUser(String id){
        if(userRepository.existsById(id))
            throw new AppException(ErrorCode.USER_NOT_EXISTED);

        userRepository.deleteById(id);
    }
}
