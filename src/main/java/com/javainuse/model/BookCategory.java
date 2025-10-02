package com.javainuse.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Table(name = "BOOK_CATEGORY")
@Entity
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "Category_Name", length=60, nullable=false, unique=false)
    private String categoryName;
}
