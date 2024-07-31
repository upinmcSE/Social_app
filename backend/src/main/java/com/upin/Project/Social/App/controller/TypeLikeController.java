package com.upin.Project.Social.App.controller;

import com.upin.Project.Social.App.dto.request.TypeLikeRequest;
import com.upin.Project.Social.App.dto.response.ApiResponse;
import com.upin.Project.Social.App.dto.response.TypeLikeResponse;
import com.upin.Project.Social.App.service.TypeLikeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/type_likes")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TypeLikeController {
    TypeLikeService typeLikeService;

    @PostMapping("/create")
    public ApiResponse<TypeLikeResponse> createTypeLike(@RequestBody TypeLikeRequest request){
        return ApiResponse.<TypeLikeResponse>builder()
                .message("create type like successfully")
                .result(typeLikeService.createTypeLike(request))
                .build();
    }
}
