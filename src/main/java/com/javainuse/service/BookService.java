package com.javainuse.service;

import com.javainuse.model.Author;
import com.javainuse.model.BookCategory;
import com.javainuse.repository.BookRepo;
import com.javainuse.model.Book;
import com.javainuse.service.strategies.ConversionStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.javainuse.dto.BookDto;
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final ConversionStrategy conversionStrategy;

    // READ:

    public List<BookDto> getAllBooksInfo() {
        List<Book> allBooks = bookRepo.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : allBooks) {
            bookDtoList.add(new BookDto(book.getId(), book.getAuthor().getAuthorid(), book.getBookName(), book.getBookCategory().getCategoryId() , book.getYearpublished()));
        }
        return bookDtoList;
    }

    public BookDto getBookInfo(Long bookId) { // Optional nesnesi, bir değeri olabilir veya olmayabilir (yani null olabilir). Bu, bir değerin var olup olmadığını kontrol etmek ve varsa bu değeri güvenli bir şekilde almak için kullanılır. Optional<Book> ifadesi, Book tipinde bir değeri içerebilecek bir Optional nesnesi tanımlar.
        Optional<Book> bookOptional = bookRepo.findById(bookId); //Burada bookRepo.findById(bookId) metodu, belirtilen bookId ile bir Book nesnesini veritabanından arar ve bu nesneyi bir Optional<Book> olarak döndürür. Eğer veritabanında bookId ile ilgili bir kitap bulunamazsa, Optional.empty() döner.
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();  // Eger sadece evetse true hayirsa false dondurmesini istiyorsak bookOptional.isPresent() kullanabiliriz. Ama get() kullanarak bir exception atadik.
            return new BookDto(book.getId(), book.getAuthor().getAuthorid(), book.getBookName(), book.getBookCategory().getCategoryId() , book.getYearpublished());
        }
        return null;
    }

    public List<BookDto> getBookDtosFromBooks(List<Book> books) {
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book : books) {
            BookDto bookDto = conversionStrategy.convertToBookDto(book);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }


    // GetByYear interfacesinden once:
//    public List<BookDto> getByYearpublishedGreaterThan(Integer yearpublished) {
//        List<Book> booksYearGreater = bookRepo.findByYearpublishedGreaterThan(yearpublished);
//        return getBookDtosFromBooks(booksYearGreater);
//    }

            // VS

    // Ornek olarak kalsin getBookDtosFromBooks metodunu yazmadan once bu skeildeydi.
    /*
    public List<BookDto> getByYearpublishedGreaterThan(Integer yearpublished){
        List<Book> booksYearGreater = bookRepo.findByYearpublishedGreaterThan(yearpublished);
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book: booksYearGreater){
            BookDto bookDto = conversionStrategy.convertToBookDto(book);
            //BookDto bookDto = convertToBookDto(book);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }
     */

    public List<BookDto> getAuthorsAllBooks(Author authorId) {
        List<Book> booksByAuthor = bookRepo.findByAuthor(authorId);
        if (booksByAuthor == null) {
            return Collections.emptyList();
        }
        return getBookDtosFromBooks(booksByAuthor);
    }

    public List<BookDto> getByCategoryOfBook(BookCategory categoryOfBook) {
        List<Book> booksByCategory = bookRepo.findByBookCategory(categoryOfBook);
        return getBookDtosFromBooks(booksByCategory);
    }

}













