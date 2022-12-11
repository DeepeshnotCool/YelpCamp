package me.jysh.springyelpcamp.repository;

import me.jysh.springyelpcamp.model.document.Campground;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampgroundRepository extends MongoRepository<Campground, String> {}
