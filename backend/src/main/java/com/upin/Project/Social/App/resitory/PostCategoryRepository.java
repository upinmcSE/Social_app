package com.upin.Project.Social.App.resitory;

import com.upin.Project.Social.App.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, String> {
    boolean existsByName(String s);
}
