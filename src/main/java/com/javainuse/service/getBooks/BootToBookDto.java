package com.javainuse.service.getBooks;

import com.javainuse.dto.BookDto;
import com.javainuse.model.Book;
import com.javainuse.service.strategies.ConversionStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BootToBookDto {
    private final ConversionStrategy conversionStrategy;
    public List<BookDto> getBookDtosFromBooks(List<Book> books) {
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book : books) {
            BookDto bookDto = conversionStrategy.convertToBookDto(book);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

}
