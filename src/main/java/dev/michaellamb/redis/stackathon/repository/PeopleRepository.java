package dev.michaellamb.redis.stackathon.repository;

import org.springframework.stereotype.Repository;

import com.redis.om.spring.repository.RedisDocumentRepository;

import dev.michaellamb.redis.stackathon.domain.Person;

@Repository
public interface PeopleRepository extends RedisDocumentRepository<Person,String> {

  // Find people by their first and last name
  Iterable<Person> findByFirstNameAndLastName(String firstName, String lastName);
  
  // Performing a text search on all text fields:
  Iterable<Person> search(String text);
}
