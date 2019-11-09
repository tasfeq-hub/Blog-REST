package com.tasfeq.blog.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Blog> getAllBlogs(){
		return blogRepository.findAll();
	}
	
	public Long delete(Long id) {
		blogRepository.deleteById(id);
		return id;
	}
}
