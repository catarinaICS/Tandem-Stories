package controllers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import domain.Paragraph;

@Repository
public interface ParagraphRepository extends MongoRepository<Paragraph, String> {

	Paragraph findById(String id);

}
