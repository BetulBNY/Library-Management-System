package com.javainuse.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.javainuse.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data; // Getter, setter, equals, hashCode ve toString metodları otomatik olarak oluşturulacaktır.
import lombok.NoArgsConstructor;

//@NoArgsConstructor ve @AllArgsConstructor kullanıldığında lombok'ün otomatik olarak belirli constructor olusturuyordu ama biz bu entityde kendimiz olusturmayi tercih ettik.
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","title","authorName","categoryOfBook", "yearPublished"}) // @JsonPropertyOrder annotasyonu, JSON çıktısında sıralamanın nasıl olacağını belirtmek için kullanılır. Bu annotasyon, belirtilen sıraya göre JSON özelliklerinin (property) sıralanmasını sağlar.

public class BookDto implements Serializable {

    private Long id;
    private Long authorId;
    private String title;
    private Long categoryOfBook;
    private int yearPublished;

    // KARSILASTIGIM BIR HATA : getBookInfo metodunda BookDto nesnesini oluştururken Book entity'sinin özelliklerini BookDto constructorunun beklediği sırayla göndermek gerekiyordu.
    /*public BookDto(Long id, String title, Author authorName, String categoryOfBook, int yearPublished) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.categoryOfBook = categoryOfBook;
        this.yearPublished = yearPublished;
    }*/






}
