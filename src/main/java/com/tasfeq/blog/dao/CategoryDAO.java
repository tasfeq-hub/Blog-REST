package com.tasfeq.blog.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tasfeq.blog.domain.Category;
import com.tasfeq.blog.repository.CategoryRepository;

@Service
public class CategoryDAO {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	public Optional<Category> getCategoryById(Long id) {
		return categoryRepository.findById(id);
	}
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	public Long delete(Long id) {
		categoryRepository.deleteById(id);
		return id;
	}
}
