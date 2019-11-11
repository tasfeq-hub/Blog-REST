package com.tasfeq.blog.Controller;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.Optional;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.tasfeq.blog.BlogApplication;
import com.tasfeq.blog.dao.CategoryDAO;
import com.tasfeq.blog.domain.Blog;
import com.tasfeq.blog.domain.Category;
import com.tasfeq.blog.domain.PublicationType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BlogApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlogControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();

	@Test
	void testCreateBlog() {
		Long id = 3L;
		Optional<Category> category = categoryDAO.getCategoryById(id);
		if(category.isPresent()) {
			Blog blog = new Blog("Good Music another",category.get(),"test.png",new Date(),PublicationType.unpublished);
			
			HttpEntity<Blog> entity = new HttpEntity<Blog>(blog,headers);
			
			ResponseEntity<String> response = restTemplate.exchange(
					createURLWithPort("/api/v1/blogs/"),
					HttpMethod.POST, entity, String.class);

			
			int result = response.getStatusCodeValue();
			
			assertTrue(result==201);
		}else {
			fail("Test Failed ! Not Saved Successfully !");	
		}
	}

	@Test
	void testGetAllBlogs() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/v1/blogs/"),
				HttpMethod.GET, entity, String.class);
		
		String expected ="[{ id:4, title:Godzilla2 }]";
		
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
