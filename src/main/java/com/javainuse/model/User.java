package com.javainuse.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER")
public class User {

    // 2 farkli user tipimiz var biri librarian digeri kitap alan/musteri.
    // Java'da inheritance kullanarak ve Spring Security ile yetkilendirme yaparak cozucez.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length=60, nullable=false, unique=false) // Ozel bir sutun adi
    private String userName;

    @Column(name = "user_password", length=60, nullable=false, unique=false) // Ozel bir sutun adi
    private String userPassword;


    @Column(name = "contact", length=60, nullable=false, unique=false) // Ozel bir sutun adi
    private int contactNumber;

    private String role; // USER veya LIBRARIAN

    // Getter ve Setter metotları

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String password) {
        this.userPassword = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    // Abstract method for the role

}
