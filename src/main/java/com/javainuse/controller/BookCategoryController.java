package com.javainuse.controller;

import com.javainuse.model.BookCategory;
import com.javainuse.model.User;
import com.javainuse.service.BookCategoryService;
import com.javainuse.service.UserService;
import com.javainuse.service.bookcategorylib.SaveCategory;
import com.javainuse.service.bookcategorylib.UpdateCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class BookCategoryController {
    private BookCategoryService bookCategoryService;
    private UserService userService;

    private SaveCategory saveCategory;
    private UpdateCategory updateCategory;

    public BookCategoryController(BookCategoryService bookCategoryService, UserService userService,SaveCategory saveCategory,UpdateCategory updateCategory){
        this.bookCategoryService = bookCategoryService;
        this.userService = userService;
        this.saveCategory = saveCategory;
        this.updateCategory = updateCategory;
    }


    // READ:
    @GetMapping
    public List<BookCategory> getAllBooks(){
        return bookCategoryService.getAllCategories();
    }

    // UPDATE:
    @PutMapping("/{categoryId}")
    public ResponseEntity<BookCategory> updateCategory(@PathVariable Long categoryId, @RequestBody BookCategory newcategroy){
        if(userService.isCurrentUserLibrarian()){
            return ResponseEntity.ok(updateCategory.updateCategory(categoryId, newcategroy));
        }
        return ResponseEntity.status(401).build();
    }

    //CREATE:
    @PostMapping
    public ResponseEntity<BookCategory> createCategory(@RequestBody BookCategory newCategory){
        if(userService.isCurrentUserLibrarian()){
            return ResponseEntity.ok(saveCategory.createCategory(newCategory));
        }
        return ResponseEntity.status(401).build();
    }
}
