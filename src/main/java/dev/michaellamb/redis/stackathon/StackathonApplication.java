package dev.michaellamb.redis.stackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;

import dev.michaellamb.redis.stackathon.repository.PeopleRepository;

@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "dev.michaellamb.redis.stackathon")
public class StackathonApplication {

	@Autowired
	PeopleRepository peopleRepository;

	public static void main(String[] args) {
		SpringApplication.run(StackathonApplication.class, args);
	}
}
