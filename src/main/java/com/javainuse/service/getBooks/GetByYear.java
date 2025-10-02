package com.javainuse.service.getBooks;

import com.javainuse.dto.BookDto;

import java.util.List;

public interface GetByYear {
    public List<BookDto> lessormore (Integer value);
    // open closed prensibini karsiliyor.
}
