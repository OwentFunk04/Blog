package com.tts.techtalentblog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tts.techtalentblog.models.BlogPost;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {

	@Override
	Optional<BlogPost> findById(Long id);

	@Override
	List<BlogPost> findAll();
}
