package com.javainuse.service.bookcategorylib;

import com.javainuse.model.BookCategory;
import com.javainuse.repository.BookCategoryRepo;
import com.javainuse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class SaveCategory {


    private final UserService userService;
    private final BookCategoryRepo bookCategoryRepo;



    public BookCategory createCategory(BookCategory bookCategory){
        if(userService.isCurrentUserLibrarian()){
            return bookCategoryRepo.save(bookCategory);
        }
        throw new RuntimeException("You are not responsible!");
    }


}
