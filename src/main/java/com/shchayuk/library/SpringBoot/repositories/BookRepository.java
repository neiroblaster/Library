package com.shchayuk.library.SpringBoot.repositories;

import com.shchayuk.library.SpringBoot.models.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByOwnerId(int id);

    List<Book> findByNameIsStartingWith(String partName);

    Page<Book> findAll(Pageable pageable);

}
