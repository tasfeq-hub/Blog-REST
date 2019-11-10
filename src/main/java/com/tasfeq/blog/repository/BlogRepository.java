package com.tasfeq.blog.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.tasfeq.blog.domain.Blog;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Long>{
	
	@Query(
	        value = "SELECT * FROM blogs b where b.category_id = :category_id", 
	        nativeQuery=true
	    )
	public List<Blog> findByCategory_id(@Param("category_id") Long id);
}
