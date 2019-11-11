package com.tasfeq.blog.Controller;

import java.net.URI;
import java.util.Date;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.tasfeq.blog.dao.BlogDAO;
import com.tasfeq.blog.domain.Blog;
import com.tasfeq.blog.exceptionhandler.ErrorDetails;
import io.swagger.annotations.ApiOperation;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/blogs/")
public class BlogController {
	
	@Autowired
	private BlogDAO blogDAO;
	
	@ApiOperation(value = "Insert an Blog Post")
	@PostMapping(path="/", consumes="application/json", produces="application/json")
	public ResponseEntity<Blog> createBlog(@Valid @RequestBody Blog blog){
		Blog newBlog = blogDAO.save(blog);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newBlog.getId())
				.toUri();
		
		if(newBlog.equals(blog)) {
			return ResponseEntity.created(location).build();
		}else {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@ApiOperation(value = "Get All Blog Posts")
	@GetMapping(path="/", produces="application/json")
	public ResponseEntity<Iterable<Blog>> getAllBlogs(){
		Iterable<Blog> blogList = blogDAO.getAllBlogs();
		return ResponseEntity.ok().body(blogList);
	}
	
	@ApiOperation(value = "Get Specific Blog Post")
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Blog>> getBlogById (@PathVariable(name="id") Long id) {
		Optional<Blog> blog = blogDAO.getBlogById(id);
		if(blog.isPresent()) {
			return ResponseEntity.ok().body(blog);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Update an Existing Blog Post")
	@PutMapping(path="/{id}", consumes="application/json", produces="application/json")
	public ResponseEntity<Blog> updateBlog(@PathVariable(name="id") Long id, @Valid @RequestBody Blog blog){
		Optional<Blog> existingBlog = blogDAO.getBlogById(id);
		blog.setId(existingBlog.get().getId());
		blogDAO.save(blog);
		return ResponseEntity.status(HttpStatus.OK).body(blog);
	}
	
	@ApiOperation(value = "Delete a Blog Post")
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deleteBlog(@PathVariable(name="id") Long id) {
		try {
			Long blogId = blogDAO.delete(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(Exception e){
			e.printStackTrace();
			ErrorDetails errorMessage = new ErrorDetails(new Date(), "Blog post of Id: "+id+" Not found", e.getMessage());
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Get Paginated Results For All Blogs")
	@GetMapping(path="/paginated")
    public ResponseEntity<List<Blog>> getAllBlogs(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy) 
    {
        List<Blog> list = blogDAO.getAllBlogsWithPagination(pageNo, pageSize, sortBy);
 
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
	
}
