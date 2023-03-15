package me.deepesh.springyelpcamp.repository;

import me.deepesh.springyelpcamp.model.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {}
