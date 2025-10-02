package com.javainuse.service.strategies;

import com.javainuse.dto.BookDto;
import com.javainuse.model.Book;
import org.springframework.stereotype.Component;

public interface ConversionStrategy {
    BookDto convertToBookDto(Book book); // ConvertToDto olmaliydi
}

