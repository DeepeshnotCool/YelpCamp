package me.deepesh.springyelpcamp.repository;

import me.deepesh.springyelpcamp.model.document.Campground;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampgroundRepository extends MongoRepository<Campground, String> {}
