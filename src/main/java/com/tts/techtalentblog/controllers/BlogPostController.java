package com.tts.techtalentblog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.techtalentblog.models.BlogPost;
import com.tts.techtalentblog.repositories.BlogPostRepository;


// We don't give this a @RestController annotation because we
// actually want this particular controller to return links to HTML
// documents rather than JSON / ResponseBodies that APIs typically send back
@Controller
public class BlogPostController {
   
	@Autowired
	BlogPostRepository blogPostRepository;

	// Part of the spring boot magic -> We get access to this Model object
	// that allows us to provide attributes / variables that can be accessed
	// from our HTML / Thymeleaf Documents

	// Inversion of Control / Dependency Injection -> We let Spring Boot
	// create an object responsible for sending data from our controller method
	// to our HTML templates and also let it inject it into our Controller method
	// as input

	@GetMapping("/")
	public String home(Model model) {
//		BlogPost myBlogPost = new BlogPost("Secret Basketball Tips", "Michael Jordan", "Don't miss");
////		System.out.println(myBlogPost);
////  
////		BlogPost savedBlogPost = blogPostRepository.save(myBlogPost);
////		System.out.println(savedBlogPost);
//		String secretMessage = "My password is 123456789";
//
//		// addAttribute takes 2 pieces of data in this case:
//		// 1. The 'name' of the variable / key value pair we want the
//		// template to have access to
//		// 2. the value of the variable associated with 1.'s name
//		model.addAttribute("blogPost", myBlogPost);
  
		List<BlogPost> blogPosts = blogPostRepository.findAll();
		
		
		model.addAttribute("blogPosts", blogPosts);
  
		return "blogPost/index.html";
	}
 
	@GetMapping("/new")
	public String newPost(Model model) {
		BlogPost blogPost = new BlogPost();
		model.addAttribute("blogPost", blogPost);

		return "blogPost/new.html";
	}

	// Method responsible for creating a new blogpost
	@PostMapping("/blogposts")
	public String createNewBlogPost(BlogPost blogPost, Model model) {

		BlogPost dbBlogPost = blogPostRepository.save(blogPost);
		model.addAttribute("newBlogPost", dbBlogPost);

		return "blogPost/result.html";
	}
	
	
	// The {} with a variable inside of it in web dev is sometimes referred to as a 'slug'
	// a 'standin' for a variable. Using slugs gives you access to more dynamic pathing in your
	// projects and they keep your projects DRY since you don't have to create HTML documents
	// for individual DB Rows
	// localhost:8080/blogposts/1, /blogposts/2, etc.
	@GetMapping("/blogposts/{id}")
	public String editPostWithId(@PathVariable Long id, Model model) {
		
		Optional<BlogPost> blogPost = blogPostRepository.findById(id);

		// isPresent is a method provided by the Optional class that
		// ensures that there is in fact a non-null object inside of it
		// Essentially, we are checking whether or not the Database could find
		// a BlogPost with the id we're looking for
		if(blogPost.isPresent()) {
			// the Optional .get() method essentially just removes the 'Optional' around
			// an object's typing. Optional<BlogPost> -> *use .get()* -> BlogPost
			BlogPost blogPostById = blogPost.get();
			model.addAttribute("blogPost", blogPostById);
		} 
		  
		return "blogPost/edit.html";
	}
	
	// This is listening in for 'PATCH' requests from our frontend / thymeleaf form
	@PatchMapping("/blogposts/{id}")
	public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
		
		Optional<BlogPost> blogPostById = blogPostRepository.findById(id);
		
		if(blogPostById.isPresent()) {
			// Query the database for the row pertaining to the ID
			// we are trying to edit
			BlogPost dbBlogPost = blogPostById.get();
			// Modify all of its fields so that it is up to date
			// with our recently filled out form / the input BlogPost variable we got
			
			System.out.println(dbBlogPost);
			
			dbBlogPost.setTitle(blogPost.getTitle());
			dbBlogPost.setAuthor(blogPost.getAuthor());
			dbBlogPost.setBlogEntry(blogPost.getBlogEntry());
			
			System.out.println(dbBlogPost);

			// Save the updated dbBlogPost to our database (right now it's just
			// an object)
			// We can use .save() here because it will automatically look / update
			// by ID which is present inside of dbBlogPost
			blogPostRepository.save(dbBlogPost);
			
			model.addAttribute("newBlogPost", dbBlogPost);
		}
		System.out.println(id);
		System.out.println(blogPost); 
		
		return "blogPost/result.html";
	} 
	
	
	// Responds to DELETE Requests from our frontend
	@DeleteMapping(path="/blogposts/{id}")
	public String deletePostById(@PathVariable Long id, Model model) {
		  
		Optional<BlogPost> blogPostById = blogPostRepository.findById(id);
		
		if(blogPostById.isPresent()) {
			BlogPost blogPost = blogPostById.get();
			model.addAttribute("deletedBlogPost", blogPost);
			blogPostRepository.deleteById(id);		 	
		}
		
		return "blogPost/delete.html";
	}
	
	

}
