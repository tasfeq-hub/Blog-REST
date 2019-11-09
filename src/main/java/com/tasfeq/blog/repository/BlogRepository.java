package com.tasfeq.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tasfeq.blog.domain.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>{

}
