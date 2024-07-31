package com.upin.Project.Social.App.resitory;

import com.upin.Project.Social.App.entity.TypeLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeLikeRepository extends JpaRepository<TypeLike, Long> {
    boolean existsByName(String s);
}
