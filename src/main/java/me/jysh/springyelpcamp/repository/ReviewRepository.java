package me.jysh.springyelpcamp.repository;

import me.jysh.springyelpcamp.model.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {}
