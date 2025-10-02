package com.javainuse.controller;

import com.javainuse.dto.BookDto;
import com.javainuse.model.BookCategory;
import com.javainuse.service.BookService;
import com.javainuse.service.UserService;
import com.javainuse.service.bookserlib.DeleteBook;
import com.javainuse.service.bookserlib.UpdateBook;
import com.javainuse.service.getBooks.GetByYearpublishedGreaterThan;
import com.javainuse.service.getBooks.GetByYearpublishedLessThan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.javainuse.service.bookserlib.SaveBook;

import java.util.List;

@RestController
@RequestMapping("/book") // ana path adi
public class BookController {
    private BookService bookService;
    private SaveBook saveBook;
    private UserService userService;
    private DeleteBook deleteBook;
    private UpdateBook updateBook;
    private GetByYearpublishedLessThan getByYearpublishedLessThan;
    private GetByYearpublishedGreaterThan getByYearpublishedGreaterThan;
    public BookController(BookService bookService, UserService userService,SaveBook saveBook,DeleteBook deleteBook,UpdateBook updateBook,
                          GetByYearpublishedLessThan getByYearpublishedLessThan, GetByYearpublishedGreaterThan getByYearpublishedGreaterThan ){
        this.bookService = bookService;
        this.userService = userService;
        this.saveBook = saveBook;
        this.deleteBook = deleteBook;
        this.updateBook = updateBook;
        this.getByYearpublishedGreaterThan = getByYearpublishedGreaterThan;
        this.getByYearpublishedLessThan = getByYearpublishedLessThan;
    }

    // GET
    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooksInfo();
    }

    /*
    @GetMapping("/{bookId}")
    public Book getOneBook(@PathVariable Long bookId){
        return bookService.getOneBook(bookId);
    } */

    @GetMapping("/info/{bookId}")
    public BookDto getBookInfo(@PathVariable Long bookId){
        return bookService.getBookInfo(bookId);
    }

    /*
    @GetMapping("/{authorName}")
    public List<BookDto> getBookbyAuthor(@PathVariable Author authorName){
        return bookService.getBookbyAuthor(authorName);
    }  */

    @GetMapping("/category/{categoryName}")
    public List<BookDto> getByCategoryOfBook(@PathVariable BookCategory categoryName){
        return bookService.getByCategoryOfBook(categoryName);
    }

    @GetMapping("/yeargreater/{yearpublished}")
    public List<BookDto> getByYearpublishedGreaterThan(@PathVariable("yearpublished") Integer yearpublished){
        return getByYearpublishedGreaterThan.lessormore(yearpublished);
    }

    @GetMapping("/yearless/{yearpublished}")
    public List<BookDto> getByYearpublishedLessThan(@PathVariable("yearpublished") Integer yearpublished){
        return getByYearpublishedLessThan.lessormore(yearpublished);
    }



    // POST --> CREATE


    @PostMapping
    public ResponseEntity <BookDto> createBook(@RequestBody BookDto newBook){
        if(!userService.isCurrentUserLibrarian()){
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(saveBook.saveOneBook(newBook));
    }

    /*
    @PostMapping
    public ResponseEntity <BookDto> createBook(@RequestBody BookDto newBook){
        if(!userService.isCurrentUserLibrarian()){
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(bookService.saveOneBook(newBook));
    }*/

    // UPDATE
    //@PreAuthorize("hasRole('LIBRARIAN')") // Sadece LIBRARIAN rolüne sahip kullanıcılar erişebilir
    @PutMapping("/update/{bookId}")
    public ResponseEntity <BookDto> updateoneBook(@PathVariable Long bookId, @RequestBody BookDto updatedBook){
        if(userService.isCurrentUserLibrarian()){
            return ResponseEntity.ok(updateBook.updateOneBook(bookId,updatedBook));
        }
        return ResponseEntity.status(401).build();
    }

    // DELETE  solid icin kapattim
    /*
    @DeleteMapping("/{bookId}")
    public ResponseEntity <String> deleteoneBook(@PathVariable Long bookId){
        if(userService.isCurrentUserLibrarian()){
            return ResponseEntity.ok(bookService.deleteOneBook(bookId));
        }
        return ResponseEntity.status(401).build();
    }*/

    @DeleteMapping("/{bookId}")
    public ResponseEntity <String> deleteoneBook(@PathVariable Long bookId) {
        if (userService.isCurrentUserLibrarian()) {
            return ResponseEntity.ok(deleteBook.deleteOneBook(bookId));
        }
        return ResponseEntity.status(401).build();
    }

}
