package com.upin.Project.Social.App.resitory;

import com.upin.Project.Social.App.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    boolean existsByName(String s);

}
