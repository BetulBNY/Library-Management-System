package com.javainuse.controller;


import com.javainuse.dto.BookDto;
import com.javainuse.model.Author;
import com.javainuse.model.Book;
import com.javainuse.service.AuthorService;
import com.javainuse.service.BookService;
import com.javainuse.service.UserService;
import com.javainuse.service.authorlib.SaveAuthor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    private UserService userService;

    private BookService bookService;

    private SaveAuthor saveAuthor;

    public AuthorController(AuthorService authorService, UserService userService,BookService bookService,SaveAuthor saveAuthor )
    {   this.authorService = authorService;
        this.userService = userService;
        this.bookService = bookService;
        this.saveAuthor = saveAuthor;
    }

    // READ
    @GetMapping
    public List<Author> getAllAuthors() {return authorService.getAllAuthors();}

// 1 authorun butun kitaplarini getirme
    // obj auth
    //@GetMapping("/{authorId}")
    //public List<BookDto> getAuthorsAllBooks(@PathVariable Author authorId){return authorService.getAuthorsAllBooks(authorId);}

    // long obj

    @GetMapping("/auth/{authorId}")
    public List<BookDto> getAuthorsAllBookss(@PathVariable("authorId") Author authorId)
    {return authorService.getAuthorsAllBookss(authorId);}




    // CREATE
    @PostMapping
    public ResponseEntity <Author> saveOneAuthor(@RequestBody Author newAuthor){
        if(!userService.isCurrentUserLibrarian()){
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(saveAuthor.saveOneAuthor(newAuthor));
    }
}
