package com.tts.techtalentblog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tts.techtalentblog.models.BlogPost;

// CrudRepository Takes 2 Generics:
// 1. The Datatype of the table this repository is responsible for (BlogPost)
// 2. The Datatype of the Primary Key (id) of our table that this repository
// is responsible for

public interface BlogPostRepository extends CrudRepository<BlogPost, Long>{

	@Override
	Optional<BlogPost> findById(Long id);
	
	@Override
	List<BlogPost> findAll();
}
