package dev.michaellamb.redis.stackathon.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;

import dev.michaellamb.redis.stackathon.domain.Person;

public interface PeopleRepository extends RedisDocumentRepository<Person,String> {

}
