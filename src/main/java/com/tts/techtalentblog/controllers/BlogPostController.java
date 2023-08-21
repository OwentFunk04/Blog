package com.tts.techtalentblog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.techtalentblog.models.BlogPost;
import com.tts.techtalentblog.repositories.BlogPostRepository;

import jakarta.validation.Valid;

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

}
