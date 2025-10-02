package com.javainuse.service.userlib;

import com.javainuse.model.User;
import com.javainuse.repository.UserRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class SaveUser {

    private final UserRepo userRepo;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public User saveOneUser(User newUser){
        newUser.setUserPassword(bCryptPasswordEncoder.encode(newUser.getUserPassword()));
        return userRepo.save(newUser);}
}
