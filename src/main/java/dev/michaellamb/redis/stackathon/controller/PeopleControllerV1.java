package dev.michaellamb.redis.stackathon.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.michaellamb.redis.stackathon.domain.Person;
import dev.michaellamb.redis.stackathon.repository.PeopleRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleControllerV1 {

  @Autowired
  PeopleRepository peopleRepository;

  @GetMapping("all")
  Iterable<Person> all() {
    return peopleRepository.findAll();
  }

  @GetMapping("age_between")
  Iterable<Person> byAgeBetween( //
      @RequestParam("min") int min, //
      @RequestParam("max") int max) {
    return peopleRepository.findByAgeBetween(min, max);
  }

  @GetMapping("homeloc")
  Iterable<Person> byHomeLoc(//
      @RequestParam("lat") double lat, //
      @RequestParam("lon") double lon, //
      @RequestParam("d") double distance) {
    return peopleRepository.findByHomeLoc(new Point(lon, lat), new Distance(distance, Metrics.MILES));
  }

  @GetMapping("name")
  Iterable<Person> byFirstNameAndLastName(@RequestParam("first") String firstName, //
      @RequestParam("last") String lastName) {
    return peopleRepository.findByFirstNameAndLastName(firstName, lastName);
  }

  @GetMapping("statement")
  Iterable<Person> byPersonalStatement(@RequestParam("q") String q) {
    return peopleRepository.searchByPersonalStatement(q);
  }

  @PostMapping("new")
  Person create(@RequestBody Person newPerson) {
    return peopleRepository.save(newPerson);
  }
  
  @GetMapping("{id}")
  Optional<Person> byId(@PathVariable String id) {
    return peopleRepository.findById(id);
  }

  @PutMapping("{id}")
  Person update(@RequestBody Person newPerson, @PathVariable String id) {
    return peopleRepository.findById(id).map(person -> {
      person.setFirstName(newPerson.getFirstName());
      person.setLastName(newPerson.getLastName());
      return peopleRepository.save(person);
    }).orElseGet(() -> {
      return peopleRepository.save(newPerson);
    });
  }

  @DeleteMapping("{id}")
  void delete(@PathVariable String id) {
    peopleRepository.deleteById(id);
  }
  
  @GetMapping("city")
  Iterable<Person> byCity(@RequestParam("city") String city) {
    return peopleRepository.findByAddress_City(city);
  }
  
  @GetMapping("city_state")
  Iterable<Person> byCityAndState(@RequestParam("city") String city, //
      @RequestParam("state") String state) {
    return peopleRepository.findByAddress_CityAndAddress_State(city, state);
  }
  
  @GetMapping("skills")
  Iterable<Person> byAnySkills(@RequestParam("skills") Set<String> skills) {
    return peopleRepository.findBySkills(skills);
  }
  
  @GetMapping("skills/all")
  Iterable<Person> byAllSkills(@RequestParam("skills") Set<String> skills) {
    return peopleRepository.findBySkillsContainingAll(skills);
  }
  
  @GetMapping("search/{q}")
  Iterable<Person> fullTextSearch(@PathVariable("q") String q) {
    return peopleRepository.search(q);
  }
}