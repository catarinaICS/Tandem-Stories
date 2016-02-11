package controllers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import domain.Story;

@Repository
public interface StoryRepository extends MongoRepository<Story, String> {

	Story findById(String id);
	
}
