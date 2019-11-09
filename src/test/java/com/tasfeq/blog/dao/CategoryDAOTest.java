package com.tasfeq.blog.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tasfeq.blog.domain.Category;

@SpringBootTest
class CategoryDAOTest {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Test
	void testSave() {
		Category finance = new Category("finance");
		Category result = categoryDAO.save(finance); 
		
		assertNotNull(result);
	}

	@Test
	void testGetCategoryById() {
		Long id = 1L;
		Optional<Category> result = categoryDAO.getCategoryById(id);
		
		assertNotNull(result);
	}

	@Test
	void testGetAllCategories() {
		List<Category> categoryList = new ArrayList<Category>();
		categoryList = categoryDAO.getAllCategories();
		
		assertTrue(!categoryList.isEmpty());
	}

	@Test
	void testDelete() {
		Long id = 1L;
		Long result = categoryDAO.delete(id);
		
		assertTrue(result > 0);
	}

}
