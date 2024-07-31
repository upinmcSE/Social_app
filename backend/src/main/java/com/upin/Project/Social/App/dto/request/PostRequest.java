package com.upin.Project.Social.App.dto.request;

import com.upin.Project.Social.App.entity.HashTag;
import com.upin.Project.Social.App.entity.PostCategory;
import com.upin.Project.Social.App.entity.UserProfile;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {

    String title;

    String topic;

    String content;

    String username;

    String postCategoryId;

    Set<Long> hashTags;
}
