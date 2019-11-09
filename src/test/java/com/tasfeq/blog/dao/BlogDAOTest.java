package com.tasfeq.blog.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tasfeq.blog.domain.Blog;
import com.tasfeq.blog.domain.Category;
import com.tasfeq.blog.domain.PublicationType;


@SpringBootTest
class BlogDAOTest {
	
	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Test
	void testSave() {
		Long id = 2L;
		Optional<Category> category = categoryDAO.getCategoryById(id);
		if(category.isPresent()) {
			Blog blog = new Blog("Good Music",category.get(),"test.png",new Date(),PublicationType.unpublished);
			blogDAO.save(blog);
		}
	}

	@Test
	void testGetBlogById() {
		Long id = 1L;
		Optional<Blog> result = blogDAO.getBlogById(id);
		
		assertNotNull(result);
	}

	@Test
	void testGetAllBlogs() {
		List<Blog> blogList = new ArrayList<Blog>();
		blogList = blogDAO.getAllBlogs();
		
		assertFalse(blogList.isEmpty());
	}

	@Test
	void testDelete() {
		Long id = 1L;
		Long result = blogDAO.delete(id);
		
		assertTrue(result==1);
	}

}
