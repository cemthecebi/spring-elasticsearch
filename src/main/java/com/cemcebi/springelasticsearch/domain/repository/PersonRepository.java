package com.cemcebi.springelasticsearch.domain.repository;

import com.cemcebi.springelasticsearch.domain.entity.Person;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PersonRepository extends ElasticsearchRepository<Person, Long> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    List<Person> getByCustomQuery(String search);

    List<Person> findPersonByNameAndSurname(String name, String surname);
}