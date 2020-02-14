package com.cemcebi.springelasticsearch.application.controller;

import com.cemcebi.springelasticsearch.domain.entity.Person;
import com.cemcebi.springelasticsearch.domain.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostConstruct
    public void init() {
        Person person = new Person();
        person.setId(1L);
        person.setName("cem");
        person.setSurname("cebi");
        person.setAddress("istanbul");
        person.setBirthDate(Calendar.getInstance().getTime());
        personRepository.save(person);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<List<Person>> getPersonByName(@PathVariable("name") String name) {
        List<Person> persons = personRepository.getByCustomQuery(name);
        return ResponseEntity.ok(persons);
    }

    @GetMapping(path = "/{name}/{surname}")
    public ResponseEntity<List<Person>> getPersonByFullName(@PathVariable("name") String name, @PathVariable("name") String surname) {
        List<Person> persons = personRepository.findPersonByNameAndSurname(name, surname);
        return ResponseEntity.ok(persons);
    }
}