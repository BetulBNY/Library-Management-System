package com.javainuse.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long authorid;

    @Column(name = "AuthorName", length=60, nullable=false, unique=false) // Ozel bir sutun adi
    private String authorName;

    //@OneToMany(mappedBy = "author", cascade = CascadeType.ALL): Bu anotasyon, Author sınıfının birçok Book ile ilişkili olabileceğini ve bu ilişkinin Book sınıfındaki author alanı üzerinden yönetileceğini belirtir. cascade = CascadeType.ALL ise, Author silindiğinde ilişkili tüm kitapların da silineceğini belirtir.
    //@OneToMany(mappedBy = "author") //mappedBy = "author" ifadesi, bu ilişkinin Book sınıfında author alanı ile yönetildiğini belirtir.
    //private List<Book> books;

    //@OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    //private List<Book> book;
}
