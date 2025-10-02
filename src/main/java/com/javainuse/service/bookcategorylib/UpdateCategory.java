package com.javainuse.service.bookcategorylib;

import com.javainuse.model.BookCategory;
import com.javainuse.repository.BookCategoryRepo;
import com.javainuse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UpdateCategory {

    private final UserService userService;
    private final BookCategoryRepo bookCategoryRepo;



    public BookCategory updateCategory(Long categoryId, BookCategory newcategroy){
        if(!userService.isCurrentUserLibrarian()){
            throw new RuntimeException("You are not responsible!");
        }
        Optional<BookCategory> oldCategoryData = bookCategoryRepo.findById(categoryId);

        //Optional sınıfı, bir değeri olabileceği gibi olmayabileceği anlamına gelir ve Java'da null değerlerle başa çıkmak için kullanılır.
        if(oldCategoryData.isPresent()){
            BookCategory updateCategoryData = oldCategoryData.get();
            updateCategoryData.setCategoryName(newcategroy.getCategoryName());
            return bookCategoryRepo.save(updateCategoryData);
        }
        return null;
    }

}
