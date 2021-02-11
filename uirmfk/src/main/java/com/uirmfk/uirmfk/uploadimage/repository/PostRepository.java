package com.uirmfk.uirmfk.uploadimage.repository;

import com.uirmfk.uirmfk.uploadimage.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
