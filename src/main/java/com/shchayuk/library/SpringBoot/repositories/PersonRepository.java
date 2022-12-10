package com.shchayuk.library.SpringBoot.repositories;

import com.shchayuk.library.SpringBoot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository <Person, Integer> {
    Person findByName(String name);

    Person findByBooksId(int id);
}
