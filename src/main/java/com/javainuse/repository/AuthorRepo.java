package com.javainuse.repository;

import com.javainuse.model.Author;
import com.javainuse.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    //List<Book> findByAuthorid(Author author);


    // Long hali
    //List<Book> findByAuthorid(Long author);

}
