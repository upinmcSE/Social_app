package com.upin.Project.Social.App.dto.response;

import com.upin.Project.Social.App.entity.HashTag;
import com.upin.Project.Social.App.entity.PostCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {

    String id;

    String title;

    String topic;

    String content;

    String nameOfUser;

    String nameOfPostCategory;

    Set<HashTag> hashTags;
}
