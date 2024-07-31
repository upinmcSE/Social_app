package com.upin.Project.Social.App.service;

import com.upin.Project.Social.App.dto.request.PostCategoryRequest;
import com.upin.Project.Social.App.dto.response.HashTagResponse;
import com.upin.Project.Social.App.dto.response.PostCategoryResponse;
import com.upin.Project.Social.App.entity.HashTag;
import com.upin.Project.Social.App.entity.PostCategory;
import com.upin.Project.Social.App.exception.AppException;
import com.upin.Project.Social.App.exception.ErrorCode;
import com.upin.Project.Social.App.resitory.PostCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostCategoryService {
    PostCategoryRepository postCategoryRepository;

    public PostCategoryResponse create(PostCategoryRequest request){
        if(postCategoryRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.POST_CATEGORY_EXISTED);
        PostCategory postCategory = PostCategory.builder()
                .name(request.getName())
                .build();
        postCategory = postCategoryRepository.save(postCategory);

        return PostCategoryResponse.builder()
                .id(postCategory.getId())
                .name(postCategory.getName())
                .build();
    }
}
