package com.upin.Project.Social.App.service;

import com.upin.Project.Social.App.dto.request.PostRequest;
import com.upin.Project.Social.App.dto.response.PostResponse;
import com.upin.Project.Social.App.entity.Post;
import com.upin.Project.Social.App.entity.PostCategory;
import com.upin.Project.Social.App.entity.User;
import com.upin.Project.Social.App.resitory.HashTagRepository;
import com.upin.Project.Social.App.resitory.PostCategoryRepository;
import com.upin.Project.Social.App.resitory.PostRepository;
import com.upin.Project.Social.App.resitory.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {

    PostRepository postRepository;
    PostCategoryRepository postCategoryRepository;
    UserRepository userRepository;
    HashTagRepository hashTagRepository;


    public PostResponse create(PostRequest request){

        var optionalTemp1 = userRepository.findByUsername(request.getUsername());

        var optionalTemp2 = postCategoryRepository.findById(request.getPostCategoryId());

        User user = optionalTemp1.get();

        PostCategory postCategory = optionalTemp2.get();

        Post post = Post.builder()
                .title(request.getTitle())
                .topic(request.getTopic())
                .content(request.getContent())
                .user(user)
                .postCategory(postCategory)
                .build();

        var hashtags = hashTagRepository.findAllById(request.getHashTags());
        post.setHashTags(new HashSet<>(hashtags));

        post = postRepository.save(post);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .topic(post.getTopic())
                .content(post.getContent())
                .nameOfUser(post.getUser().getUsername())
                .nameOfPostCategory(post.getPostCategory().getName())
                .hashTags(post.getHashTags())
                .build();
    }
}
