package com.javainuse.service.strategies;

import com.javainuse.dto.BookDto;
import com.javainuse.model.Book;
import org.springframework.stereotype.Component;

@Component
public class DefaultConversionStrategy implements ConversionStrategy{
    public BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthorId(book.getAuthor().getAuthorid());
        bookDto.setTitle(book.getBookName());
        bookDto.setCategoryOfBook(book.getBookCategory().getCategoryId());
        bookDto.setYearPublished(book.getYearpublished());
        return bookDto;
    }
    
        }
