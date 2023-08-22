package com.tts.techtalentblog.models;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// We need to tell spring boot that this is something that needs
// to be stored inside of a database
// To do that, we use the @Entity annotation

@Entity
public class BlogPost {

	// The primary key -> Tell springboot the instance variable that is
	// the PK with @Id
	// We can also tell SB how to generate it / rely on springboot to generate it
	// without our help

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Add some validation to our instance variables to enforce
	// what type of data we add to our DB

	@Length(max = 50, min = 1)
	private String title;

	@Length(max = 50, min = 1)
	private String author;

	@Length(min = 1)
	private String blogEntry;
	
	public BlogPost() {
		
	}

	public BlogPost(String title, String author, String blogEntry) {
		this.title = title;
		this.author = author;
		this.blogEntry = blogEntry;
	}
	
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;    
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBlogEntry() {
		return blogEntry;
	}

	public void setBlogEntry(String blogEntry) {
		this.blogEntry = blogEntry;
	}

	@Override
	public String toString() {
		return "BlogPost [id=" + id + ", title=" + title + ", author=" + author + ", blogEntry=" + blogEntry + "]";
	}

	
	
}
