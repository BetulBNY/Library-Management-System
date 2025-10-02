package com.javainuse.repository;

import com.javainuse.dto.BookDto;
import com.javainuse.model.Author;
import com.javainuse.model.Book;
import com.javainuse.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repositoryler bizim db'ye query atmamizin metodlu hali. Mesela JPARepository icinde findAll() diye metod var
// Bu aslinda Select* From user' a denk geliyor.
@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(Author author);
    List<Book> findByBookCategory(BookCategory bookCategory);

    List<Book> findByYearpublishedGreaterThan(Integer yearpublished);
    List<Book> findByYearpublishedLessThan(Integer yearpublished);

}
