package com.javainuse.service;

import com.javainuse.dto.BookDto;
import com.javainuse.repository.AuthorRepo;
import com.javainuse.model.Author;
import com.javainuse.model.Book;
import com.javainuse.repository.BookRepo;
import com.javainuse.service.strategies.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// COPY

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    @Autowired
    private UserService userService;
    private final BookService bookService;


    public AuthorService(AuthorRepo authorRepo, BookRepo bookRepo,BookService bookService)
    {this.authorRepo = authorRepo;
    this.bookRepo = bookRepo;
    this.bookService = bookService;}

    public List<Author> getAllAuthors(){ return authorRepo.findAll();}

    public List<BookDto> getAuthorsAllBookss(Author authorId) {
        List<BookDto> booksByAuthor = bookService.getAuthorsAllBooks(authorId);
        if (booksByAuthor == null) {
            return Collections.emptyList();
        }

        List<BookDto> bookDtos = new ArrayList<>();
        for (BookDto bookDto : booksByAuthor) {
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

}
