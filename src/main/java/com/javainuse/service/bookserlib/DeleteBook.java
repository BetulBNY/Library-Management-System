package com.javainuse.service.bookserlib;

import com.javainuse.model.Book;
import com.javainuse.repository.BookRepo;
import com.javainuse.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteBook {
    private final BookRepo bookRepo;
    private final UserService userService;

    public DeleteBook(BookRepo bookRepo,UserService userService) {
        this.bookRepo = bookRepo;
        this.userService = userService;
    }

    public String deleteOneBook(Long bookid) {
        if(!userService.isCurrentUserLibrarian()){
            throw new RuntimeException("You can't access!");
        }
        Optional<Book> bookOptional = bookRepo.findById(bookid);
        if(bookOptional.isPresent()){
            Book deletedbook = bookOptional.get();
            bookRepo.deleteById(bookid);
            return "You deleted the '"+deletedbook.getBookName()+"' from your library";
        }
        return null;
    }


}
