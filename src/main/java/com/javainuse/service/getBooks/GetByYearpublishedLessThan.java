package com.javainuse.service.getBooks;

import com.javainuse.dto.BookDto;
import com.javainuse.model.Book;
import com.javainuse.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GetByYearpublishedLessThan implements GetByYear {
        private final BookRepo bookRepo;
        private final BootToBookDto bootToBookDto;
        @Override
        public List<BookDto> lessormore(Integer yearpublished) {
            List<Book> booksYearGreater = bookRepo.findByYearpublishedLessThan(yearpublished);
            return bootToBookDto.getBookDtosFromBooks(booksYearGreater);
        }
}
