package com.javainuse.controller;

import com.javainuse.model.JwtRequest;
import com.javainuse.model.JwtResponse;
import com.javainuse.model.User;
import com.javainuse.service.userlib.DeleteUser;
import com.javainuse.service.userlib.UpdateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.javainuse.service.UserService;
import java.util.List;

// DELETE HARICINDE TAMAM, BIRDE UPDATE DE SIFRE GORUNUYOR ONU DA DEGISTIR.
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UpdateUser updateUser;
    private DeleteUser deleteUser;
    public UserController(UserService userService,UpdateUser updateUser,DeleteUser deleteUser) {
        this.userService = userService;
        this.updateUser = updateUser;
        this.deleteUser= deleteUser;
    }

    // GET
    @GetMapping
    public ResponseEntity <List<User>> getAllUsers() {
        if(userService.isCurrentUserLibrarian()){
            return ResponseEntity.ok(userService.getAllUsers());
        }
        return ResponseEntity.status(401).build();
    }


    // Sadece Service katmaninda isCurrentUserLibrarian() diye authanticate ettigimizde 200 ve 500 donsuruyor
    // 401 not found vs dondurmuyor. 400'leri de dondurmesi icin burada tekrar if(isCurrentUserLibrarian().. gibi bir sorgu aciyoruz.
    @GetMapping("/{userId}")
    public ResponseEntity <User> getOneUser(@PathVariable Long userId) {
        if(!userService.isCurrentUserLibrarian()){
            return ResponseEntity.status(450).build();
        }
        return ResponseEntity.ok(userService.getOneUser(userId));
    }

    /* Bu sekle donusturmeden once, yani bu haldeyken:

        public User getOneUser(@PathVariable Long userId) {
        return userService.getOneUser(userId);
        }
        401 diye bir hata donemiyordu, tamamsa 200 degilse 500 hatasini donduruyordu.
     */

    // UPDATE
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserData(@PathVariable Long userId, @RequestBody User newUserData){
        if(!userService.isCurrentUserLibrarian()){
            ResponseEntity.status(407).build();
        }

        return ResponseEntity.ok(updateUser.updateUserData(userId ,newUserData));
    }


    // DELETE
    @DeleteMapping("/{userId}")
    public String deleteOneUser(@PathVariable Long userId){
        return deleteUser.deleteUser(userId);
    }
}

