package com.tasfeq.blog.Controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tasfeq.blog.dao.BlogDAO;
import com.tasfeq.blog.dao.CategoryDAO;
import com.tasfeq.blog.domain.Blog;
import com.tasfeq.blog.domain.Category;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

	 @Autowired
	 private CategoryDAO categoryDAO;
	 
	 @Autowired
	 private BlogDAO blogDAO;
	
	 @PostMapping(path="/", consumes="application/json", produces="application/json")
	 public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
		 Category newCategory = categoryDAO.save(category);
		 
		 if(newCategory.equals(category)) {
			 return ResponseEntity.status(HttpStatus.CREATED).body(category);
		 }else {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
	 }
	 
	 @GetMapping(path="/", produces="application/json")
	 public ResponseEntity<List<Category>> getAllCategories(){
		 List<Category> categoryList = categoryDAO.getAllCategories();
		 return ResponseEntity.ok().body(categoryList);
	 }
	 
	 @GetMapping(path="/{id}", produces="application/json")
	 public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable(name="id") Long id){
		 Optional<Category> category = categoryDAO.getCategoryById(id);
		
		 if(category.isPresent()) {
			 return ResponseEntity.ok().body(category);
		 }else {
			 return ResponseEntity.notFound().build();
		 }
	 }
	 
	 @PutMapping(path="/{id}", consumes="application/json", produces="application/json")
	 public ResponseEntity<Category> updateCategory(@PathVariable(name="id") Long id, @Valid @RequestBody Category category){
		 Optional<Category> existingCategory = categoryDAO.getCategoryById(id);
		 category.setId(existingCategory.get().getId());
		 categoryDAO.save(category);
		 return ResponseEntity.status(HttpStatus.OK).body(category);
	 }
	 
	 @DeleteMapping(path="/{id}")
	 public ResponseEntity<Category> deleteCategory(@PathVariable(name="id") Long id){
		 try {
			 Long categoryId = categoryDAO.delete(id);
			 return ResponseEntity.status(HttpStatus.OK).build();
		 }catch(Exception e){
			 e.printStackTrace();
			 return ResponseEntity.noContent().build();
		 }
	 }
	 
	 @GetMapping(path="/blogs")
	 public ResponseEntity<List<Blog>> getAllBlogsbyCategoryId(@RequestParam(value="categoryId", required=false) Long id){
		 List<Blog> blogLists = blogDAO.getAllBlogsByCategoryId(id);
		 return ResponseEntity.ok().body(blogLists);
	 }
}
