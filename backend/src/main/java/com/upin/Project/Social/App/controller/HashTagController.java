package com.upin.Project.Social.App.controller;

import com.upin.Project.Social.App.dto.request.HashTagRequest;
import com.upin.Project.Social.App.dto.response.ApiResponse;
import com.upin.Project.Social.App.dto.response.HashTagResponse;
import com.upin.Project.Social.App.service.HashTagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hashtags")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HashTagController {

    HashTagService hashTagService;
    @PostMapping("/create")
    public ApiResponse<HashTagResponse> createHashtag(@RequestBody HashTagRequest request){
        return ApiResponse.<HashTagResponse>builder()
                .message("create hashtag successfully")
                .result(hashTagService.create(request))
                .build();
    }
}
