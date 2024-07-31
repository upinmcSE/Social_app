package com.upin.Project.Social.App.service;

import com.upin.Project.Social.App.dto.request.TypeLikeRequest;
import com.upin.Project.Social.App.dto.response.TypeLikeResponse;
import com.upin.Project.Social.App.entity.TypeLike;
import com.upin.Project.Social.App.exception.AppException;
import com.upin.Project.Social.App.exception.ErrorCode;
import com.upin.Project.Social.App.resitory.TypeLikeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TypeLikeService {
    TypeLikeRepository typeLikeRepository;

    public TypeLikeResponse createTypeLike(TypeLikeRequest request){
        if(typeLikeRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.TYPE_LIKE_EXISTED);
        TypeLike typeLike = TypeLike.builder()
                .name(request.getName())
                .build();
        typeLike = typeLikeRepository.save(typeLike);

        return TypeLikeResponse.builder()
                .id(typeLike.getId())
                .name(typeLike.getName())
                .build();
    }
}
