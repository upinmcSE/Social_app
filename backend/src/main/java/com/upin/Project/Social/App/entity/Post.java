package com.upin.Project.Social.App.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String topic;

    @Lob
    @Column(columnDefinition = "TEXT")
    String content;

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdDate;

    @CreatedBy
    @Column(updatable = false)
    UUID createdBy;

    @LastModifiedDate
    LocalDateTime lastModifiedDate;

    @LastModifiedBy
    UUID lastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    Set<Like_> likes = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "post_category_id", nullable = false)
    PostCategory postCategory;

    @ManyToMany
    private Set<HashTag> hashTags = new HashSet<>();


}
