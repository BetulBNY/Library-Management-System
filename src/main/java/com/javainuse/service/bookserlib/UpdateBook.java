package com.javainuse.service.bookserlib;

import com.javainuse.dto.BookDto;
import com.javainuse.model.Author;
import com.javainuse.model.Book;
import com.javainuse.model.BookCategory;
import com.javainuse.repository.AuthorRepo;
import com.javainuse.repository.BookCategoryRepo;
import com.javainuse.repository.BookRepo;
import com.javainuse.service.UserService;
import com.javainuse.service.strategies.AccessControlService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBook {
    private UserService userService;
    private BookRepo bookRepo;
    private AuthorRepo authorRepo;
    private BookCategoryRepo bookCategoryRepo;

    private AccessControlService accessControlService;

    public UpdateBook(AuthorRepo authorRepo, BookRepo bookRepo, BookCategoryRepo bookCategoryRepo, UserService userService,AccessControlService accessControlService) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.bookCategoryRepo = bookCategoryRepo;
        this.userService = userService;
        this.accessControlService = accessControlService;
    }


    public BookDto getBookInfo(Long bookId) { // Optional nesnesi, bir değeri olabilir veya olmayabilir (yani null olabilir). Bu, bir değerin var olup olmadığını kontrol etmek ve varsa bu değeri güvenli bir şekilde almak için kullanılır. Optional<Book> ifadesi, Book tipinde bir değeri içerebilecek bir Optional nesnesi tanımlar.
        Optional<Book> bookOptional = bookRepo.findById(bookId); //Burada bookRepo.findById(bookId) metodu, belirtilen bookId ile bir Book nesnesini veritabanından arar ve bu nesneyi bir Optional<Book> olarak döndürür. Eğer veritabanında bookId ile ilgili bir kitap bulunamazsa, Optional.empty() döner.
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();  // Eger sadece evetse true hayirsa false dondurmesini istiyorsak bookOptional.isPresent() kullanabiliriz. Ama get() kullanarak bir exception atadik.
            return new BookDto(book.getId(), book.getAuthor().getAuthorid(), book.getBookName(), book.getBookCategory().getCategoryId() , book.getYearpublished());
        }
        return null;
    }


    public BookDto updateOneBook(Long bookid, BookDto newBookData) {
        if (!accessControlService.isCurrentUserLibrarian()) {
            throw new RuntimeException("Can't access!");
        }
        Optional<Book> oldBookData = bookRepo.findById(bookid);

        if(oldBookData.isPresent()){
            Book updatedBookData = oldBookData.get();
            updatedBookData.setBookName(newBookData.getTitle());
            updatedBookData.setYearpublished(newBookData.getYearPublished());
            //updatedBookData.setStatus(newBookData.getStatus());  // Update status

            Optional<Author> authorOptional = authorRepo.findById(newBookData.getAuthorId());
            if (authorOptional.isPresent()) {
                Author author = authorOptional.get();
                updatedBookData.setAuthor(author);
            } else {
                throw new RuntimeException("Güncellenmek istenen kitaba ait yazar bulunamadı!");
            }
            Optional<BookCategory> categoryOptional = bookCategoryRepo.findById(newBookData.getCategoryOfBook());
            if (categoryOptional.isPresent()) {
                BookCategory bookCategory = categoryOptional.get();
                updatedBookData.setBookCategory(bookCategory);
            } else {
                throw new RuntimeException("Güncellenmek istenen kitaba ait kategori bulunamadı!");
            }

            bookRepo.save(updatedBookData);
            return getBookInfo(bookid);
        }
        return null;
    }
}
