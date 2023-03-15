package me.deepesh.springyelpcamp.repository;

import me.deepesh.springyelpcamp.enums.RoleEnum;
import me.deepesh.springyelpcamp.model.document.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

  Optional<Role> findByName(RoleEnum name);
}
