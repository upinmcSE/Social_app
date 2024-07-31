package com.upin.Project.Social.App.controller;

import com.upin.Project.Social.App.dto.request.PostCategoryRequest;
import com.upin.Project.Social.App.dto.response.ApiResponse;
import com.upin.Project.Social.App.dto.response.PostCategoryResponse;
import com.upin.Project.Social.App.service.PostCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post_categories")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PostCategoryController {
    PostCategoryService postCategoryService;

    @PostMapping("/create")
    public ApiResponse<PostCategoryResponse> createPostCategory(@RequestBody PostCategoryRequest request){
        return ApiResponse.<PostCategoryResponse>builder()
                .message("crete post category successfully")
                .result(postCategoryService.create(request))
                .build();
    }
}
