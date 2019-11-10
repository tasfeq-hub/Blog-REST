package com.tasfeq.blog.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.tasfeq.blog.domain.Blog;
import com.tasfeq.blog.repository.BlogRepository;

@Service
public class BlogDAO {
	
	@Autowired
	private BlogRepository blogRepository;
	
	public Blog save(Blog blog) {
		return blogRepository.save(blog);
	}
	
	public Optional<Blog> getBlogById(Long id) {
		return blogRepository.findById(id);
	}
	
	public Iterable<Blog> getAllBlogs() {
		return blogRepository.findAll();
	}
	
	public Long delete(Long id) {
		blogRepository.deleteById(id);
		return id;
	}
	
	public List<Blog> getAllBlogsByCategoryId(Long id) {
		return blogRepository.findByCategory_id(id);
	}
	
	public List<Blog> getAllBlogsWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<Blog> pagedResult = blogRepository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Blog>();
        }
    }
}
