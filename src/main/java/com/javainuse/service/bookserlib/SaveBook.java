package com.javainuse.service.bookserlib;

import com.javainuse.dto.BookDto;
import com.javainuse.model.Author;
import com.javainuse.model.Book;
import com.javainuse.model.BookCategory;
import com.javainuse.service.UserService;
import org.springframework.stereotype.Service;
import com.javainuse.repository.BookRepo;
import java.util.Optional;
import com.javainuse.repository.AuthorRepo;
import com.javainuse.repository.BookCategoryRepo;
@Service

public class SaveBook {


    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final BookCategoryRepo bookCategoryRepo;
    private final UserService userService;

    // Constructor
    public SaveBook(AuthorRepo authorRepo, BookRepo bookRepo, BookCategoryRepo bookCategoryRepo, UserService userService) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.bookCategoryRepo = bookCategoryRepo;
        this.userService = userService;
    }

    public BookDto getBookInfo(Long bookId) { // Optional nesnesi, bir değeri olabilir veya olmayabilir (yani null olabilir). Bu, bir değerin var olup olmadığını kontrol etmek ve varsa bu değeri güvenli bir şekilde almak için kullanılır. Optional<Book> ifadesi, Book tipinde bir değeri içerebilecek bir Optional nesnesi tanımlar.
        Optional<Book> bookOptional = bookRepo.findById(bookId); //Burada bookRepo.findById(bookId) metodu, belirtilen bookId ile bir Book nesnesini veritabanından arar ve bu nesneyi bir Optional<Book> olarak döndürür. Eğer veritabanında bookId ile ilgili bir kitap bulunamazsa, Optional.empty() döner.
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();  // Eger sadece evetse true hayirsa false dondurmesini istiyorsak bookOptional.isPresent() kullanabiliriz. Ama get() kullanarak bir exception atadik.
            return new BookDto(book.getId(), book.getAuthor().getAuthorid(), book.getBookName(), book.getBookCategory().getCategoryId() , book.getYearpublished());
        }
        return null;
    }



    // CREATE:
    public BookDto saveOneBook(BookDto newBook) {
        if(userService.isCurrentUserLibrarian()) {

            Book book = new Book();
            book.setBookName(newBook.getTitle());
            //book.setCategoryOfBook(newBook.getCategoryOfBook());
            book.setYearpublished(newBook.getYearPublished());

            //For Author
            Optional<Author> authorOptional = authorRepo.findById(newBook.getAuthorId());
            Optional<BookCategory> categoryOptional = bookCategoryRepo.findById(newBook.getCategoryOfBook());

            if (!(authorOptional.isPresent() && categoryOptional.isPresent())) {
                throw new RuntimeException("Bu id ile bir yazar ve kategori yok! Lütfen önce yazar ve kategoriyi ekleyin!");
            }

            Author author = authorOptional.get();
            book.setAuthor(author);
            BookCategory bookCategory = categoryOptional.get();
            book.setBookCategory(bookCategory);


            bookRepo.save(book);
            return getBookInfo(book.getId());
        }
        throw new RuntimeException("Can't access!");
    }



}
