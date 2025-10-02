package com.javainuse.service;

import com.javainuse.model.Book;
import com.javainuse.model.BookCategory;
import com.javainuse.repository.BookCategoryRepo;
import com.javainuse.repository.BookRepo;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCategoryService {
    private final BookRepo bookRepo;
    private final BookCategoryRepo bookCategoryRepo;
    private final UserService userService;

    public BookCategoryService(BookRepo bookRepo,BookCategoryRepo bookCategoryRepo,UserService userService){

        this.bookRepo = bookRepo;
        this.bookCategoryRepo = bookCategoryRepo;
        this.userService = userService;
    }

    public List<BookCategory> getAllCategories() {
        return bookCategoryRepo.findAll();
    }

}
