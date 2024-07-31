package com.upin.Project.Social.App.service;

import com.upin.Project.Social.App.dto.request.HashTagRequest;
import com.upin.Project.Social.App.dto.response.HashTagResponse;
import com.upin.Project.Social.App.entity.HashTag;
import com.upin.Project.Social.App.exception.AppException;
import com.upin.Project.Social.App.exception.ErrorCode;
import com.upin.Project.Social.App.resitory.HashTagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HashTagService {

    HashTagRepository hashTagRepository;

    public HashTagResponse create(HashTagRequest request){
        if(hashTagRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.HASHTAG_EXISTED);
        HashTag hashTag = HashTag.builder()
                .name(request.getName())
                .build();
        hashTag = hashTagRepository.save(hashTag);

        return HashTagResponse.builder()
                .id(hashTag.getId())
                .name(hashTag.getName())
                .build();
    }
}
