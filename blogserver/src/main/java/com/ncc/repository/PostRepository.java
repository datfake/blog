package com.ncc.repository;

import com.ncc.domain.Category;
import com.ncc.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * Spring Data  repository for the Post entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByCategory(Category category , Pageable pageable);
}
