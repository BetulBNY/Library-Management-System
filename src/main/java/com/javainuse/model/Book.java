package com.javainuse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;



@Data
@Entity
// Bu anotasyon, bir JPA (Java Persistence API) varlığı olduğunu belirtir. JPA, Java sınıflarını ilişkisel veritabanlarındaki tablolara eşlemek için kullanılır.
@Table(name = "BOOK")
public class Book {

        @Id  // Bu entitynin birincil anahtar oldugunu belirtmek icin.
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // Otomatik olarak artan bir deger kullanmak icin.
        private Long id;

        @Column(name = "Book_Title", length=60, nullable=false, unique=false) // Ozel bir sutun adi
        private String bookName;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "auth_id")
        private Author author;

       // @Column(name = "book_category")
        //private String categoryOfBook;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id")
        private BookCategory bookCategory;

        @Column(name = "publish_year")
        private int yearpublished;

        ///////////////////////NEW
       // @Column(name = "Book_Status")
        //private String status;   // kitabin durumu yani kutuphanede mi yoksa odunc alinmis mi?

        //public String getStatus() {return status;}

        //public void setStatus(String status) {this.status = status;}

        /*
        @ManyToOne  //bir yazarın birden fazla kitabı olabilir, ancak bir kitap sadece bir yazar ile ilişkilendirilebilir
        @JoinColumn(name = "author_id", referencedColumnName = "id") // Bu anotasyon, author alanını veritabanında author_id adında bir sütun ile ilişkilendirir. referencedColumnName = "id" ise bu ilişkinin Author tablosundaki id sütunu ile eşleştiğini belirtir.
        @JsonBackReference // sonsuz donguye engel olmak icin
        private Author author; */

        /*
        @OneToOne
        @JoinColumn(name = "author_id", referencedColumnName = "id")
        private Author authorId;
*/


    }


// author: Kitabın yazarını temsil eder. @ManyToOne ve @JoinColumn anotasyonları ile bu alanın veritabanında
// author_id adında bir sütun ile ilişkilendirildiğini ve bu sütunun Author tablosundaki id sütunu ile eşleştiğini
// belirtiyoruz. @JsonBackReference anotasyonu, JSON çıktısında sonsuz döngü oluşmasını önlemek için kullanılır.



